package ru.sbt.course.ExecutionManager;

public class ExecutionManagerImp implements ExecutionManager {

    private FixedThreadPool fixedThreadPool;

    public ExecutionManagerImp() {
        fixedThreadPool = new FixedThreadPool(10);
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        fixedThreadPool.execute(callback, tasks);
        fixedThreadPool.start();
        return contextObj;
    }

    Context contextObj = new Context() {
        @Override
        public int getCompletedTaskCount() {
            return fixedThreadPool.getCompletedTaskCount();
        }

        @Override
        public int getFailedTaskCount() {
            return fixedThreadPool.getFiledTaskCount();
        }

        @Override
        public int getInterruptedTaskCount() {
            return isFinished() ? fixedThreadPool.getTaskQueueSize() : 0;
        }

        @Override
        public void interrupt() {
            fixedThreadPool.stop_threads();
        }

        @Override
        public boolean isFinished() {
            return fixedThreadPool.isFinished();
        }
    };
}
