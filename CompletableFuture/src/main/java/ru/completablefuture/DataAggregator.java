package ru.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class DataAggregator {
    private final Random random;

    public DataAggregator() {
        random = new Random();
    }

    public ProductInfo aggregateProductInfo(String productName) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> cfPrice = CompletableFuture.supplyAsync(this::fetchPrice).exceptionally(ex -> 0d);
        CompletableFuture<String> cfDescription = CompletableFuture.supplyAsync(this::fetchDescription).exceptionally(Throwable::getMessage);
        CompletableFuture<Double> cfRating = CompletableFuture.supplyAsync(this::fetchRating).exceptionally(ex -> 0d);

        CompletableFuture.allOf(cfPrice, cfDescription, cfRating).join();

        return new ProductInfo(productName, cfPrice.get(), cfDescription.get(), cfRating.get());
    }

    private Double fetchPrice() {
        simulateDelay();

        if(random.nextInt(100) < 20) {
            throw new DataNotFound("Price is not fount");
        }

        return random.nextDouble(1000);
    }

    private String fetchDescription() {
        simulateDelay();

        if(random.nextInt(100) < 20) {
            throw new DataNotFound("Description is not fount");
        }

        return "The best product in the world!";
    }

    private Double fetchRating() {
        simulateDelay();

        if(random.nextInt(100) < 20) {
            throw new DataNotFound("Rating is not fount");
        }

        return random.nextDouble(10);
    }

    private void simulateDelay() {
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }
}
