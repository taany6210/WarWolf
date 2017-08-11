package com.ty.warwolf.model.retrofit;

import android.util.Pair;

import com.dgrlucky.log.LogX;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @ 文件名:   LogInterceptor
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:00
 * @ 描述:
 */

public class LogInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String interParam = null;
        Request original = chain.request();
        LogX.d(original.url().toString());
        Request.Builder requestBuilder = original.newBuilder();

        if (original.body() instanceof FormBody) {
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oldFormBody = (FormBody) original.body();
            for (int i = 0; i < oldFormBody.size(); i++) {
                String value = oldFormBody.encodedValue(i);
                if (i == 0) {
                    value = URLDecoder.decode(value, "UTF-8");
                    interParam = value;
                }
                newFormBody.addEncoded(oldFormBody.encodedName(i), value);
            }

            FormBody build = newFormBody.build();

            printRequestLog(build);

            requestBuilder.method(original.method(), build);
        }

        Request request = requestBuilder.build();
        Response response = chain.proceed(request);

        ResponseBody body = response.body();
        String json = printResponseLog(body);

        return transformerData(response, json);
    }

    private void printRequestLog(FormBody build) throws UnsupportedEncodingException {
        String str = build.encodedValue(0);

        String decode = URLDecoder.decode(str, "UTF-8");

        String json = "{Request<interParam>:" + decode + "}";

        try {
            LogX.json(json);
        } catch (Exception e) {
            LogX.e("Json serialization failure\n\n" + json);
        }

        StringBuilder log = new StringBuilder();
        for (int i = 1; i < build.size(); i++) {
            String value = build.encodedValue(i);
            String decodeValue = URLDecoder.decode(value, "UTF-8");
            log.append("key   : ")
                    .append(build.encodedName(i))
                    .append("\n")
                    .append("value : ")
                    .append(decodeValue)
                    .append("\n");
        }

        LogX.d(log.toString());
    }

    private String printResponseLog(ResponseBody responseBody) throws IOException {
        String rawJson = null;

        long contentLength = responseBody.contentLength();

        if (contentLength != 0) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            rawJson = buffer.clone().readString(Charset.forName("UTF-8"));
            String json = "{Response:" + rawJson + "}";

            //            Logger.d(json);

            try {
                LogX.json(json);
            } catch (Exception e) {
                LogX.e("Json serialization failure\n\n" + json);
            }
        }
        return rawJson;
    }

    public Response transformerData(Response response, String json) {
        Pair<Boolean, String> booleanStringPair = transformerJson(json);
        if (booleanStringPair.first) {
            BufferedSource source = response.body().source();
            Buffer buffer = source.buffer();
            buffer.clear();
            buffer.writeString(booleanStringPair.second, Charset.forName("UTF-8"));
            return response.newBuilder().body(new RealResponseBody(response.headers(), source)).build();
        }
        return response;
    }

    public Pair<Boolean, String> transformerJson(String json) {
        return new Pair<>(false, json);
    }
}
