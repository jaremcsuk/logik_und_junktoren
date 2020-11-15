package diehard;

import java.util.ArrayList;
import java.util.List;

public class Warenkorb {
    private int teil1;
    private int teil2;
    private int teil3;
    private int teil4;
    private int teil5;

    public int getTeil1() {
        return teil1;
    }

    public int getTeil2() {
        return teil2;
    }

    public int getTeil3() {
        return teil3;
    }

    public int getTeil4() {
        return teil4;
    }

    public int getTeil5() {
        return teil5;
    }

    private Warenkorb(int teil1, int teil2, int teil3, int teil4, int teil5) {
        this.teil1 = teil1;
        this.teil2 = teil2;
        this.teil3 = teil3;
        this.teil4 = teil4;
        this.teil5 = teil5;
    }

    public static Warenkorb create(int teil1, int teil2, int teil3, int teil4, int teil5) {
        if(teil1 <0 || teil2 <0 || teil3 <0 || teil4 <0 || teil5 <0) {
            throw new IllegalArgumentException("illegal arguments: kein Parameter darf kleiner 0 sein");
        }

        //Es handelt sich eher um ein B2C als um B2B Online Händler, das heißt,
        // dass die Blu-Rays Disks gehen eher an Endkunden. Daher ist die Annahme,
        //dass Maxwert kann nicht großer als 100 sein
        if(teil1 >100 || teil2 >100 || teil3 >100 || teil4 >100 || teil5 >100) {
            throw new IllegalArgumentException("illegal arguments: es ist nicht möglich, " +
                                                "mehr als 100 Exemplare eines Teiles zu bestellen");
        }
        return new Warenkorb (teil1, teil2, teil3, teil4, teil5);
    }

    public List<Integer> getAllTeile(){
        List<Integer> allTeile = new ArrayList<>();
        allTeile.add(teil1);
        allTeile.add(teil2);
        allTeile.add(teil3);
        allTeile.add(teil4);
        allTeile.add(teil5);
        return allTeile;
    }

    public boolean isLeer(){
        return (teil1 == 0 && teil2 == 0 && teil3 == 0 && teil4 == 0 && teil5 == 0);
    }
}