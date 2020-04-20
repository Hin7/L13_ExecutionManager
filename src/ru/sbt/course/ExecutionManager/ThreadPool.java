/**
 * Интерфейс пула потоков.
 *
 * @author: HIN7
 * @version: 1.1 09/04/2020
 */

package ru.sbt.course.ExecutionManager;

public interface ThreadPool {
    void start(); // запускает потоки
    void execute(Runnable finalTask, Runnable... task); //добавляет задачи в очередь
    void stop_threads(); //останавливает потоки с ожиданием
}
