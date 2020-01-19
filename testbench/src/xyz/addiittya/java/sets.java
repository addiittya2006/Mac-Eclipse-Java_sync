package xyz.addiittya.java;

/**
 * under testbench
 * Created by addiittya on 10/09/16.
 */

public class sets{
	
    static int gcd(int a, int b) {
        if(b==0){
            return a;
        }
        return gcd(b, a%b);
    }
    
    static int getTotalX(int[] a, int[] b) {
        /*
         * Write your code here.
         */
        int lcm = a[0];
        int count = 0;

        for(int i=0;i<a.length;i++) {
            lcm = (a[i]*lcm)/gcd(a[i], lcm);
        }

        for(int j=1;j*lcm<=b[0];j++) {
            int tot=0;
            for (int k=0;k<b.length;k++) {
                tot = tot+b[k]%(lcm*j);
            }
            if(tot==0) {
                count++;
            }
        }
        return count;
    }
	
    public static void main(String[] args) {
    	int[] a = {2, 4};
    	int[] b = {16, 32, 96};
    	System.out.println(getTotalX(a, b));
    }
}