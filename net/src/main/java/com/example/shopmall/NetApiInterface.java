package com.example.shopmall;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.util.Map;

public interface NetApiInterface {
    @GET("{path}")
    Observable<ResponseBody> getData(@Path(value = "path", encoded = true) String path, @QueryMap Map<String, Object> params);

    @POST("{path}")
    @FormUrlEncoded
    Observable<ResponseBody> postData(@Path(value = "path", encoded = true) String path, @FieldMap Map<String, Object> params);

    //下载文件
    @GET
    @Streaming
    //防止占用内存过多，避免OOM问题也就是内存溢出
    Observable<ResponseBody> downloadFile(@Url String url);

    @POST("{path}")
    Observable<ResponseBody> postDataWithJson(@Path(value = "path", encoded = true) String path, @Body RequestBody body);

    @Multipart
    @POST("{path}")
    Observable<ResponseBody> uploadFile(@Path(value = "path", encoded = true) String path, @Part("body") RequestBody body, @Part MultipartBody.Part file);
}
