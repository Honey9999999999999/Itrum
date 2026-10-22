package ru.blockingqueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyBlockingQueue<T> {
    private final Deque<T> deque;
    private final int maxCapacity;

    public MyBlockingQueue(int maxCapacity){
        this.maxCapacity = maxCapacity;
        deque = new ArrayDeque<>(maxCapacity);
    }

    public synchronized void enqueue(T element) throws InterruptedException {
        while(deque.size() >= maxCapacity){
            wait();
        }

        deque.addFirst(element);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (deque.size() <= 0){
            wait();
        }

        notifyAll();
        return deque.pollLast();
    }

    public int size() { return deque.size(); }
}
