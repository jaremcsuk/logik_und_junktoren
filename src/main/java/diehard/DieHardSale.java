package diehard;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DieHardSale {
    public static void printSumme(PrintStream out, Warenkorb warenkorb) {
        double summe = berechneSumme(warenkorb);
        out.println(summe);
    }
    public static double berechneSumme(Warenkorb warenkorb){
        if (warenkorb == null) {
            throw new NullPointerException("null als Parameter ist nicht erlaubt");
        }
        if(warenkorb.isLeer()){
            throw new IllegalArgumentException("illegal argument: die Summeberechnung " +
                    "für leeren Warenkorb ist nicht möglich");
        }
        List<Integer> allTeile = warenkorb.getAllTeile();
        double summe = 0;
        while (isThereTeilToCountPriceFor(allTeile)) {
            int maxAnzahlVerschiedenerTeile = getMaxAnzahlVerschiedenerTeile(allTeile);
            int anzahlKombinationen = getAnzahlKombinationen(allTeile);
            double teilSumme = berechneTeilSumme(maxAnzahlVerschiedenerTeile, anzahlKombinationen);
            summe = summe + teilSumme;
        }
        return round(summe, 2);
    }
    public static double round(double value, int places) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static boolean isThereTeilToCountPriceFor( List <Integer> allTeile) {
        boolean isThereTeilToCountPriceFor = false;
        for (int i : allTeile){
            if (i>0){
                isThereTeilToCountPriceFor = true;
            }
        }
        return isThereTeilToCountPriceFor;
    }

    private static double berechneTeilSumme(int maxAnzahlVerschiedenerTeile, int anzahlKombinationen) {
        double rabatt;
        rabatt = getRabatt(maxAnzahlVerschiedenerTeile);
        double teilSumme = 0;
        for (int i = 0; i< anzahlKombinationen; i++) {
             double temp = 0;
             temp = (8 - (8 * rabatt)) * maxAnzahlVerschiedenerTeile;
             teilSumme = teilSumme +  temp;
        }
        return teilSumme;
    }

    private static double getRabatt(int anzahlVerschiedenerTeile) {
        double rabatt;
        if (anzahlVerschiedenerTeile == 2) {
            rabatt = 0.05;
        } else if (anzahlVerschiedenerTeile == 3) {
            rabatt = 0.1;
        } else if (anzahlVerschiedenerTeile == 4){
            rabatt = 0.2;
        } else if (anzahlVerschiedenerTeile == 5) {
            rabatt = 0.25;
        } else {
            rabatt = 0;
        }
        return rabatt;
    }

    private static int getAnzahlKombinationen(List<Integer> allTeile) {
        int minAnzahl = getMinAusAllenTeilen(allTeile);
        int anzahlKombinationen = minAnzahl;
        while (minAnzahl > 0) {
            for(int i = 0; i<allTeile.size();i++) {
                int j = allTeile.get(i);
                if (j>0) {
                    j--;
                    allTeile.add(i, j);
                    allTeile.remove(i+1);
                }
            }
            minAnzahl--;
        }
        return anzahlKombinationen;
    }

    private static int getMinAusAllenTeilen(List<Integer> allTeile) {
        int minAnzahl = 5;
        for (int i : allTeile){
            if(i>0) {
                if (i < minAnzahl) {
                    minAnzahl = i;
                }
            }
        }
        return minAnzahl;
    }

    private static int getMaxAnzahlVerschiedenerTeile(List<Integer> allTeile) {
        int anzahl = 0;
        for (int i: allTeile){
            if(i>0){
                anzahl++;
            }
        }
        return anzahl;
    }
}