package arithmetik;

import org.junit.jupiter.api.*;

import static arithmetik.Mittelwert.*;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;


/*
Die Klasse Mittelwert funktionierte nicht ganz korrekt.
(1) Die Klasse berechnet den richtigen Mittelwert NUR für die ganzen Zahlen, falls sie beide gerade oder ungerade sind.
Fall eine Zahl gerade und andere Ungerade ist, bekommt man den falschen Mittelwert.
Der Mittelwert kann auch Fließkommazahl sein, deshlab Rückgabewert ist auf double geändert.
Auch die Parameter sind auf double geändert, da der Mittelwert kann nicht nur für die ganzen Zahle berechnet werden.

(2) Wenn double maximale negative/positive oder minimale negative/positive Werte überschreitet,
dann gibt die Funktion den falschen Mittelwert zurück.

Gefixt: in diesem Fall throws das Programm exception

(3) Wenn Paramer summande ist NULL, bricht das Programm ab.

Gefixt: in diesem Fall throws das Programm exception

(4) Wenn ein Objekt der Liste summande ist NULL, bricht das Programm auch ab.

Gefixt: in diesem Fall throws das Programm exception
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MittelwertTest {

    @AfterAll
    void printHi(){
        System.out.println("Hi");
    }

    @Test
    @DisplayName("beide Zahlen sind gerade")
    void test_beide_gerade_zahlen(){
        double mittelwert = mittelwert(10,18);
        //assumeThat(mittelwert).isEqualTo(13);
        if(mittelwert == 11) {
            assertThat(mittelwert).isEqualTo(13);
        }
    }

    @Test
    @DisplayName("beide Zahlen sind ungerade")
    void test_beide_ungerade_zahlen(){
        double mittelwert = mittelwert(5,16);
        assertThat(mittelwert).isEqualTo(10.5);
    }
    @Test
    void test_beide_zahlen_null(){
        double mittelwert = mittelwert(0,0);
        assertThat(mittelwert).isEqualTo(0);
    }
}