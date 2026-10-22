package ru.completablefuture;

import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        DataAggregator dataAggregator = new DataAggregator();

        System.out.println(dataAggregator.aggregateProductInfo("Hello World!"));
    }
}
