package com.ty.warwolf.model.retrofit;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @ 文件名:   JsonInterceptor
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:59
 * @ 描述:
 */

public class JsonInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody = request.body();
        HashMap<String, String> map = new HashMap<>();
        if (request.body() instanceof FormBody) {
            FormBody body = (FormBody) request.body();
            int size = body.size();
            for (int i = 0; i < size; i++) {
                String name = body.encodedName(i);
                String value = body.encodedValue(i);
                String nameDecode = URLDecoder.decode(name, "UTF-8");
                String valueDecode = URLDecoder.decode(value, "UTF-8");
                map.put(nameDecode, valueDecode);
            }

            String json = new Gson().toJson(map);
            requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        }

        Request request1 = request.newBuilder().method(request.method(), requestBody).build();

        return chain.proceed(request1);
    }
}
