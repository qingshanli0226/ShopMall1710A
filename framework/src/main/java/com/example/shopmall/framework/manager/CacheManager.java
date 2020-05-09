package com.example.shopmall.framework.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.example.shopmall.common.util.SpUtil;
import com.example.shopmall.framework.bean.ShopCartBean;
import com.example.shopmall.framework.greendao.DaoMaster;
import com.example.shopmall.framework.greendao.DaoSession;
import com.example.shopmall.framework.message.ShopMallMessage;
import com.example.shopmall.framework.greendao.ShopMallMessageDao;
import com.example.shopmall.framework.service.ShopMallService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//单例，负责整个应用的存储功能
public class CacheManager {
    private ShopMallService shopMallService;
    public Context applicationContext;//这个地方不能存放Activity的上下文，是因为使用Activity上下文的话，会导致内存泄漏.
    public ExecutorService executorService;//声明线程池

    //缓存购物车的成员变量
    private List<IShopcarDataRecevedLisener> shopCountRecevedLisenerList = new LinkedList<>();
    private ShopCartBean shopCartBean;

    //缓存首页数据的成员通知变量
    private IHomeDataListener iHomeDataListener;

    //缓存消息数据的成员变量
    private List<ShopMallMessage> shopMallMessageList = new ArrayList<>();
    private int countUnReadMessage;//缓存的未读的消息数量
    private ShopMallMessageDao shopMallMessageDao;//操作消息数据的dao文件
    private List<IShopMessageChangedListener> shopMessageChangedListenerList = new LinkedList<>();

