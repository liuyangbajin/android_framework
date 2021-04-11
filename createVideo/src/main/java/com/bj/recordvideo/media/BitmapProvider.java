package com.bj.recordvideo.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;

/**
 * <pre>
 *     author : yangzhi.ou
 *     e-mail : xxx@xx
 *     time   : 2019/05/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BitmapProvider implements IProvider<Bitmap> {
    private File[] mList;
    private int index = 0;

    public BitmapProvider(File[] mList) {
        this.mList = mList;
    }

    @Override
    public void prepare() {
    }

    @Override
    public void finish() {
    }

    @Override
    public void finishItem(Bitmap item) {
        item.recycle();
//        System.gc();
    }

    @Override
    public int size() {
        return mList.length;
    }

    @Override
    public Bitmap next() {
        String picPath = "";
        if(index < mList.length){
            try {
                picPath = mList[index++].getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return BitmapFactory.decodeFile(picPath);
    }
}