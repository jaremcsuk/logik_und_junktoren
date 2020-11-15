package arithmetik;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/*
Die klasse Summe funktionierte nicht ganz korrekt.
(1) Wenn long maximale oder minimale Werte überschreitet, dann gibt die Funktion die falsche Summe zurück.
(2) Wenn Paramer summande ist NULL, bricht das Programm ab.
(3) Wenn ein Objekt der Liste summande ist NULL, bricht das Programm auch ab.
Diese Bugs sind gefixt
 */
public class Summe {

    public static Long summe(List<Long> summanden) {
        if (summanden == null) {
            throw new IllegalArgumentException("Die Liste mit summanden zeigt auf null");

        //falls ein Objekt der List null ist
        } else {
            for (Long l : summanden) {
                if (l == null) {
                    System.out.println("Das Element der Liste Summanden kann nicht null sein");
                    throw new IllegalArgumentException("Es gibt ein Longobjekt in summanden, das auf null zeigt");
                }
            }
        }
        if (isSummGreaterThanLONG_MAX(summanden)) {
            throw new IllegalArgumentException("The maximum value of long is exceeded");
        } else if (isSummSmallerThanLONG_MIN(summanden)){
            throw new IllegalArgumentException("The minimum value of long is exceeded");
        }

        Long result = 0L;
        for (int i = 0; i < summanden.size(); i++) {
            result += summanden.get(i);
        }
        return result;
    }

    private static boolean isSummGreaterThanLONG_MAX(List<Long> summanden){
        BigInteger summe = new BigInteger("0");
        for (Long l: summanden){
            BigInteger longToBigInt = BigInteger.valueOf(l);
            summe = summe.add(longToBigInt);
        }
        BigInteger LongMAX = BigInteger.valueOf(Long.MAX_VALUE);
        if(summe.compareTo(LongMAX) > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSummSmallerThanLONG_MIN(List<Long> summanden){
        BigInteger summe = new BigInteger("0");
        for (Long l: summanden){
            BigInteger longToBigInt = BigInteger.valueOf(l);
            summe = summe.add(longToBigInt);
        }
        BigInteger LongMIN = BigInteger.valueOf(Long.MIN_VALUE);
        if(summe.compareTo(LongMIN) < 0) {
            return true;
        } else {
            return false;
        }
    }
}