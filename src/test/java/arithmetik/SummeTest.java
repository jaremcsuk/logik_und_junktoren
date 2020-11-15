package arithmetik;

import static arithmetik.Summe.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


/*
Die klasse Summe funktionierte nicht ganz korrekt.
(1) Wenn long maximale oder minimale Werte überschreitet, dann gibt die Funktion die falsche Summe zurück.

Gefixt: in diesem Fall throws das Programm exception

(2) Wenn Paramer summande ist NULL, bricht das Programm ab.

Gefixt: in diesem Fall throws das Programm exception

(3) Wenn ein Objekt der Liste summande ist NULL, bricht das Programm auch ab.

Gefixt: in diesem Fall throws das Programm exception
 */

public class SummeTest {

    @Test
    @DisplayName("Summe: summiere nur positive Zahlen")
    void test_only_positive_numbers(){
        List<Long> summanden = new ArrayList<>();
        Long x1 = new Long (100L);
        summanden.add(x1);
        Long x2 = new Long (10L);
        summanden.add(x2);
        Long x3 = new Long(1L);
        summanden.add(x3);

        Long result = summe(summanden);

        assertThat(result).isEqualTo(111L);
    }
    @Test
    @DisplayName("Summe: summiere nur negative Zahlen")
    void test_only_negativ_numbers(){
        List<Long> summanden = new ArrayList<>();
        Long x1 = new Long (-100L);
        summanden.add(x1);
        Long x2 = new Long (-10L);
        summanden.add(x2);
        Long x3 = new Long(-1L);
        summanden.add(x3);

        Long result = summe(summanden);

        assertThat(result).isEqualTo(-111L);
    }

    @Test
    @DisplayName("Summe: summiere negative und positive Zahlen")
    void test_negativ_and_positiv_numbers(){

        List<Long> summanden = new ArrayList<>();
        for (long i = 1; i<=10; i++) {
            Long j = new Long(i*10);
            if(i % 2 == 1) {
                j=j* (-1);
            }
            summanden.add(j);
        }

        long result = summe(summanden);
        assertThat(result).isEqualTo(50);
    }

    @Test
    @DisplayName("Summe: the maximum long value was exceeded and  throws exception")
    void test_max_long_values(){
        List<Long> summanden = new ArrayList<>();
        for (long i = 0; i<10; i++) {
            Long j = new Long(Long.MAX_VALUE-i);
            summanden.add(j);
        }

        Executable code = new Executable() {
            public void execute() throws Throwable {
                summe(summanden);
            }
        };
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, code);
        assertThat(exception.getMessage()).contains("long");
    }


    @Test
    @DisplayName("Summe: the minimum long value was exceeded and throws exception")
    void test_min_long_values(){
        List<Long> summanden = new ArrayList<>();
        for (long i = 0; i<10; i++) {
            Long j = new Long(Long.MIN_VALUE+i);
            summanden.add(j);
        }

        Executable code = new Executable() {
            public void execute() throws Throwable {
                summe(summanden);
            }
        };
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, code);
        assertThat(exception.getMessage()).contains("long");
    }

    @Test
    @DisplayName("Summe: alle Werte sind Nullen")
    void test_all_zeros_values(){

        List<Long> summanden = new ArrayList<>();
        for (long i = 0; i<100; i++) {
            summanden.add(0L);
        }
        long result = summe(summanden);
        assertThat(result).isEqualTo(0L);
    }

    @Test
    @DisplayName("Summe: einige Werte sind Nullen")
    void test_values_with_zeros() {
        List<Long> summanden = new ArrayList<>();
        for (long i = 1; i <= 10; i++) {
            long j = i;
            if (i % 2 == 1) {
                j = j * 0;
            }
            summanden.add(j);
        }
        long result = summe(summanden);
        assertThat(result).isEqualTo(30L);
    }

    @Test
    @DisplayName("Parameter darf nicht Null sein und throws exception")
    void test_parameter_zeigt_auf_null() {
        List<Long> summanden = null;

        Executable code = new Executable() {
            public void execute() throws Throwable {
                Summe.summe(summanden);
            }
        };
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, code);
        assertThat(exception.getMessage()).contains("null");
    }

    @Test
    @DisplayName("Element der Liste des Parameters darf nicht Null sein und throws exception")
    void test_parameter_listElement_zeigt_auf_null() {
        List<Long> summanden = new ArrayList<>();
        Long x1 = new Long(1);
        Long x2 = null;
        summanden.add(x1);
        summanden.add(x2);

        Executable code = new Executable() {
            public void execute() throws Throwable {
                Summe.summe(summanden);
            }
        };
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, code);
        assertThat(exception.getMessage()).contains("null");
    }


    @Test
    @DisplayName("Summe: ein LinkedList wird als Parameter übergeben")
    void test_linkedList() {
        List<Long> summanden = new LinkedList<>();
        for (long i = 1; i <= 10; i++) {
            long j = i;
            if (i % 2 == 1) {
                j = j * 0;
            }
            summanden.add(j);
        }
        long result = summe(summanden);
        assertThat(result).isEqualTo(30L);
    }

    @Test
    @DisplayName("Summe: ein Stack wird als Parameter übergeben")
    void test_Stack() {
        List<Long> summanden = new Stack<>();
        for (long i = 1; i <= 10; i++) {
            long j = i;
            if (i % 2 == 1) {
                j = j * 0;
            }
            summanden.add(j);
        }
        long result = summe(summanden);
        assertThat(result).isEqualTo(30L);
    }
}
