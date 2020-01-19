package xyz.addiittya.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * under testbench
 * Created by addiittya on 06/09/16.
 */

public class OzGame {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int nt = s.nextInt();
        for (int i = 0; i < nt; i++) {
            int n = s.nextInt();
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                arr.add(s.nextInt());
            }
            s.close();
            int soln = soln(arr);
            System.out.println(soln);
        }
    }

    private static int soln(ArrayList<Integer> array){
        int count = 0;
        int i;
        Collections.sort(array);
        int size = array.size();
        for (i = 0; i < size-1;) {
            count++;
            int num = array.get(i);
            int lsuc = array.get(i+1);
            int suc = num + 1;
            if(lsuc == suc) {
                i += 2;
            } else {
                i++;
            }
        }
        if (i == size-1) {
            count++;
        }
        return count;
    }

}