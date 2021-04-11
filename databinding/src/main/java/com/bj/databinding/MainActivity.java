package com.bj.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;

import android.os.Bundle;
import android.view.View;

import com.bj.databinding.databinding.ActivityMainBinding;
import com.bj.databinding.model.Goods;
import com.bj.databinding.model.User;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements StringUtils.OnMsgListener{

    private Goods goods;
    public ObservableField<String> msg01 = new ObservableField<>();
    public ObservableField<String> msg02 = new ObservableField<>();
    private StringUtils mStringUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.正常设置数据bean
        ActivityMainBinding mbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User mUser = new User("xiaoming", "damin123");
        mbinding.setUserInfo(mUser);

        // 2.改动单项
        String username = mbinding.tvUsername.getText().toString();
//        System.out.println(username);

        mbinding.tvUsername.setText("我改动了");
//        System.out.println(username);

        // 3.继承的方式，单项数据绑定
        goods = new Goods("java", "最好的程序语言", 24);
        mbinding.setGoodInfo(goods);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                goods.setName("code" + new Random().nextInt(100));
                goods.setPrice(new Random().nextInt(100));
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                goods.setDetails("hi" + new Random().nextInt(100));
                goods.setPrice(new Random().nextInt(100));
            }
        });

        // 4.非继承的方式，单项数据绑定
        mbinding.setAty(this);
        msg01.set("msg11111");

        // 5.非继承的方式，双向绑定
        msg02.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {

            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                System.out.println(msg02.get());
            }
        });

        // 6.使用类方法
        mStringUtils = new StringUtils();
    }

    public void toMsg(String msg){

        System.out.println(msg);
    }

    @Override
    @BindingAdapter(value = "setOnMsgListener", requireAll = false)
    public void onMsg(View textView, String msg) {

        System.out.println(msg);
    }
}
