package com.ty.warwolf.parise;

import com.dgrlucky.log.parser.Parser;

import okhttp3.Headers;

/**
 * @ 文件名:   HeaderParse
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:42
 * @ 描述:
 */

public class HeaderParse implements Parser<Headers> {
    @Override
    public Class<Headers> parseType() {
        return Headers.class;
    }

    @Override
    public String parseResult(Headers headers) {
        StringBuilder builder = new StringBuilder(headers.getClass().getSimpleName() + " [" +
                SEPARATOR);
        for (String name : headers.names()) {
            builder.append(String.format("%s = %s" + SEPARATOR,
                    name, headers.get(name)));
        }
        return builder.append("]").toString();
    }
}
