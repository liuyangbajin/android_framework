package com.bj.databinding.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.bj.databinding.BR;

public class Goods extends BaseObservable {

    // 如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;

    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private String details;

    private float price;

    public Goods(String name, String details, float price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;

        // 只更新本字段
        notifyPropertyChanged(BR.name);
    }

    public void setDetails(String details) {
        this.details = details;

        // 更新所有字段
        notifyChange();
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", price=" + price +
                '}';
    }
}