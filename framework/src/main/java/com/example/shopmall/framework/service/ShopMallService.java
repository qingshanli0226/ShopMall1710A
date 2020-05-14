package com.example.shopmall.framework.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.*;
import android.util.Log;
import com.example.shopmall.framework.bean.LoginBean;
import com.example.shopmall.framework.bean.ShopCartBean;
import com.example.shopmall.framework.bean.StepBean;
import com.example.shopmall.framework.manager.CacheManager;
import com.example.shopmall.net.RetrofitCreator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//负责后台任务,例如换出的处理,保活功能
public class ShopMallService extends Service implements SensorEventListener {

    //计步相关的成员变量Start
    private SensorManager sensorManager;
    private int currentStep; //当天的计步数据。
    private int systemStep;  //系统上报的计步数据。
    private String date; //当天时间的字符串
    private boolean flag = false;//应用程序启动后，检查这个flag，当flag为false时，就会去找回应用被系统杀掉后丢失的数据
    private final int DELAY_TIME = 1000;//1秒存储一次数据
    //计步相关的成员变量End

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        initStep();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ShopMallBinder();
    }



    public class ShopMallBinder extends Binder {
        public ShopMallService getService() {
            return ShopMallService.this;
        }
    }

    //获取首页数据
    public void getHomeData(final IHomeDataReceiveListener iHomeDataReceiveListener) {
        String path = "atguigu/json/HOME_URL.json";
        RetrofitCreator.getNetAPIService()
                .getData(path, new HashMap<String, String>())
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())//因为获取到数据后，实现数据存储，所以不能切换到主线程
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //获取到首页数据后，实现数据存储，并且
                        try {
                            iHomeDataReceiveListener.onHomeDataReceived(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //自动登录功能
    public void autoLogin(String token, @NonNull final IAutoLoginListener iAutoLoginListener) {
        String path = "autoLogin";
        HashMap<String,String> paramsMap = new HashMap<>();
        paramsMap.put("token", token);

        RetrofitCreator.getNetAPIService().postData(path, paramsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Type type  =  new TypeToken<LoginBean>(){}.getType();
                        try {
                            LoginBean loginBean = new Gson().fromJson(responseBody.string(), type);
                            if (loginBean.getCode().equals("200")) {
                                iAutoLoginListener.onAutoLoginSuccess(loginBean);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getShopcarCount(String token, final IShopcarDataListener iShopcarDataListener) {
        String path = "getShortcartProducts";
        //Header中的token在拦截器中添加的

        RetrofitCreator.getNetAPIService().getData(path, new HashMap<String, String>())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Type type  =  new TypeToken<ShopCartBean>(){}.getType();
                        try {
                            ShopCartBean shopCartBean = new Gson().fromJson(responseBody.string(), type);
                            iShopcarDataListener.onReceiveShopcarData(shopCartBean.getResult().size(),shopCartBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public interface IShopcarDataListener {
        void onReceiveShopcarData(int count, ShopCartBean shopCartBean);
    }

    //定义接口，实现获取数据后通知Manager

    public interface IHomeDataReceiveListener{
        void onHomeDataReceived(String homeJsonStr);
    }

    public interface IAutoLoginListener {
        void onAutoLoginSuccess(LoginBean loginBean);
        //失败了，暂时不用处理，下次点击购物车，会弹出登录界面，让用户登录
    }


    //和计步相关的数据
    private void initStep() {
        //初始胡数据
        initData();
        //初始胡计步传感器
        initCounterSensorListener();
        //初始化系统广播
        initReceiver();

    }

    private void initData() {
        //获取当天的数据.
        date = getCurrentDate();//获取当前的日期，就是我们正在计算的那天的步数。2019-05-29 修改为2019-05-30
        StepBean stepBean = CacheManager.getInstance().getCurrentDateStep(date);//去获取存储的数据。数据有三个值，一个是日期，一个是这个日期的计步数，还有这个日期的系统返回的数据
        if (stepBean == null) {//如果service启东时，还没有计步数据，
            currentStep = 0;//初始化数据为0
            systemStep = 0;
        } else {
            //赋初值
            currentStep = stepBean.getCurrentStep();
            systemStep = stepBean.getSystemStep();
        }
        handler.sendEmptyMessageDelayed(1,DELAY_TIME);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            StepBean stepBean = new StepBean();//更新数据
            stepBean.setSystemStep(systemStep);
            stepBean.setDate(date);
            stepBean.setCurrentStep(currentStep);
            CacheManager.getInstance().saveStepInfo(stepBean);

            handler.sendEmptyMessageDelayed(1, DELAY_TIME);//一秒钟存储数据一次.
        }
    };

    //判断当前是不是新的一天
    private boolean isNewDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");
        Calendar calendar = Calendar.getInstance();
        String currentDateStr = dateFormat.format(calendar.getTime());
        Log.d("LQS Date = ", currentDateStr);
        Log.d("LQS Date Time = ", new SimpleDateFormat("HH:mm:ss").format(calendar.getTime()));
        if (date.equals(currentDateStr)) {
            return false;//还是原来的日子
        } else {
            return true; //已经是新的一天了.
        }
    }

    //获取时间字符串
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar calendar = Calendar.getInstance();
        String currentDateStr = dateFormat.format(calendar.getTime());
        return currentDateStr;
    }


    //初始化传感器，目的是获取系统返回的数据的
    private void initCounterSensorListener() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor counterSensor =sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (Build.VERSION.SDK_INT >= 19) {//计步功能在android 4.3后添加的.
            Log.d("LQS ", " register counter sensor");
            sensorManager.registerListener(this, counterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
        }
    }

    //在这个方法中获取系统返回的数据,该方法，你每走一步都会回调该方法
    @Override
    public void onSensorChanged(SensorEvent event) {
        int stepValue = (int) event.values[0];//得到系统返回的计步数据
        int missStep = 0; //应用被杀掉，之后又被启动，中间丢失的步数.

        if (!flag) {//是否需要找回丢书数据的标志.只有启动后，第一次该函数调用时计算添加丢失的数据.
            if (systemStep == 0) {//之前没有记录系统返回的数据，代表没有丢失数据
            } else {
                missStep = stepValue - systemStep;//找到丢失的数据
                currentStep += missStep;//将丢失的数据，累加到当天的计步数据上.
                currentStep += 1;//又增加一步
                systemStep = stepValue; //更新系统的步数.
                Log.d("LQS ", " find miss steps..");
            }
            flag = true;
        } else {
            currentStep++; //增加一步
            systemStep = stepValue; //更新数据
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    /**
     * 注册receiver，去监听系统发出的广播. 去监听时间的变化，例如天数改变了.
     */
    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);

        registerReceiver(receiver, intentFilter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Intent.ACTION_DATE_CHANGED://新的一天到来。
                    handler.removeMessages(1);
                    initData();//将时间更新.
                    break;
                case Intent.ACTION_TIME_CHANGED:
                case Intent.ACTION_TIME_TICK: //一份钟发一次.
                    if (isNewDate()) {
                        handler.removeMessages(1);
                        initData();
                    }
                    break;
                default:
                    break;

            }
        }
    };
}
