package ru.frequencyanalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Integer[] values = new Integer[100];
        Random random = new Random();

        for (int i = 0; i < values.length; i++){
            values[i] = random.nextInt(0, 10);
        }

        Map<Integer, Integer> valuesMap = analyse(values);

        int maxValue = valuesMap.values().stream().max(Integer::compareTo).orElse(1);
        for (Map.Entry<Integer, Integer> entry : valuesMap.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .toList()) {
            System.out.printf("%-20s [%-" + maxValue + "s]\n", "Значение: " + entry.getKey(), "/".repeat(entry.getValue()));
        }
    }

    private static <T> Map<T, Integer> analyse(T[] array){
        Map<T, Integer> result = new HashMap<>();

        for (T value : array){
            result.merge(value, 1, (v, x) -> v + 1);
        }

        return result;
    }
}
