package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.SimpleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookReturnCalculationServiceTest {

    @InjectMocks
    private BookReturnCalculationService tested;

    @Mock
    private NowService nowService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Must calculate return period correctly on monday!")
    void mustCalculateCorrectlyOnMonday() {

        LocalDate today = LocalDate.now().with(previousOrSame(MONDAY));
        LocalDate devolutionDate = SimpleFactory.getDevolutionDate(today);

        when(nowService.getDate()).thenReturn(today);

        LocalDate response = tested.calculateReturn();

        verify(nowService).getDate();

        assertEquals(devolutionDate, response);
    }

    @Test
    @DisplayName("Must calculate return period correctly on tuesday!")
    void mustCalculateCorrectlyOnTuesday() {

        LocalDate today = LocalDate.now().with(previousOrSame(TUESDAY));
        LocalDate devolutionDate = SimpleFactory.getDevolutionDate(today);

        when(nowService.getDate()).thenReturn(today);

        LocalDate response = tested.calculateReturn();

        verify(nowService).getDate();

        assertEquals(devolutionDate, response);
    }

    @Test
    @DisplayName("Must calculate return period correctly on wednesday!")
    void mustCalculateCorrectlyOnWednesday() {

        LocalDate today = LocalDate.now().with(previousOrSame(WEDNESDAY));
        LocalDate devolutionDate = SimpleFactory.getDevolutionDate(today);

        when(nowService.getDate()).thenReturn(today);

        LocalDate response = tested.calculateReturn();

        verify(nowService).getDate();

        assertEquals(devolutionDate, response);
    }

    @Test
    @DisplayName("Must calculate return period correctly on thursday!")
    void mustCalculateCorrectlyOnThursday() {

        LocalDate today = LocalDate.now().with(previousOrSame(THURSDAY));
        LocalDate devolutionDate = SimpleFactory.getDevolutionDate(today);

        when(nowService.getDate()).thenReturn(today);

        LocalDate response = tested.calculateReturn();

        verify(nowService).getDate();

        assertEquals(devolutionDate, response);
    }

    @Test
    @DisplayName("Must calculate return period correctly on friday!")
    void mustCalculateCorrectlyOnFriday() {

        LocalDate today = LocalDate.now().with(previousOrSame(FRIDAY));
        LocalDate devolutionDate = SimpleFactory.getDevolutionDate(today);

        when(nowService.getDate()).thenReturn(today);

        LocalDate response = tested.calculateReturn();

        verify(nowService).getDate();

        assertEquals(devolutionDate, response);
    }
}

