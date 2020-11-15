package logik;

import static logik.Wahrheitswert.FALSCH;
import static logik.Wahrheitswert.WAHR;


/*
Die Klasse Junktoren funktioniert fast korrekt, da  ALLE möglichen logischen Fälle werden erfolgreich in JunktorenTest
getestet. Mehr Fälle gibt es einfach nicht.

Allerdings falls ein Enum oder beide auf null zeigen, gibt das Programm den falschen Rückgabewert.
Dieser Fall ist gefixt: Das Programm throws exception.
 */

public class Junktoren {

    private static void checkParameter(Wahrheitswert a, Wahrheitswert b) {
        if(a == null || b == null) {
            throw new  NullPointerException("null parameter ist nicht erlaubt");
        }
    }

    public static Wahrheitswert nand(Wahrheitswert a, Wahrheitswert b) {
        checkParameter(a, b);
        if (a == WAHR & b == WAHR) {
            return WAHR;
        }
        return FALSCH;
    }

    public static Wahrheitswert not(Wahrheitswert a) {
        checkParameter(a, a);
        return nand(a, a);
    }

    public static Wahrheitswert and(Wahrheitswert a, Wahrheitswert b) {
        checkParameter(a, b);
        return nand(nand(a, b), nand(a, b));
    }

    public static Wahrheitswert or(Wahrheitswert a, Wahrheitswert b) {
        checkParameter(a, b);
        return nand(nand(a, a), nand(b, b));
    }

    public static Wahrheitswert implies(Wahrheitswert a, Wahrheitswert b) {
        checkParameter(a, b);
        return nand(nand(nand(a, a), nand(a, a)), nand(b, b));
    }
}
