package diehard;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Warenkorb wk = Warenkorb.create(5,0,0,0,0);
        DieHardSale.printSumme(System.out, wk); // Ausgabe: 8.00
    }
}