    private static CacheManager instance;
    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }

    public void registerShopCountListener(IShopcarDataRecevedLisener listener) {
        if (!shopCountRecevedLisenerList.contains(listener)) {
            shopCountRecevedLisenerList.add(listener);
        }
    }

    public void unRegisterShopCountListener(IShopcarDataRecevedLisener listener) {
        if (shopCountRecevedLisenerList.contains(listener)) {
            shopCountRecevedLisenerList.remove(listener);
        }
    }

    public ShopCartBean getShopCartBean() {
        return shopCartBean;
    }


    //初始化函数
    public void init(final Context context) {
        applicationContext = context;
        executorService = Executors.newCachedThreadPool();//实例化线程池，使用缓存类型的线程池。
                //在初始化方法里去启动并且绑定service
        Intent intent = new Intent();
        intent.setClass(context, ShopMallService.class);

        context.startService(intent);

        context.bindService(intent, new ServiceConnection() {
            @Override
             public void onServiceConnected(ComponentName name, IBinder service) {
                ShopMallService.ShopMallBinder shopMallBinder = (ShopMallService.ShopMallBinder)service;
                shopMallService = shopMallBinder.getService();
                shopMallService.getHomeData(new ShopMallService.IHomeDataReceiveListener() {
                    @Override
                    public void onHomeDataReceived(String homeJsonStr) {
                        //代表数据已经返回
                        //1, 存储数据 2,通知MainActivity去刷新首页数据
                        SpUtil.saveHomeData(context, homeJsonStr);
                        if (iHomeDataListener == null) {
                            return;
                        }
                        iHomeDataListener.onHomeDataReceived(homeJsonStr);
                    }
                });

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

        //监听用户登录的状态，当登录成功后，获取购物车产品数量，并且把数量存储起来,多个页面可以获取这个数量
        ShopUserManager.getInstance().registerUserLoginListener(new ShopUserManager.IUserLoginListener() {
            @Override
            public void onLoginSuccess() {//登录成功后，获取购物车数据。
                shopMallService.getShopcarCount(SpUtil.getTpken(context), new ShopMallService.IShopcarDataListener() {
                    @Override
                    public void onReceiveShopcarData(int count, ShopCartBean shopCartBeanResult) {
                        shopCartBean = shopCartBeanResult;
                        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
                            lisener.onShopcarDataReceived(count,shopCartBean,-1);//如果是-1的话全部刷新
                        }
                    }
                });
            }

            @Override
            public void onLogoutSuccess() {

            }
        });

        //初始化好greenDao，实例化消息数据库的dao文件
        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(context, "shop.db");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        shopMallMessageDao = daoSession.getShopMallMessageDao();

        //应用启动后，在后台从数据库中读出消息数据
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //从数据中读出数据，数据按照时间戳的降序排列
                shopMallMessageList = shopMallMessageDao.queryBuilder().orderDesc(ShopMallMessageDao.Properties.Time).list();
                countUnReadMessage = 0;
                for(ShopMallMessage message:shopMallMessageList) {
                    if (!message.getIsRead()) {
                        countUnReadMessage++;
                    }
                }
                notifyMessageChanged(-1);//-1代表全部刷新
            }
        });

    }

    /**************************************购物车缓存数据Start***********************************************/

    public void addNewShopcardata(Context context, int addNum, ShopCartBean.ShopcarData shopcarData) {
        //更新缓存的数据
        shopCartBean.getResult().add(shopcarData);

        //去通知UI刷新数据
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(shopCartBean.getResult().size(),shopCartBean,-1);
        }
    }

    public void updateShopcarProductNum(Context context,String productId, ShopCartBean.ShopcarData newShopcarData) {
        int index = 0;
        for(ShopCartBean.ShopcarData shopcarData:CacheManager.getInstance().getShopCartBean().getResult()) {
            if (productId.equals(shopcarData.getProductId())) {
                shopcarData.setProductNum(newShopcarData.getProductNum());
                break;
            } else {
                index++;
            }
        }
        //去通知UI刷新数据
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(shopCartBean.getResult().size(),shopCartBean,index);
        }
    }

    public void updateShopcarProductSelected(ShopCartBean.ShopcarData shopcarData) {
        //第一步修改缓存中，该产品的选择值.
        int index = 0;
        for(ShopCartBean.ShopcarData item:shopCartBean.getResult()) {
            if (item.getProductId().equals(shopcarData.getProductId())) {
                item.setProductSelected(shopcarData.isProductSelected());
                break;
            } else  {
                index++;
            }
        }
        //第二步，需要做什么事情?
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(shopCartBean.getResult().size(),shopCartBean, index);
        }

    }

    //获取购物车选择的商品
    public List<ShopCartBean.ShopcarData> getSelectedProducts() {
        List<ShopCartBean.ShopcarData> selectedShopcarDataList = new ArrayList<>();
        for(ShopCartBean.ShopcarData item:shopCartBean.getResult()) {
            if (item.isProductSelected()) {
                selectedShopcarDataList.add(item);
            }
        }
        return selectedShopcarDataList;
    }

    public void selectAllProduct(boolean selected) {
        for(ShopCartBean.ShopcarData item:shopCartBean.getResult()) {
            item.setProductSelected(selected);
        }

        //第二步，需要做什么事情?
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(shopCartBean.getResult().size(),shopCartBean,-1);
        }
    }

    //删除产品
    public void removeManyProducts(List<ShopCartBean.ShopcarData> shopcarDataList) {
        for(ShopCartBean.ShopcarData item:shopcarDataList) {
           if (shopCartBean.getResult().contains(item)) {
               shopCartBean.getResult().remove(item);
           }
        }

        //第二步，需要做什么事情?
        for(IShopcarDataRecevedLisener lisener:shopCountRecevedLisenerList) {
            lisener.onShopcarDataReceived(shopCartBean.getResult().size(),shopCartBean,-1);
        }
    }

