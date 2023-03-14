package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.List.of;

@Service
public class BookReturnCalculationService {

    @Autowired
    private NowService nowService;

    private static final List<DayOfWeek> INVALID_DAYS = of(SATURDAY, SUNDAY);

    @Value("{api.devolution.days}")
    private Long standardReturnPeriod;

    public LocalDate calculateReturn() {

        LocalDate today = nowService.getDate();

        LocalDate devolutionDate = today.plusDays(standardReturnPeriod);

        while (INVALID_DAYS.contains(devolutionDate.getDayOfWeek())) {
            devolutionDate = devolutionDate.plusDays(1);
        }

        return devolutionDate;
    }
}
