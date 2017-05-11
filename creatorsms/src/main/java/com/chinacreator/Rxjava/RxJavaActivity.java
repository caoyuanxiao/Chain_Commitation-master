/*
package com.chinacreator.Rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chinacreator.R;
import com.chinacreator.bean.Student;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

*/
/**
 * Created by Smile on 2017/5/1.
 *//*


public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaActivity";

    ImageView mImageView, mImageView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        mImageView = (ImageView) findViewById(R.id.image);
        mImageView1 = (ImageView) findViewById(R.id.image1);

        findViewById(R.id.getDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //from最后执行了3次onnext();  先执行一次from中的onnex() 在执行一次订阅中的onnext()
//                String[] mStr = new String[]{"我", "是", "元宵"};
//                //在onsubscribe方法执行的过程中在本线程的IO线程中执行 onnext()  观察者对象生成的对象执行的call在主线程中
//                Observable.from(mStr).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println(s);
//                    }
//                });



                //为社么需要返回值 因为需要进行对象的转换
                //onsubscribe有一次修改运行线程的机会 后面的如果不更改线程的话就会在onsubscribe修改的线程中执行

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String[] mStrs = new String[]{"1", "2"};
//
//
//                        Observable.from(mStrs).map(new Func1<String, String>() {
//                            @Override
//                            public String call(String s) {
//                                // mImageView1.setImageResource(R.mipmap.ic_launcher);
//                                return s;
//                            }
//                        })*/
/*.subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
//                            @Override
//                            public void call() {
//                                Toast.makeText(RxJavaActivity.this, "onStart", 0).show();
//                            }
//                        })*//*
.subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(String s) {
//                                Toast.makeText(RxJavaActivity.this, "onNext", 0).show();
//                            }
//
//                            @Override
//                            public void onStart() {
//
//
//                            }
//                        });
//
//                    }
//                }).start();




                */
/*Observable<Observable<String>> map = Observable.from(mStudents).map(new Func1<Student, Observable<String>>() {
                    @Override
                    public Observable<String> call(Student student) {
                        return Observable.from(student.getCursors());
                    }
                });*//*


                final List<Student> mStudents = new ArrayList<Student>();
                mStudents.add(new Student("元宵", new Student.Curosr[]{new Student.Curosr("数学", new String[]{"小哥", "校歌"}), new Student.Curosr("语文", new String[]{"小哥11", "校歌11"})}));
                mStudents.add(new Student("元磊", new Student.Curosr[]{new Student.Curosr("英语", new String[]{"小哥22", "校歌22"}), new Student.Curosr("生物", new String[]{"小哥33", "校歌33"})}));
                //如果不想使用for循环来输出，其实就是多执行一次from
                // 那就需要返回的对象为Observable对象才可以使用使用from

               */
/* public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> func) {
                    if (getClass() == ScalarSynchronousObservable.class) {
                        return ((ScalarSynchronousObservable<T>)this).scalarFlatMap(func);
                    }
                    return merge(map(func));
                }*//*


                //第一个from是将这里的mstudents这个数组事件 消费成单个的student事件
                //在onnext中再次将student中的cursor事件消费成单个的String事件执行

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Observable.from(mStudents).flatMap(new Func1<Student, Observable<Student.Curosr>>() {

                            @Override
                            public Observable<Student.Curosr> call(Student student) {
                                Toast.makeText(RxJavaActivity.this, "flatMap11111", 0).show();
                                return Observable.from(student.getCursors());
                            }
                        }).subscribeOn(AndroidSchedulers.mainThread()).flatMap(new Func1<Student.Curosr, Observable<String>>() {
                            @Override
                            public Observable<String> call(Student.Curosr curosr) {
                                Toast.makeText(RxJavaActivity.this, "flatMap2222", 0).show();
                                return Observable.from(curosr.getTeacher());
                            }
                        }).subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                Toast.makeText(RxJavaActivity.this, "call", 0).show();
                                Log.i(TAG, "call: tearch:" + s);
                            }
                        });
                    }
                }).start();


                Observable.from(mStudents).map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return null;
                    }
                });
                Observable.just("www").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        });


    }

    public void SchedulersChange() {
        //在创建被观察者的过程中将

        new Thread(new Runnable() {
            @Override
            public void run() {
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        //在io线程中执行代码
                        subscriber.onNext("你好");

                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
                    @Override
                    public void onStart() {
                        System.out.println("开始");
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("结束");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        //在主线程中执行 更新ui
                        System.out.println("onNext：" + s);
                        mImageView.setImageResource(R.mipmap.ic_launcher);
                    }
                });
            }
        }).start();


        Observable.just(1).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer + 10;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i(TAG, "call: " + integer);
            }
        });

        //1 需要输出每个学生对应的名字  其实就是将Student对象在map中装换成String

        Observable.from(new ArrayList<Student>()).map(new Func1<Student, String>() {
            @Override
            public String call(Student student) {
                return student.getName();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String strings) {
                Log.i(TAG, "call: name" + strings);
            }
        });


        List<Student> mStudents = new ArrayList<Student>();
        mStudents.add(new Student("元宵", new Student.Curosr[]{new Student.Curosr("数学", new String[]{"小哥", "校歌"}), new Student.Curosr("语文", new String[]{"小哥11", "校歌11"})}));
        mStudents.add(new Student("元磊", new Student.Curosr[]{new Student.Curosr("英语", new String[]{"小哥22", "校歌22"}), new Student.Curosr("生物", new String[]{"小哥33", "校歌33"})}));
        //如果不想使用for循环来输出，其实就是多执行一次from
        // 那就需要返回的对象为Observable对象才可以使用使用from

               */
