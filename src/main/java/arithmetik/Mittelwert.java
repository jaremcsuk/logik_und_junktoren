package arithmetik;

import java.math.BigDecimal;

public class Mittelwert {

    /*
   Die Klasse Mittelwert funktionierte nicht ganz korrekt.
   (1) Die Klasse berechnet den richtigen Mittelwert NUR für die ganzen Zahlen, falls sie beide gerade oder ungerade sind

   Gefixt: Rückgabewert soll double sein. Parameter wurden von int auf double geändert.

   (2) Wenn double maximale negative/positive oder minimale negative/positive Werte überschreitet,
   dann gibt die Funktion den falschen Mittelwert zurück.

   Gefixt: in diesem Fall throws das Programm exception

   (3) Wenn Paramer summande ist NULL, bricht das Programm ab.

   Gefixt: in diesem Fall throws das Programm exception

   (4) Wenn ein Objekt der Liste summande ist NULL, bricht das Programm auch ab.

   Gefixt: in diesem Fall throws das Programm exception
    */

    public static double mittelwert(double a, double b) {
        if(isGreaterThanMAXPositiveNumber(a, b)){
            throw new IllegalArgumentException("The maximum value of double is exceeded");
        } else if (isSmallerThanMINPositiveNumber(a, b)) {
            throw new IllegalArgumentException("The minimum value of double is exceeded");
        } else if (isSmallerThanMINNegativNumbner(a, b)){
            throw new IllegalArgumentException("The maximum negative value of double is exceeded");
        } else if (isGreaterThanMAXNegativNumber(a, b)){
            throw new IllegalArgumentException("The minimum value of negative double is exceeded");
        }
        return (a + b) / 2.0;
    }

    private static boolean isGreaterThanMAXPositiveNumber(double a, double b){
        BigDecimal aa = BigDecimal.valueOf(a);
        BigDecimal bb = BigDecimal.valueOf(b);
        BigDecimal a_plus_b = aa.add(bb);
        BigDecimal doubleMAX = BigDecimal.valueOf(Double.MAX_VALUE);
        if (a_plus_b.compareTo(doubleMAX) > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSmallerThanMINPositiveNumber(double a, double b){
        BigDecimal aa = BigDecimal.valueOf(a);
        BigDecimal bb = BigDecimal.valueOf(b);
        BigDecimal a_plus_b = aa.add(bb);
        a_plus_b = a_plus_b.divide(new BigDecimal(2));
        BigDecimal doubleMIN = BigDecimal.valueOf(Double.MIN_VALUE);
        if ((a_plus_b.compareTo(doubleMIN) < 0) &&
            (a_plus_b.compareTo(new BigDecimal(0))>0)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSmallerThanMINNegativNumbner(double a, double b){
        BigDecimal aa = BigDecimal.valueOf(a);
        BigDecimal bb = BigDecimal.valueOf(b);
        BigDecimal a_plus_b = aa.add(bb);
        BigDecimal doubleMAX = BigDecimal.valueOf(-1*Double.MAX_VALUE);
        if (a_plus_b.compareTo(doubleMAX) < 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isGreaterThanMAXNegativNumber(double a, double b){
        BigDecimal aa = BigDecimal.valueOf(a);
        BigDecimal bb = BigDecimal.valueOf(b);
        BigDecimal a_plus_b = aa.add(bb);
        a_plus_b = a_plus_b.divide(new BigDecimal(2));
        BigDecimal doubleMIN = BigDecimal.valueOf(-Double.MIN_VALUE);
        if ((a_plus_b.compareTo(doubleMIN) > 0) &&
                (a_plus_b.compareTo(new BigDecimal(0))<0)) {
            return true;
        } else {
            return false;
        }
    }





}