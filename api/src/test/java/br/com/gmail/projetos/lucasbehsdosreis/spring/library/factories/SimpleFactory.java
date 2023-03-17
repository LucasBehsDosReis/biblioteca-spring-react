package br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.List.of;

public class SimpleFactory {

    private static final List<DayOfWeek> INVALID_DAYS = of(SATURDAY, SUNDAY);

    public static Long getRandomLong() {
        return new Random().nextLong();
    }

    public static LocalDate getNowDate() {
        return LocalDate.now();
    }

    public static LocalDate getDevolutionDate(LocalDate today) {

        LocalDate devolutionDate = today.plusDays(5);

        while (INVALID_DAYS.contains(devolutionDate.getDayOfWeek())) {
            devolutionDate = devolutionDate.plusDays(1);
        }

        return devolutionDate;
    }
}
