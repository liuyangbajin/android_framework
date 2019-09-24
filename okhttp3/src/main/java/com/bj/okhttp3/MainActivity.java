package com.bj.okhttp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * OkHttpClient.Builder的扩展属性
 *
 * final Dispatcher dispatcher;  //重要：分发器,分发执行和关闭由request构成的Call
 * final Proxy proxy;  //代理
 * final List<Protocol> protocols; //协议
 * final List<ConnectionSpec> connectionSpecs; //传输层版本和连接协议
 * final List<Interceptor> interceptors; //重要：拦截器
 * final List<Interceptor> networkInterceptors; //网络拦截器
 * final ProxySelector proxySelector; //代理选择
 * final CookieJar cookieJar; //cookie
 * final Cache cache; //缓存
 * final InternalCache internalCache;  //内部缓存
 * final SocketFactory socketFactory;  //socket 工厂
 * final SSLSocketFactory sslSocketFactory; //安全套接层socket 工厂，用于HTTPS
 * final CertificateChainCleaner certificateChainCleaner; // 验证确认响应证书 适用 HTTPS 请求连接的主机名。
 * final HostnameVerifier hostnameVerifier;    //  主机名字确认
 * final CertificatePinner certificatePinner;  //  证书链
 * final Authenticator proxyAuthenticator;     //代理身份验证
 * final Authenticator authenticator;      // 本地身份验证
 * final ConnectionPool connectionPool;    //连接池,复用连接
 * final Dns dns;  //域名
 * final boolean followSslRedirects;  //安全套接层重定向
 * final boolean followRedirects;  //本地重定向
 * final boolean retryOnConnectionFailure; //重试连接失败
 * final int connectTimeout;    //连接超时
 * final int readTimeout; //read 超时
 * final int writeTimeout; //write 超时
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);
        findViewById(R.id.btn_post_upload).setOnClickListener(this);
        findViewById(R.id.btn_post_download).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_get: // get

                startActivity(new Intent(this, TestGetAty.class));
                break;
            case R.id.btn_post: // post

                startActivity(new Intent(this, TestPostAty.class));
                break;
            case R.id.btn_post_upload: // 上传文件

                startActivity(new Intent(this, TestPostUploadFileAty.class));
                break;
            case R.id.btn_post_download: // 下载文件

                startActivity(new Intent(this, TestPostDownloadFileAty.class));
                break;
        }
    }
}
