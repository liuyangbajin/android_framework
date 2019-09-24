package com.bj.okhttp3.utils;

import okhttp3.MediaType;

public enum MediaTypeUtils {

    JSON_UTF_8(MediaType.parse("application/json; charset=utf-8")),  // 设置Json数据传输并指定Utf-8为编码集
    UPLOAD_FILE(MediaType.parse("multipart/form-data")); // 上传文件

    public MediaType value;

    MediaTypeUtils(MediaType value) {
        this.value = value;
    }
}
