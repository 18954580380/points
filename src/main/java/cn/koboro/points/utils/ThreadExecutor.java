package cn.koboro.points.utils;

import java.util.concurrent.*;

/**
 * 任务线程池
 *
 * @author xdw
 */
public class ThreadExecutor {
    private static final int CORE_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_SECONDS = 3000;
    private static final int MAX_POOL_SIZE = 20;

    private static ThreadPoolExecutor executor;

    private static ExecutorService getExecutor() {
        if (executor == null || executor.isShutdown()) {
            synchronized (ThreadFactory.class) {
                if (executor == null || executor.isShutdown()) {
                    executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_SECONDS,
                            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20));
                }
            }
        }
        return executor;
    }

    public static <T extends Runnable> void execute(T task) {
        getExecutor().execute(task);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return getExecutor().submit(task);
    }

    public static <T extends Runnable> Future submit(T task) {
        return getExecutor().submit(task);
    }

}
