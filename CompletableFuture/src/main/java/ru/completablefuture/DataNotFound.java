package ru.completablefuture;

public class DataNotFound extends RuntimeException {
    public DataNotFound(String message) {
        super(message);
    }
}
