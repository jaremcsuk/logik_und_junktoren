package diehard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static arithmetik.Mittelwert.mittelwert;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WarenkorbTest {

    @BeforeEach
    @Test
    @Disabled
    @DisplayName("Warenkorb mit negativen Zahlen darf nicht erzeugt werden und throws exception")
    void test_instanziirung_warenkorb_mit_negarivenZahlen(){
        Executable code = new Executable() {
            public void execute() throws Throwable {
                Warenkorb warenkorb = Warenkorb.create(-1, 0, 2, 3, 0);
            }
        };
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, code);
        assertThat(exception.getMessage()).contains("illegal argument");
    }

    //Es handelt sich eher um ein B2C als um B2B Online Händler, das heißt,
    // dass die Blu-Rays Disks gehen eher an Endkunden. Daher ist die Annahme,
    //dass Maxwert kann nicht großer als 100 sein

    @Test
    @DisplayName("Warenkorb mit mehr als 100 Exemplaren pro Teil darf nicht erstellt werden und throws exception")
    void test_instanziirung_warenkorb_mit_101_Exemplare_pro_Teil(){
        Executable code = new Executable() {
            public void execute() throws Throwable {
                Warenkorb warenkorb = Warenkorb.create(101, 101, 101, 100, 0);
            }
        };
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, code);
        assertThat(exception.getMessage()).contains("illegal argument");
    }



    @Test
    @DisplayName("Warenkorb ist korrekt instanziirt")
    void test_if_warenkorb_is_korrekt_instanziiert(){
        Warenkorb warenkorb = Warenkorb.create(1,2,3,4,5);
        assertThat(warenkorb.getTeil1()).isEqualTo(1);
        assertThat(warenkorb.getTeil2()).isEqualTo(2);
        assertThat(warenkorb.getTeil3()).isEqualTo(3);
        assertThat(warenkorb.getTeil4()).isEqualTo(4);
        assertThat(warenkorb.getTeil5()).isEqualTo(5);
    }

    @Test
    @DisplayName("getAllTeile gibt alle Teile richtig zurück")
    void test_getAllTeile(){
        Warenkorb warenkorb = Warenkorb.create(10,10,20,20,30);
        ArrayList <Integer> teile = new ArrayList<>();
        teile.add(10);
        teile.add(10);
        teile.add(20);
        teile.add(20);
        teile.add(30);
        assertThat(warenkorb.getAllTeile()).isEqualTo(teile);
    }

    @Test
    @DisplayName("isLeer Methode soll true zurückgeben")
    void test_isLeer_true(){
        Warenkorb warenkorb = Warenkorb.create(0,0,0,0,0);
        assertThat(warenkorb.isLeer()).isEqualTo(true);
    }

    @Test
    @DisplayName("isLeer Methode soll false zurückgeben")
    void test_isLeer_false(){
        Warenkorb warenkorb = Warenkorb.create(0,0,1,0,0);
        assertThat(warenkorb.isLeer()).isEqualTo(false);
    }
}