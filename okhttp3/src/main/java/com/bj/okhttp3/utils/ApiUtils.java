package com.bj.okhttp3.utils;

public interface ApiUtils {

    String BaseUri = "http://192.168.43.129:8081/TestOkhttp3_war_exploded/";

    // post提交json
    String TestPost = BaseUri + "TestPost";

    // 上传文件
    String TestPostUpload = BaseUri + "TestPostUpload";

    // 下载文件
    String TestPostDownload = "http://gdown.baidu.com/data/wisegame/812ce50d5cfdeacf/eliaome_372.apk";
}
