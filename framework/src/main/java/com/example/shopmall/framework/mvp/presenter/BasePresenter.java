package com.example.shopmall.framework.mvp.presenter;

import com.example.shopmall.BaseObserver;
import com.example.shopmall.NetFunction;
import com.example.shopmall.RetrofitManager;
import com.example.shopmall.common.util.ErrorUtil;
import com.example.shopmall.framework.mvp.view.IBaseView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public abstract class BasePresenter<T,V extends IBaseView<T>> implements IPresenter{
    protected V mView;
    public BasePresenter(V mView) {
        this.mView = mView;
    }
    @Override
    public void destroy() {
        if (mView != null){
            mView = null;
        }
    }

    //必须子类实现，返回具体的网络接口地址
    protected abstract String getPath();
    // 获取解析类型 , 子类实现
    public abstract Type getBeanType();

    @Override
    public void getHttpData(final int requestCode) {
        RetrofitManager.getNetAPIService()
                .getData(getPath(),getParamsMap())
                .subscribeOn(Schedulers.io())
                //数据转换
                .map(new NetFunction<ResponseBody,T>(getBeanType()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() { // 网络请求前
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();
                    }
                })
                .doFinally(new Action() { // 请求结束
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new BaseObserver<T>(){
                    @Override
                    public void onNext(T t) {
                        mView.onHttpReceived(requestCode,t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onHttpReceivedFailed(requestCode, ErrorUtil.handleError(e));
                    }
                });

    }
    //定义通用方法，所有的post请求,参数以表单形式发送的,都可以通过该方法获取数据
    @Override
    public void postHttpData(final int requestCode) {
        RetrofitManager.getNetAPIService()
                .postData(getPath(), getParamsMap())
                .delay(2, TimeUnit.SECONDS)//延迟两秒后进行网络请求
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<ResponseBody, T>(getBeanType()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();//该方法是向服务端发起网络请求之前，调用的方法
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        //是网络请求结束，且用户拿到数据后，调用的方法.在此方法中关闭加载页面
                        mView.hideLoading();
                    }
                })
                .subscribe(new BaseObserver<T>() {
                    //需要把类型做一个转换，转换成页面需要的类型
                    //onNext是异步运行的,如果直接返回数据的话，onNext函数执行时间在返回结果之后运行的
                    @Override
                    public void onNext(T result) {
                        mView.onHttpReceived(requestCode, result);
                    }
                    //onError是所有错误的入口
                    @Override
                    public void onError(Throwable e) {
                        mView.onHttpReceivedFailed(requestCode, ErrorUtil.handleError(e));//将结果返回给UI层
                    }

                });

    }
    //定义通用方法，所有的post请求参数以Json形式发送都可以通过该方法获取数据
    //异步方法
    @Override
    public void postHttpDataWithJson(final int requestCode) {
        RetrofitManager.getNetAPIService()
                .postDataWithJson(getPath(), getRequestBody())
                .subscribeOn(Schedulers.io())
                //数据转换，将服务端返回的数据，转换成我们需要的数据的
                .map(new NetFunction<ResponseBody, T>(getBeanType()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();//该方法是向服务端发起网络请求之前，调用的方法
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        //是网络请求结束，且用户拿到数据后，调用的方法.在此方法中关闭加载页面
                        mView.hideLoading();
                    }
                })
                .subscribe(new BaseObserver<T>() {
                    //需要把类型做一个转换，转换成页面需要的类型
                    //onNext是异步运行的,如果直接返回数据的话，onNext函数执行时间在返回结果之后运行的
                    @Override
                    public void onNext(T result) {
                        mView.onHttpReceived(requestCode, result);
                    }
                    //onError是所有错误的入口
                    @Override
                    public void onError(Throwable e) {
                        mView.onHttpReceivedFailed(requestCode, ErrorUtil.handleError(e));//将结果返回给UI层
                    }

                });
    }

    // 获取参数 params
    private Map<String, Object> parmas = new HashMap<>();
    //返回请求的参数Map，子类可以重写，如果子类不重写，返回默认空的Map
    public Map<String, Object> getParamsMap() {
        return parmas;
    }
    public void updateParamsMap(Map<String, Object> parmas) {
        this.parmas = parmas;
    }

    private RequestBody requestBody;
    public RequestBody getRequestBody() {
        return requestBody;
    }
    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }
}
