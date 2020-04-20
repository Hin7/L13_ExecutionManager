package ru.sbt.course.ExecutionManager;

/**
 * Context - интерфейс для получения информации о выполнении задач в пуле потоков.
 * Задание 2 урока 13 СБТ (Java memory model).
 *
 * @author Hin
 * @version 1.0 17/04/2020
 *
 */

public interface Context {
    /**
     * @return - количество тасков, которые на текущий момент успешно выполнились.
     */
    int getCompletedTaskCount();

    /**
     * @return - количество тасков, при выполнении которых произошел Exception
     */
    int getFailedTaskCount();

    /**
     * @return - количество тасков, которые не были выполены из-за отмены (вызовом interrupt)
     */
    int getInterruptedTaskCount();

    /**
     * отменяет выполнения тасков, которые еще не начали выполняться
     */
    void interrupt();

    /**
     * @return - true, если все таски были выполнены или отменены, false в противном случае
     */
    boolean isFinished();

}
