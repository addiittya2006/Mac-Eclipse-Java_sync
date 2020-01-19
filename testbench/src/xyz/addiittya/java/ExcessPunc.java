package xyz.addiittya.java;

import java.util.Arrays;

/**
 * under testbench
 * Created by addiittya on 10/09/16.
 */
public class ExcessPunc {

    public static void main(String[] args) {

        Integer[] arr = {3, 1, 4, 1, 5, 9};
        Arrays.sort(arr, (o1, o2) -> o1 < o2 ? 1 : (o2 > o1 ? -1 : 0));
        System.out.println(Arrays.toString(arr));

        System.out.println(123+32l);

//        int count = 0;
//        for (int i = 0;i<100;i++); {
//            count++;
//        }
//        System.out.println(count);
    }

}
