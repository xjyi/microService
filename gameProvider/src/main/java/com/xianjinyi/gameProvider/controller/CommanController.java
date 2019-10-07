package com.hzed.simulate.controller;


import com.alibaba.fastjson.JSON;
import com.hzed.simulate.command.MySeriveCommand;
import com.hzed.simulate.command.MySeriveObserveCommand;
import com.hzed.simulate.vo.ApiResponse;
import com.hzed.simulate.vo.AutoSignRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;


/**
 * command 调用四种方式
 *
 * 1. execute()
 *      仅对HystrixCommand生效
 *      同步，调用后直接block住，直至返回结果
 *
 *      底层其实也是依赖observe()去执行
 *
 *
 *      另外：
 *      new MySeriveObserveCommand(new Long[]{1L,2L}).toBlocking().toFuture().get()
 *      如果认为ObserveCommand只会返回一条i数据，使用次模式，将同步执行返回一条数据（少用）
 *
 *
 * 2. queue()
 *      仅对HystrixCommand生效
 *      异步
 *      仅仅将commang放进线程池的一个等待队列，就立即返回 拿到一个Future对象，可以先处理其他事情，需要拿的时候，调用get（阻塞获取）
 *
 *      另外：
 *      也是拿一条数据（少用）异步
 *      new MySeriveObserveCommand(new Long[]{1L,2L}).toBlocking().toFuture()
 *
 *
 * 3.observe()
 *      订阅一个Observable对象，Observable代表的是依赖服务返回的结果，是代表结果的Observable对象的拷贝结果
 *      立即执行
 *
 * 4.toObservable()
 *      也是订阅，返回个Observable对象
 *      延迟执行不会立即执行 在subscribe订阅的时候才执行
 *
 */
@RestController
@RequestMapping("/command")
@Slf4j
public class CommanController {


    @RequestMapping("/command")
    public AutoSignRequest tCommand (){
        MySeriveCommand mySeriveCommand = new MySeriveCommand(1L);
        AutoSignRequest execute = mySeriveCommand.execute();

        return execute;
    }


    @RequestMapping("/obCommand")
    public AutoSignRequest obCommand (){
        MySeriveObserveCommand mySeriveCommand = new MySeriveObserveCommand(new Long[]{1L,2L});
        Observable<AutoSignRequest> observe = mySeriveCommand.observe();
         observe.subscribe(new Observer<AutoSignRequest>() {
            @Override
            public void onCompleted() {
                System.out.println("全部完成");
            }

            @Override
            public void onError(Throwable throwable) {
                log.error(throwable.getMessage());
            }

            @Override
            public void onNext(AutoSignRequest autoSignRequest) {
                System.out.println(JSON.toJSONString(autoSignRequest));
            }
        });
        return new AutoSignRequest();
    }
}
