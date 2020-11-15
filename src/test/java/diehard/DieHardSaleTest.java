package diehard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DieHardSaleTest {

    //Ist unlogisch die Summe zu berechnen für den Warenkorb, der keine bestellten Teile beinhalte
    //ein solcher Warenkorb könnte aber für die anderen Zwecke gebraucht sein, dashalb wird es
    //in Warenkorb Klasse nicht getestet,ob man midestens einen Teil bestellt hat.
    @Test
    @DisplayName("berechneSumme für leeren Warenkorb(alle Teile sind 0) throws exception")
    void test_berechneSumme_für_leeren_warenkorb(){
        Warenkorb warenkorb =  Warenkorb.create(0, 0, 0, 0, 0);
        Executable code = new Executable() {
                public void execute() throws Throwable {
                DieHardSale.berechneSumme(warenkorb);
            }
        };
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, code);
        assertThat(exception.getMessage()).contains("illegal argument");
    }

    @Test
    @DisplayName("berechneSumme: null parameter ist nicht erlaubt und throws exception")
    void test_berechneSumme_für_null_parameter(){
        Warenkorb warenkorb = null;
        Executable code = new Executable() {
            public void execute() throws Throwable {
                DieHardSale.berechneSumme(warenkorb);
            }
        };
        NullPointerException exception = assertThrows(NullPointerException.class, code);
        assertThat(exception.getMessage()).contains("null");
    }

    @Test
    @DisplayName("berechneSumme: 2 x Teil 1 -- soll 16.00 zurückgeben")
    void test_berechne_summe_2xTeil1(){
        Warenkorb warenkorb = Warenkorb.create(2,0,0,0,0);
        double summe = DieHardSale.berechneSumme(warenkorb);
        assertThat(summe).isEqualTo(16.00);
    }

    @Test
    @DisplayName("berechneSumme: Teil 1 + Teil 2 -- soll 15.20 zurückgeben")
    void test_berechne_summe_Teil1_Teil2(){
        Warenkorb warenkorb = Warenkorb.create(1,1,0,0,0);
        double summe = DieHardSale.berechneSumme(warenkorb);
        assertThat(summe).isEqualTo(15.20);
    }

    @Test
    @DisplayName("berechneSumme: 2 x Teil 1 + Teil 2 -- soll 23.20 zurückgeben")
    void test_berechne_summe_2xTeil1_Teil2(){
        Warenkorb warenkorb = Warenkorb.create(2,1,0,0,0);
        double summe = DieHardSale.berechneSumme(warenkorb);
        assertThat(summe).isEqualTo(23.20);
    }

    @Test
    @DisplayName("berechneSumme: 2 x Teil 1 + 2 x Teil 2 -- soll 30.40 zurückgeben")
    void test_berechne_summe_2xTeil1_2xTeil2(){
        Warenkorb warenkorb = Warenkorb.create(2,2,0,0,0);
        double summe = DieHardSale.berechneSumme(warenkorb);
        assertThat(summe).isEqualTo(30.40);
    }

    @Test
    @DisplayName("berechneSumme: Teil 1, 2, 3 und 4 -- soll 25.60 zurückgeben")
    void test_berechne_summe_Teil1_Teil2_Teil3_Teil4(){
        Warenkorb warenkorb = Warenkorb.create(1,1,1,1,0);
        double summe = DieHardSale.berechneSumme(warenkorb);
        assertThat(summe).isEqualTo(25.60);
    }

    @Test
    @DisplayName("berechneSumme: Teil 1, 2, 3, 4 und 5 -- soll 30.0 zurückgeben")
    void test_berechne_summe_Teil1_Teil2_Teil3_Teil4_teil5(){
        Warenkorb warenkorb = Warenkorb.create(1,1,1,1,1);
        double summe = DieHardSale.berechneSumme(warenkorb);
        assertThat(summe).isEqualTo(30.0);
    }
}