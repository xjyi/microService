package com.hzed.simulate.command;

import com.hzed.simulate.vo.AutoSignRequest;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MySeriveObserveCommand extends HystrixObservableCommand<AutoSignRequest> {

    private Long[] pids;

    public MySeriveObserveCommand( Long[] pid) {
        super(HystrixCommandGroupKey.Factory.asKey("MySeriveObserveCommand"));
        this.pids = pid;
    }

    @Override
    protected Observable<AutoSignRequest> construct() {
       return Observable.create(new Observable.OnSubscribe<AutoSignRequest>(){
           @Override
           public void call(Subscriber<? super AutoSignRequest> observer) {
                try{
                    //if (observer.isUnsubscribed()){

                        for (Long pid:pids) {
                            // get reslut by pid
                            AutoSignRequest req= new AutoSignRequest();
                            observer.onNext(req);
                        }
                        observer.onCompleted();

                    //}



                }catch (Exception e){
                    observer.onError(e);
                }
           }
       }).subscribeOn(Schedulers.io());
    }


}
