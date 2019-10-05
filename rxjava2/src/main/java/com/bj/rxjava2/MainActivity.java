package com.bj.rxjava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        创建操作符
//        testCreate01();
//        testCreate();
//        just();
//        timer();
//        interval();
//        intervalRange();
//        range();
//        rangeLong();
//        empty_never_error();

//        转换操作符
//        map();
//        flatMap();
//        concatMap();
//        buffer();
//        scan();
//        window();

//        组合操作符
//        concat();
//        concatArray();
//        merge();
//        zip();
//        startWithArray();
//        count();

//        功能操作符
//        delay();
//        doOnFunc();
//        onErrorReturn();
//        onErrorResumeNext();
//        onExceptionResumeNext();
//        retry();
//        retryUntil();
//        repeat();
//        subscribeOn_observeOn();

//        过滤操作符
//        filter();
//        ofType();
//        skip();
//        distinct();
//        distinctUntilChanged();
//        take();
//        debounce();
//        firstElement_lastElement_elementAt();

//        条件操作符
//        all();
//        takeWhile();
//        skipWhile();
//        isEmpty();
//        contains();
        sequenceEqual();
    }

    // ----------------------创建操作符--------------------
    /**
     * 简单使用
     * */
    private void testCreate01() {

        // 创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                emitter.onNext("你好呀");
                emitter.onNext("我爱中国");
                emitter.onNext("祝愿祖国繁荣富强");
                emitter.onComplete();
            }
        });

        // 创建观察者
        Observer observer = new Observer<String>(){

            @Override
            public void onSubscribe(Disposable d) {

                Log.i("lybj", "准备监听");
            }

            @Override
            public void onNext(String s) {

                Log.i("lybj", s);
            }

            @Override
            public void onError(Throwable e) {

                Log.i("lybj", "error");
            }

            @Override
            public void onComplete() {

                Log.i("lybj", "监听完毕");
            }
        };

        // 订阅
        observable.subscribe(observer);
    }

    /**
     * Create: 创建被观察者
     * */
    private void testCreate() {

        // 创建被观察者
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                emitter.onNext("你好呀");
                emitter.onNext("我爱中国");
                emitter.onNext("祝愿祖国繁荣富强");
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>(){ // 关联观察者

            @Override
            public void onSubscribe(Disposable d) {

                Log.i("lybj", "准备监听");
            }

            @Override
            public void onNext(String s) {

                Log.i("lybj", s);
            }

            @Override
            public void onError(Throwable e) {

                Log.i("lybj", "error");
            }

            @Override
            public void onComplete() {

                Log.i("lybj", "监听完毕");
            }
        });
    }

    /**
     * just: 创建被观察者，并发射数据
     * */
    private void just(){

        Observable.just("小明", "小红", "小兰").subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

                Log.i("lybj", "准备监听");
            }

            @Override
            public void onNext(String s) {

                Log.i("lybj", s+"来了");
            }

            @Override
            public void onError(Throwable e) {

                Log.i("lybj", "Error");
            }

            @Override
            public void onComplete() {

                Log.i("lybj", "完毕");
            }
        });
    }

    /**
     * timer: 当到指定时间后就会发送一个 0 的值给观察者。
     * 类似于项目中发Handler中的延时
     * */
    private void timer(){

        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {

            @Override
            public void accept(Long aLong) throws Exception {

                Log.i("lybj", aLong+"");
            }
        });
    }

    /**
     * interval: 每隔一段时间就会发送一个事件，这个事件是从0开始，不断增1的数字。
     * 类似于项目中的timer，做计时器
     * */
    private void interval(){

        Observable.interval(3,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

                Log.i("lybj", aLong+"");
            }
        });
    }

    /**
     * intervalRange: 指定发送数量的开始值和数量
     * */
    private void intervalRange(){

        Observable.intervalRange(100l, 4l, 0l, 10, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

                Log.i("lybj", aLong+"");
            }
        });
    }

    /**
     * range: 发送一定范围的事件序列。
     * */
    private void range(){

        Observable.range(0,10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

                Log.i("lybj", integer+"");
            }
        });
    }

    /**
     * rangeLong: 发送一定范围的事件序列，但是是long类型。
     * */
    private void rangeLong(){

        Observable.rangeLong(0,10).subscribe(new Consumer<Long>() {

            @Override
            public void accept(Long aLong) throws Exception {

                Log.i("lybj", aLong+"");
            }
        });
    }

    /**
     *  never()：不发送任何事件
     *  error()：发送 onError() 事件
     *  empty() ： 直接发送 onComplete() 事件
     * */
    private void  empty_never_error(){

        Observable.never().subscribe(new Observer(){
            @Override
            public void onSubscribe(Disposable d) {

                Log.i("lybj", "准备监听");
            }

            @Override
            public void onNext(Object o) {

                Log.i("lybj", o+"");
            }

            @Override
            public void onError(Throwable e) {

                Log.i("lybj", "onError");
            }

            @Override
            public void onComplete() {

                Log.i("lybj", "onComplete");
            }
        });
    }





    // ----------------------转换操作符--------------------
    /**
     * map 可以将被观察者发送的数据类型转变成其他的类型
     * */
    private void map(){

        Observable.just("中国", "祖国", "中国军人")
                .map(new Function<String, String>() {

                    @Override
                    public String apply(String s) throws Exception {

                        return "我爱" + s;
                    }
                })
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        Log.i("lybj", s);
                    }
                });
    }

    /**
     * flatMap：这个方法可以将事件序列中的元素进行整合加工，返回一个新的被观察者。
     * flatMap() 其实与 map() 类似，但是 flatMap() 返回的是一个 Observerable，
     * map()只是返回数据，如果在元素再加工的时候，想再使用上面的创建操作符的话，建议使用flatMap()，而非map()。
     * */
    private void flatMap(){

        Observable.just("中国", "祖国", "中国军人", "贪官")
                .flatMap(new Function<String, ObservableSource<String>>() {

                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {

                        if(s.equals("贪官")){

                            return Observable.error(new Exception("贪官不能被喜欢"));
                        }
                        return Observable.just("我爱"+s);
                    }
                })
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        Log.i("lybj", s);
                    }
                }, new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Log.i("lybj", throwable.getMessage());
                    }
                });
    }

    /**
     * concatMap(): 和 flatMap() 基本上是一样的，只不过 concatMap() 转发出来的事件是有序的，而 flatMap() 是无序的。
     * */
    private void concatMap(){

        Observable.just("中国", "祖国", "中国军人", "贪官")
                .concatMap(new Function<String, ObservableSource<String>>() {

                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {

                        if(s.equals("贪官")){

                            return Observable.error(new Exception("贪官不能被喜欢"));
                        }
                        return Observable.just("我爱"+s);
                    }
                })
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        Log.i("lybj", s);
                    }
                }, new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Log.i("lybj", throwable.getMessage());
                    }
                });
    }

    /**
     *  buffer(): 从需要发送的事件当中获取一定数量的事件，并将这些事件放到缓冲区当中一并发出。
     *  buffer 有两个参数，一个是 count，另一个 skip。count 缓冲区元素的数量，
     *  skip 就代表缓冲区满了之后，发送下一次事件序列的时候要跳过多少元素。
     * */
    private void buffer(){

        Observable.just("1", "2", "3", "4", "5")
                .buffer(2,1)
                .subscribe(new Consumer<List<String>>() {

                    @Override
                    public void accept(List<String> strings) throws Exception {

                        Log.i("lybj", "缓冲区大小： " + strings.size());
                        for (String s : strings){
                            Log.i("lybj",  s);
                        }
                    }
                });
    }

    /**
     * scan(): 遍历源Observable产生的结果，再按照自定义规则进行运算，依次输出每次计算后的结果给订阅者
     * */
    private void scan(){

        Observable.just(1, 2, 3, 4, 5)
                .scan(new BiFunction<Integer, Integer, Integer>() {

                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {

                        Log.i("lybj", "integer01: " + integer + " integer02: "+ integer2);
                        return 9;
                    }
                }).subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj", "accept: " + integer);
                    }
                });
    }

    /**
     * window(): 发送事件时，将这些事件分为按数量重新分组。window 中的 count 的参数就是代表指定的数量，
     * 例如将 count 指定为2，那么每发2个数据就会将这2个数据分成一组。
     * */
    private void window(){

        Observable.just("鲁班", "孙尚香", "亚索","火女","盖伦")
                .window(2)
                .subscribe(new Consumer<Observable<String>>() {

                    @Override
                    public void accept(Observable<String> stringObservable) throws Exception {

                        Log.i("lybj", "分组开始");
                        stringObservable.subscribe(new Consumer<String>() {

                            @Override
                            public void accept(String s) throws Exception {

                                Log.i("lybj", s);
                            }
                        });
                    }
                });
    }





    // ----------------------组合操作符--------------------
    /**
     * concat(): 可以将多个观察者组合在一起，然后按照之前发送顺序发送事件。
     * 需要注意的是，concat() 最多只可以发送4个事件。
     * */
    private void concat(){

        Observable.concat(
                Observable.just(1, 2, 3),
                Observable.just(4, 5),
                Observable.just(6, 7),
                Observable.just(8, 9))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj", integer+"");
                    }
                });
    }

    /**
     * concatArray() :  与 concat() 作用一样，不过 concatArray() 可以发送多于 4 个被观察者。
     * */
    private void concatArray(){

        Observable.concatArray(Observable.just(1, 2, 3, 4),
                Observable.just(5, 6),
                Observable.just(7, 8, 9, 10),
                Observable.just(11, 12, 13),
                Observable.just(14, 15),
                Observable.just(16))
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

                Log.i("lybj", integer+"");
            }
        });
    }

    /**
     *  merge(): 这个方法与 concat() 作用基本一样，但是 concat() 是串行发送事件，而 merge() 并行发送事件
     *  最多只能发送4个
     * */
    private void merge(){

        Observable.merge(Observable.just(1, 2, 3, 4),
                Observable.just(5, 6),
                Observable.just(7, 8, 9, 10),
                Observable.just(11, 12, 13))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj", integer+"");
                    }
                });
    }

    /**
     * zip(): 将多个数据源合并，并生成一个新的数据源。
     * 新生成的数据源严格按照合并前的数据源的数据发射顺序，
     * 并且新数据源的数据个数等于合并前发射数据个数最少的那个数据源的数据个数。
     * */
    private void zip(){

        Observable.zip(Observable.just(1, 2, 3),
                Observable.just("A", "B", "C", "D", "E"),
                new BiFunction<Integer, String, String>(){

                    @Override
                    public String apply(Integer o1, String o2) throws Exception {

                        return o1 +"_"+ o2;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String o) throws Exception {

                        Log.i("lybj", o);
                    }
                });
    }

    /**
     * startWithArray(): 在发送事件之前追加事件，
     * startWith() 追加一个事件，startWithArray() 可以追加多个事件。追加的事件会先发出。
     * */
    private void startWithArray(){

        Observable.just(1, 2, 3)
                .startWithArray(4, 5)
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj", integer+"");
                    }
                });
    }

    /**
     * count(): 返回被观察者发送事件的数量。
     * */
    private void count(){

        Observable.just(2,3,4,5,6)
                .count()
                .subscribe(new Consumer<Long>() {

                    @Override
                    public void accept(Long aLong) throws Exception {

                        Log.i("lybj", "事件数量：" + aLong);
                    }
                });
    }






    // ----------------------功能操作符--------------------
    /**
     * delay() : 延迟一段事件发送事件。
     * */
    private void delay(){

        Observable.just(1,2,3,4)
                .delay(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj",  integer+"");
                    }
                });
    }

    /**
     * doOnEach(): 每次发送事件之前都会回调这个方法
     * doOnNext(): Observable 每发送 onNext() 之前都会先回调这个方法。
     * doAfterNext(): Observable 每发送 onNext() 之后都会回调这个方法。
     * doOnComplete(): Observable 每发送 onComplete() 之前都会回调这个方法。
     * doOnError(): Observable 每发送 onError() 之前都会回调这个方法。
     * doOnSubscribe(): Observable 每发送 onSubscribe() 之前都会回调这个方法。
     * doOnDispose(): 当调用 Disposable 的 dispose() 之后回调该方法。
     * doOnLifecycle(): 在回调 onSubscribe 之前回调该方法的第一个参数的回调方法，可以使用该回调方法决定是否取消订阅。
     * doOnTerminate(): 在 onError 或者 onComplete 发送之前回调。
     * doAfterTerminate(): onError 或者 onComplete 发送之后回调。
     * doFinally(): 在所有事件发送完毕之后回调该方法。如果取消订阅之后 doAfterTerminate() 就不会被回调，
     *              而 doFinally() 无论怎么样都会被回调，且都会在事件序列的最后。
     * */
    private void doOnFunc(){

        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnEach(new Consumer<Notification<Integer>>() {

            @Override
            public void accept(Notification<Integer> integerNotification) throws Exception {

                Log.i("lybj",  "doOnEach 方法执行了, 结果："+ integerNotification.getValue());
            }
        }).doOnNext(new Consumer<Integer>() {

            @Override
            public void accept(Integer integer) throws Exception {

                Log.i("lybj",  "doOnNext 方法执行了, 结果："+ integer);
            }
        }).doAfterNext(new Consumer<Integer>() {

            @Override
            public void accept(Integer integer) throws Exception {

                Log.i("lybj",  "doAfterNext 方法执行了, 结果："+ integer);
            }
        }).doOnComplete(new Action() {

            @Override
            public void run() throws Exception {

                Log.i("lybj",  "doOnComplete 方法执行了");
            }
        }).doOnError(new Consumer<Throwable>() {

            @Override
            public void accept(Throwable throwable) throws Exception {

                Log.i("lybj",  "doOnError 方法执行了");
            }
        }).doOnSubscribe(new Consumer<Disposable>() {

            @Override
            public void accept(Disposable disposable) throws Exception {

                Log.i("lybj",  "doOnSubscribe 方法执行了");
            }
        }).doOnDispose(new Action() {

            @Override
            public void run() throws Exception {

                Log.i("lybj",  "doOnDispose 方法执行了");
            }
        }).doOnTerminate(new Action() {

            @Override
            public void run() throws Exception {

                Log.i("lybj",  "doOnTerminate 方法执行了");
            }
        }).doAfterTerminate(new Action() {

            @Override
            public void run() throws Exception {

                Log.i("lybj",  "doAfterTerminate 方法执行了");
            }
        }).doFinally(new Action() {

            @Override
            public void run() throws Exception {

                Log.i("lybj",  "doFinally 方法执行了");
            }
        }).subscribe(new Observer<Integer>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {

                disposable = d;
                Log.i("lybj", "------观察者onSubscribe()执行");
            }

            @Override
            public void onNext(Integer integer) {

                Log.i("lybj", "------观察者onNext()执行："+integer);
                if(integer == 2){
//                    disposable.dispose(); // 取消订阅
                }
            }

            @Override
            public void onError(Throwable e) {

                Log.i("lybj", "------观察者onError()执行");
            }

            @Override
            public void onComplete() {

                Log.i("lybj", "------观察者onComplete()执行");
            }
        });
    }

    /**
     * onErrorReturn(): 当接受到一个 onError() 事件之后回调，返回的值会回调 onNext() 方法，并正常结束该事件序列
     * */
    private void onErrorReturn(){

        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                emitter.onNext("小明：到");
                emitter.onError(new IllegalStateException("error"));
                emitter.onNext("小方：到");
            }
        }).onErrorReturn(new Function<Throwable, String>() {

            @Override
            public String apply(Throwable throwable) throws Exception {

                Log.i("lybj",  "小红请假了");
                return "小李：到";
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {

                Log.i("lybj",  s);
            }

            @Override
            public void onError(Throwable e) {

                Log.i("lybj",  e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * onErrorResumeNext(): 当接收到 onError() 事件时，返回一个新的 Observable，并正常结束事件序列。
     * */
    private void onErrorResumeNext(){

        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                emitter.onNext("小明");
                emitter.onNext("小方");
                emitter.onNext("小红");
                emitter.onError(new NullPointerException("error"));
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends String>>() {

            @Override
            public ObservableSource<? extends String> apply(Throwable throwable) throws Exception {

                return Observable.just("1", "2", "3");
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

                Log.i("lybj",  "准备监听");
            }

            @Override
            public void onNext(String s) {

                Log.i("lybj",  s);
            }

            @Override
            public void onError(Throwable e) {

                Log.i("lybj",  e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("lybj",  "onComplete");
            }
        });
    }

    /**
     * onExceptionResumeNext(): 与 onErrorResumeNext() 作用基本一致，但是这个方法只能捕捉 Exception。
     * */
    private void onExceptionResumeNext(){

        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                emitter.onNext("小明");
                emitter.onNext("小方");
                emitter.onNext("小红");
                emitter.onError(new Error("error"));
            }
        }).onExceptionResumeNext(new Observable<String>() {

            @Override
            protected void subscribeActual(Observer observer) {

                observer.onNext("小张");
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

                Log.i("lybj",  "准备监听");
            }

            @Override
            public void onNext(String s) {

                Log.i("lybj",  s);
            }

            @Override
            public void onError(Throwable e) {

                Log.i("lybj",  e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("lybj",  "onComplete");
            }
        });
    }

    /**
     * retry(): 如果出现错误事件，则会重新发送所有事件序列。times 是代表重新发的次数。
     * */
    private void retry(){

        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onError(new IllegalStateException());
            }
        }).retry(2)
          .subscribe(new Observer<String>() {

              @Override
              public void onSubscribe(Disposable d) {

                  Log.i("lybj",  "准备监听");
              }

              @Override
              public void onNext(String s) {

                  Log.i("lybj",  s);
              }

              @Override
              public void onError(Throwable e) {

                  Log.i("lybj",  e.getMessage());
              }

              @Override
              public void onComplete() {
                  Log.i("lybj",  "onComplete");
              }
          });
    }

    /**
     * retryUntil(): 出现错误事件之后，可以通过此方法判断是否继续发送事件。
     * */
    private void retryUntil(){

        Observable.create(new ObservableOnSubscribe<String>() {

            public void subscribe(@NonNull ObservableEmitter<String> emitter){

                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onError(new NullPointerException("error"));
                emitter.onNext("4");
                emitter.onNext("5");
            }
        }).retryUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Exception {

                Log.i("lybj",  "getAsBoolean");
                return true;
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {

                Log.i("lybj",  s);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * repeat(): 重复发送被观察者的事件，times 为发送次数。
     * */
    private void repeat(){

        Observable.just(1,2,3)
                .repeat(2)
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj",  integer+"");
                    }
                });
    }

    /**
     *  subscribeOn(): 指定被观察者的线程，如果多次调用此方法，只有第一次有效。
     *  observeOn(): 指定观察者的线程
     * */
    private void subscribeOn_observeOn(){

        Observable.create(new ObservableOnSubscribe<String>() {

            public void subscribe(@NonNull ObservableEmitter<String> emitter){

                emitter.onNext("1");
                Log.i("lybj",  Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(Schedulers.newThread())
          .subscribe(new Consumer<String>() {

               @Override
               public void accept(String s) throws Exception {

                   Log.i("lybj",  s);
                   Log.i("lybj",  Thread.currentThread().getName());
               }
          });
    }






    // ----------------------过滤操作符--------------------
    /**
     * filter(): 如果返回 true 则会发送事件，否则不会发送
     * */
    private void filter(){

        Observable.just(1,2,3,4,5)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {

                        if(integer > 4){
                            return true;
                        }
                        return false;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

                Log.i("lybj",  integer+"");
            }
        });
    }

    /**
     * ofType(): 可以过滤不符合该类型事件
     * */
    private void ofType(){

        Observable.just(1, 2, 3, "小明", "小方")
                .ofType(String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        Log.i("lybj",  s+"");
                    }
                });
    }

    /**
     *  skip(): 跳过正序某些事件，count 代表跳过事件的数量
     * */
    private void skip(){

        Observable.just(1,2,3,4,5,6,7)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj",  integer+"");
                    }
                });
    }

    /**
     * distinct(): 过滤事件序列中的重复事件。
     * */
    private void distinct(){

        Observable.just(1,2,3,1,4,1,2)
                .distinct()
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj",  integer+"");
                    }
                });
    }

    /**
     * distinctUntilChanged(): 过滤掉连续重复的事件
     * */
    private void distinctUntilChanged(){

        Observable.just(1,2,3,3,1,5,6)
        .distinctUntilChanged()
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

                Log.i("lybj",  integer+"");
            }
        });
    }

    /**
     * take(): 控制观察者接收的事件的数量。
     * */
    private void take(){

        Observable.just(1,2,3,4,5,6)
                .take(3)
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj",  integer+"");
                    }
                });
    }

    /**
     * debounce(): 如果两件事件发送的时间间隔小于设定的时间间隔则前一件事件就不会发送给观察者。
     * */
    private void debounce(){

        Observable.just(1,2,3,4,5)
                .map(new Function<Integer, Integer>() {

                    @Override
                    public Integer apply(Integer integer) throws Exception {

                        Thread.sleep(900);
                        return integer;
                    }
                })
                .debounce(1,TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj",  integer+"");
                    }
                });
    }

    /**
     *  firstElement():  取事件序列的第一个元素。
     *  lastElement(): 取事件序列的最后一个元素。
     *  elementAt(): 以指定取出事件序列中事件，但是输入的 index 超出事件序列的总数的话就不会出现任何结果。
     */
    private void firstElement_lastElement_elementAt(){

        Observable.just(1,2,3,4)
                .firstElement()
                .subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj",  integer+"");
                    }
                });
    }






    // ----------------------条件操作符--------------------
    /**
     * all(): 判断事件序列是否全部满足某个事件，如果都满足则返回 true，反之则返回 false。
     * */
    private void all(){

        Observable.just(1, 2, 3, 4, 5)
                .all(new Predicate<Integer>() {

                    @Override
                    public boolean test(Integer integer) throws Exception {

                        return integer <= 4;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {

                Log.i("lybj",  aBoolean+"");
            }
        });
    }

    /**
     * takeWhile(): 从左边开始，将满足条件的元素取出来，直到遇到第一个不满足条件的元素，则终止
     * takeUntil(): 从左边开始，将满足条件的元素取出来，直到遇到第一个满足条件的元素，则终止
     * filter(): 是将所有满足条件的数据都取出。
     * */
    private void takeWhile(){

        Observable.just(1, 2, 3, 4, 5)
                .takeWhile(new Predicate<Integer>() {

                    @Override
                    public boolean test(Integer integer) throws Exception {

                        return integer < 3;
                    }
                }).subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj",  integer+"");
                    }
                });
    }

    /**
     * skipWhile(): 从左边开始，根据条件跳过元素
     * */
    private void skipWhile(){

        Observable.just(1,2,3,4,5,3,2,1,7)
                .skipWhile(new Predicate<Integer>() {

                    @Override
                    public boolean test(Integer integer) throws Exception {

                        return integer < 3;
                    }
                }).subscribe(new Consumer<Integer>() {

                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("lybj",  integer+"");
                    }
                });
    }

    /**
     * isEmpty(): 判断事件序列是否为空。
     * */
    private void isEmpty(){

        Observable.create(new ObservableOnSubscribe<String>() {

            public void subscribe(@NonNull ObservableEmitter<String> emitter){

                emitter.onComplete();
            }
        }).isEmpty()
          .subscribe(new Consumer<Boolean>() {

              @Override
              public void accept(Boolean aBoolean) throws Exception {

                  Log.i("lybj",  aBoolean+"");
              }
          });
    }

    /**
     * contains(): 判断事件序列中是否含有某个元素，如果有则返回 true，如果没有则返回 false
     * */
    private void contains(){

        Observable.just(1,2,3,4,5,6)
                .contains(2)
                .subscribe(new Consumer<Boolean>() {

                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        Log.i("lybj",  aBoolean+"");
                    }
                });
    }

    /**
     * sequenceEqual(): 判断两个 Observable 发送的事件是否相同。
     * */
    private void sequenceEqual(){

        Observable.sequenceEqual(Observable.just("小明", "小方", "小李"),
                Observable.just("小明", "小方", "小李", "小张"))
                .subscribe(new Consumer<Boolean>() {

                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        Log.i("lybj",  aBoolean+"");
                    }
                });
    }
}