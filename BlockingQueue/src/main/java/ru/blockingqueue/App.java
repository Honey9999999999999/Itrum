package ru.blockingqueue;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(5);
        Random random = new Random();

        Thread a = new Thread(() -> {
            try {
                while (true){
                    queue.enqueue(random.nextInt());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread d = new Thread(() -> {
            try {
                while (true){
                    queue.enqueue(random.nextInt());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread b = new Thread(() -> {
            try {
                while (true){
                    System.out.println(queue.size() + " | " + queue.dequeue());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread c = new Thread(() -> {
            try {
                while (true){
                    System.out.println(queue.size() + " | " + queue.dequeue());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        //c.start();
        b.start();
        a.start();
        d.start();
    }
}
