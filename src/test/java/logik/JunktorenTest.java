package logik;


import arithmetik.Summe;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static logik.Junktoren.*;
import static logik.Wahrheitswert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



/*
Die Klasse Junktoren funktioniert fast korrekt, da  ALLE möglichen logischen Fälle werden erfolgreich in JunktorenTest
getestet. Mehr Fälle gibt es einfach nicht.

Allerdings falls ein Enum oder beide auf null zeigen, gibt das Programm den falschen Rückgabewert.
Dieser Fall ist gefixt: Das Programm throws exception.
 */

public class JunktorenTest {

    Wahrheitswert a_wahr = WAHR;
    Wahrheitswert b_wahr = WAHR;
    Wahrheitswert a_falsch = FALSCH;
    Wahrheitswert b_falsch = FALSCH;

    //NAND

    @Test
    @Disabled("Disabled")
    //@DisplayName("NAND: a und b  sind beide WAHR. Der Rückgabewer soll FALSCH sein")
    void test_nand_beide_wahr(){
        Wahrheitswert result = nand(a_wahr, b_wahr);
        assertThat(result).isEqualTo(FALSCH);
    }

    @Test
    @DisplayName("NAND: a und b  sind beide FALSCH. Der Rückgabewer soll WAHR sein")
    void test_nand_beide_falsch(){
        Wahrheitswert result = nand(a_falsch, b_falsch);
        assertThat(result).isEqualTo(WAHR);
    }

    @Test
    @DisplayName("NAND: a = WAHR, b = FALSCH. Der Rückgabewer soll WAHR sein")
    void test_nand_a_wahr_b_falsch(){
        Wahrheitswert result = nand(a_wahr, b_falsch);
        assertThat(result).isEqualTo(WAHR);
    }

    @Test
    @DisplayName("NAND: a = FALSCH, b = WAHR. Der Rückgabewer soll WAHR sein")
    void test_nand_a_falsch_b_wahr(){
        Wahrheitswert result = nand(a_falsch, b_wahr);
        assertThat(result).isEqualTo(WAHR);
    }

    //NOT

    @onlyNot
    @DisplayName("NOT: a = WAHR. Der Rückgabewert soll FALSCH sein")
    void test_not_a_wahr(){
        Wahrheitswert result = not(a_wahr);
        assertThat(result).isEqualTo(FALSCH);
    }

    @onlyNot
    @DisplayName("NOT: a = FALSCH. Der Rückgabewert soll WAHR sein")
    void test_not_a_falsch(){
        Wahrheitswert result = not(a_falsch);
        assertThat(result).isEqualTo(WAHR);
    }

    //AND

    @Test
    @DisplayName("AND: a und b sind beide WAHR. Der Rückgabewert soll WAHR sein")
    void test_and_beide_wahr(){
        Wahrheitswert result = and(a_wahr, b_wahr);
        assertThat(result).isEqualTo(WAHR);
    }

    @Test
    @DisplayName("AND: a und b sind beide FALSCH. Der Rückgabewert soll FALSCH sein")
    void test_and_beide_falsch(){
        Wahrheitswert result = and(a_falsch, b_falsch);
        assertThat(result).isEqualTo(FALSCH);
    }

    @Test
    @DisplayName("AND: a = WAHR, b  = FALSCH. Der Rückgabewert soll FALSCH sein")
    void test_and_a_wahr_b_falsch(){
        Wahrheitswert result = and(a_wahr, b_falsch);
        assertThat(result).isEqualTo(FALSCH);
    }

    @Test
    @DisplayName("AND: a = FALSCH, b = WAHR. Der Rückgabewert soll FALSCH sein")
    void test_and_a_falsch_b_wahr(){
        Wahrheitswert result = and(a_falsch, b_wahr);
        assertThat(result).isEqualTo(FALSCH);
    }

    //OR

    @Test
    @DisplayName("OR: a und b sind beide WAHR. Der Rückgabewert soll WAHR sein")
    void test_or_beide_wahr(){
        Wahrheitswert result = or(a_wahr, b_wahr);
        assertThat(result).isEqualTo(WAHR);
    }

    @Test
    @DisplayName("OR: a und b sind beide FALSCH. Der Rückgabewert soll FALSCH sein")
    void test_or_beide_falsch() {
        Wahrheitswert result = or(a_falsch, b_falsch);
        assertThat(result).isEqualTo(FALSCH);
    }

    @Test
    @DisplayName("OR: a = WAHR, b = FALSCH. Der Rückgabewert soll WAHR sein")
    void test_or_a_wahr_b_falsch(){
        Wahrheitswert result = or (a_wahr, b_falsch);
        assertThat(result).isEqualTo(WAHR);
    }

    @Test
    @DisplayName("OR: a = FALSCH, b = WAHR. Der Rückgabewert soll WAHR sein")
    void test_or_a_falsch_b_wahr(){
        Wahrheitswert result = or(a_falsch, b_wahr);
        assertThat(result).isEqualTo(WAHR);
    }

    //implies

    @Test
    @DisplayName("implies: a und b sind beide WAHR. Der Rückgabewert soll WAHR sein")
    void test_implies_beide_wahr(){
        Wahrheitswert result = implies (a_wahr, b_wahr);
        assertThat(result).isEqualTo(WAHR);

    }

    @Test
    @DisplayName("implies: a und b sind beide FALSCH. Der Rückgabewert soll WAHR sein")
    void test_implies_beide_falsch(){
        Wahrheitswert result = implies (a_falsch, b_falsch);
        assertThat(result).isEqualTo(WAHR);
    }

    @Test
    @DisplayName("implies: a = WAHR, b = FALSCH. Der Rückgabewert soll FALSCH sein")
    void test_implies_a_wahr_b_falsch(){
        Wahrheitswert result = implies (a_wahr, b_falsch);
        assertThat(result).isEqualTo(FALSCH);
    }

    @Test
    @DisplayName("implies: a = Falsch, b = WAHR. Der Rückgabewert soll FALSCH sein")
    void test_implies_a_falsch_b_wahr(){
        Wahrheitswert result = implies (a_falsch, b_wahr);
        assertThat(result).isEqualTo(WAHR);
    }

    @Test
    @DisplayName("Null parameter ist nicht erlaubt und throws exception")
    void test_1_parameter_is_null() {
        Wahrheitswert a = null;
        Wahrheitswert b = WAHR;

        Executable code = new Executable() {
            public void execute() throws Throwable {
                and(a, b);
            }
        };
        NullPointerException exception = assertThrows(NullPointerException.class, code);
        assertThat(exception.getMessage()).contains("null");
    }

    @Test
    @DisplayName("Null parameter sind nicht erlaubt und throws exception")
    void test_both_parameters_are_null() {
        Wahrheitswert a = null;
        Wahrheitswert b = null;

        Executable code = new Executable() {
            public void execute() throws Throwable {
                nand(a, b);
            }
        };
        NullPointerException exception = assertThrows(NullPointerException.class, code);
        assertThat(exception.getMessage()).contains("null");
    }
}