/* public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> func) {
                    if (getClass() == ScalarSynchronousObservable.class) {
                        return ((ScalarSynchronousObservable<T>)this).scalarFlatMap(func);
                    }
                    return merge(map(func));
                }*//*


        //第一个from是将这里的mstudents这个数组事件 消费成单个的student事件
        //在onnext中再次将student中的cursor事件消费成单个的String事件执行

        Observable.from(mStudents).flatMap(new Func1<Student, Observable<Student.Curosr>>() {

            @Override
            public Observable<Student.Curosr> call(Student student) {
                return Observable.from(student.getCursors());
            }
        }).flatMap(new Func1<Student.Curosr, Observable<String>>() {
            @Override
            public Observable<String> call(Student.Curosr curosr) {
                return Observable.from(curosr.getTeacher());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "call: tearch:" + s);
            }
        });

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });


    }

    public void creatObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }

        };


        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }


        };

        Observable<String> observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });
        //创建被观察者 //观察者模式 被观察者调用了观察者中的方法 传递数据  里面需要一个Onsubscribe对象
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("哈哈");
                subscriber.onNext("你好");
                subscriber.onNext("曹元宵");
                subscriber.onCompleted();
            }
        });

        //查看源码可以发现其实代码只是创建一个子类继承自Observable
        //当里面只有一个参数的时候，创建ScalarSynchronousObservable对象继承自Observable 初始化时候执行了onnext()和oncomplete对象一次
        //当里面的参数大于一个的时候，利用from进行输出
        Observable.just("你好", "元宵");

        //这里源码看懂 Prosuce 原理都是Onsubscribe接口的使用 以及call方法中返回观察者 Subscribe对象
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable.from(words);

        observable.subscribe(subscriber);
        Subscription subscribe = observable.subscribe(observer);

        //源码  最后都是转换成Subscriber中去实现
//        // new Subscriber so onStart it
//        subscriber.onStart();
//
//        */
/*
//         * See https://github.com/ReactiveX/RxJava/issues/216 for discussion on "Guideline 6.4: Protect calls
//         * to user code from within an Observer"
//         *//*

//        // if not already wrapped
//        if (!(subscriber instanceof SafeSubscriber)) {
//            // assign to `observer` so we return the protected version
//            subscriber = new SafeSubscriber<T>(subscriber);
//        }
//
//        // The code below is exactly the same an unsafeSubscribe but not used because it would add a sigificent depth to alreay huge call stacks.
//        try {
//            // allow the hook to intercept and/or decorate
//            hook.onSubscribeStart(observable, observable.onSubscribe).call(subscriber);
//            return hook.onSubscribeReturn(subscriber);


        Action1<String> onNextAction = new Action1<String>() {
            //onnext()
            @Override
            public void call(String s) {
                Log.i(TAG, "call: ");
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            //onError()
            @Override
            public void call(Throwable s) {

            }
        };
        Action0 onCompleteAction = new Action0() {
            //oncomplete
            @Override
            public void call() {

            }
        };

        //和上面的订阅方法一样 创建subscribe对象 并且会在onnext()方法中直接调用 onNextAction.next()
        observable.subscribe(onNextAction);
        //自动创建observable和会在onnext()和onError()中
        observable.subscribe(onNextAction, onErrorAction);
        //同上可得
        observable.subscribe(onNextAction, onErrorAction, onCompleteAction);

    }
}
*/
