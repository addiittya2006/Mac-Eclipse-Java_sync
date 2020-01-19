package xyz.addiittya.java;

/**
 * under testbench
 * Created by addiittya on 10/09/16.
 */

public class FloatTest {

    public static void main(String[] args) {

        Float a = new Float("1.0");
        System.out.println(Float.floatToIntBits(a));

        long dd = 24L*1000*10000*100;
        System.out.println((int) dd);

    }

}