//获取购物车产品数量
    public int getShopcarCount() {
        return shopCartBean.getResult().size();
    }

    public interface IShopcarDataRecevedLisener {
        void onShopcarDataReceived(int conunt, ShopCartBean shopCartBean, int index);
    }


    //定义接口，通知homeData数据已经获取到。,Manager屏蔽了Service, Activity是直接和Manager进行通信
    public interface IHomeDataListener{
        void onHomeDataReceived(String homeDataJson);
    }

    public void registerIHomeDataListener(IHomeDataListener listener) {
        iHomeDataListener =  listener;
    }
    public void unRegisterIHomeDataListener() {
        iHomeDataListener = null;
    }


    //提供方法，让MainActivity去获取首页数据
    public String getHomeData(Context context) {
        return SpUtil.getHomeData(context);
    }

    /**************************************购物车缓存数据End***********************************************/


    /**************************************消息缓存数据Satart*************************************************/

    public List<ShopMallMessage> getShopMallMessageList() {
        return shopMallMessageList;
    }

    public int getCountUnReadMessage() {
        return countUnReadMessage;
    }

    public void registerShopMessageChangedListener(IShopMessageChangedListener listener) {
        if (!shopMessageChangedListenerList.contains(listener)) {
            shopMessageChangedListenerList.add(listener);
        }
    }

    public void unRegisterShopMessageChangedListener(IShopMessageChangedListener listener) {
        if (shopMessageChangedListenerList.contains(listener)) {
            shopMessageChangedListenerList.remove(listener);
        }
    }

    //通知UI刷新内容
    private void notifyMessageChanged(int index) {
        for(IShopMessageChangedListener iShopMessageChangedListener:shopMessageChangedListenerList) {
            iShopMessageChangedListener.onShopMessageChanged(shopMallMessageList, countUnReadMessage,index);
        }
    }


    //增加一条消息
    public void addShopMessage(ShopMallMessage shopMallMessage) {
        if (shopMallMessageList.contains(shopMallMessage)) {
            return;
        }

        shopMallMessageDao.insert(shopMallMessage);//向数据库添加一条数据
        //更新缓存，并且将该数据添加到列表的第0个位置
        shopMallMessageList.add(0, shopMallMessage);
        countUnReadMessage++;
        //更新UI
        for(IShopMessageChangedListener listener:shopMessageChangedListenerList) {
            listener.onShopMessageAdded(shopMallMessage, countUnReadMessage, 0);
        }
    }

    //删除一条消息
    public void deleteShopMessage(ShopMallMessage shopMallMessage) {
        if (!shopMallMessageList.contains(shopMallMessage)){
            return;
        }
        shopMallMessageDao.delete(shopMallMessage);
        if (!shopMallMessage.getIsRead()) {//如果是未读的话，就需要该表未读数据的数量
            countUnReadMessage--;
        }

        //找到删除数据位置
        int index = shopMallMessageList.indexOf(shopMallMessage);
        shopMallMessageList.remove(shopMallMessage);
        for(IShopMessageChangedListener listener:shopMessageChangedListenerList) {
            listener.onShopMessageDelted(shopMallMessage, countUnReadMessage, index);
        }
    }

    //更新一条数据，只能是将未读的状态改成已读
    public void updateShopMallMessageToReadStatus(ShopMallMessage shopMallMessage) {
        if (!shopMallMessageList.contains(shopMallMessage)){
            return;
        }
        //更新数据库
        //将链表中的数据未读状态变成已读
        shopMallMessage.setIsRead(true);
        shopMallMessageDao.update(shopMallMessage);
        int index = shopMallMessageList.indexOf(shopMallMessage);
        countUnReadMessage--;
        for(IShopMessageChangedListener listener:shopMessageChangedListenerList) {
            listener.onShopMessageUpdated(shopMallMessage, countUnReadMessage, index);
        }
    }
    //查找所有数据
    public List<ShopMallMessage> findAllMessage() {
        return shopMallMessageDao.queryBuilder().orderDesc(ShopMallMessageDao.Properties.Time).list();
    }




    //定义一个刷新UI的接口listener
    public interface IShopMessageChangedListener {
         void onShopMessageChanged(List<ShopMallMessage> shopMallMessageList, int unReadCount, int index);
         void onShopMessageAdded(ShopMallMessage shopMallMessage, int unReadCount, int index);
         void onShopMessageDelted(ShopMallMessage shopMallMessage, int unReadCount, int index);
         void onShopMessageUpdated(ShopMallMessage shopMallMessage, int unReadCount, int index);
    }
    /**************************************消息缓存数据End*************************************************/
}
