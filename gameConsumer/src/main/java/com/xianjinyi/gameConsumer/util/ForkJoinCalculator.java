package com.xianjinyi.gameConsumer.util;

import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;

/**
 * @author: xianjinyi
 * @date 2020/03/23
 */

public class ForkJoinCalculator implements Calculator {
    private ForkJoinPool pool;

    private static class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // 当需要计算的数字小于6时，直接计算结果
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
                // 否则，把任务一分为二，递归计算
            } else {
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle+1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }

    public ForkJoinCalculator() {
        // 也可以使用公用的 ForkJoinPool：
        // pool = ForkJoinPool.commonPool()
        pool = new ForkJoinPool();


    }

    @Override
    public long sumUp(long[] numbers) {

        LinkedList<Object> list = new LinkedList<>();
        Object pop = list.pop();
        Object poll = list.poll();
        return pool.invoke(new SumTask(numbers, 0, numbers.length-1));
    }

//    private int doJoin() {
//        int s; Thread t; ForkJoinWorkerThread wt; ForkJoinPool.WorkQueue w;
//
//        if ((s = status) < 0){
//            return s;
//        }
//        if (((t = Thread.currentThread()) instanceof ForkJoinWorkerThread)){
//            if((w = (wt = (ForkJoinWorkerThread)t).workQueue).tryUnpush(this) && (s = doExec()) < 0 ){
//                return s;
//            }else{
//                return wt.pool.awaitJoin(w, this, 0L);
//            }
//        }else{
//            return externalAwaitDone();
//        }
//
//    }

//    private int doJoin2() {
//        int s; Thread t; ForkJoinWorkerThread wt; ForkJoinPool.WorkQueue w;
//        return (s = status) < 0 ? s :
//                ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) ?
//                        (w = (wt = (ForkJoinWorkerThread)t).workQueue).
//                                tryUnpush(this) && (s = doExec()) < 0 ? s :
//                                wt.pool.awaitJoin(w, this, 0L) :
//                        externalAwaitDone();
//    }
}

