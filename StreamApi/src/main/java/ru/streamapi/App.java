package ru.streamapi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public record WorkSheet(String name, List<DayOfWeek> workDays) {
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        var denSheet = new WorkSheet("Денис", List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY));
        var benSheet = new WorkSheet("Бен", List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY));
        var lisSheet = new WorkSheet("Лиза", List.of(DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        var sheets = List.of(denSheet, benSheet, lisSheet);
        // TODO: Необходимо вывести на экран дни в которые работало более 1 человека
        // sheets.stream()
        // .forEach(System.out::println);

        sheets.stream()
                .flatMap(s -> s.workDays.stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                        .entrySet().stream()
                        .filter(e -> e.getValue() > 1)
                        .map(Map.Entry::getKey)
                        .forEach(System.out::println);
    }
}
