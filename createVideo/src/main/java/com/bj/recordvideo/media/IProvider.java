package com.bj.recordvideo.media;

/**
 * 提供数据方
 *
 * @param <T>
 */
public interface IProvider<T> {

    int size();

    T next();

    void prepare();

    void finish();

    void finishItem(T item);
}