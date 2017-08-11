package com.ty.warwolf.model.retrofit;


import com.ty.warwolf.config.Const;
import com.ty.warwolf.util.AppSystemUtil;
import com.ty.warwolf.util.EncryptUtil;
import com.ty.warwolf.util.UIUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @ 文件名:   SignInterceptor
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午3:01
 * @ 描述:
 */

public class SignInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HashMap<String, String> map = new HashMap<>();
        //添加常用参数
        map.put(Const.BASE_VERSION_NAME, AppSystemUtil.getVersionName());
        map.put(Const.BASE_DEVICE_TYPE, AppSystemUtil.getOS());
        RequestBody requestBody = request.body();

        //签名加密算法实现及认证
        try {
            if (requestBody instanceof FormBody) {
                FormBody formBody = (FormBody) requestBody;
                int size = formBody.size();
                for (int i = 0; i < size; i++) {
                    String name = formBody.encodedName(i);
                    String value = formBody.encodedValue(i);
                    //添加截取到的参数
                    map.put(name, value);
                }

                //将map中的参数生成json格式
                String sendData = String.valueOf(new JSONObject(map));

                //将上一步的sendData作为值再次加入集合中，键为data
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("data", sendData);

                //生成base64字符
                String sign = EncryptUtil.encrypt(String.valueOf(new JSONObject(hashMap)));

                requestBody = new FormBody.Builder()
                        //重新添加token
                        .add(Const.TOKEN, UIUtil.getToken())
                        //添加base64加密字符参数
                        .add(Const.ENCRYPT, sign)
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //重新生成request
        Request.Builder builder = request.newBuilder().method(request.method(), requestBody);
        Response response = chain.proceed(builder.build());
        return response;
    }
}
