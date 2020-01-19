package xyz.addiittya.java;

public class squares {
	
//	public static int getSquares(int a, int b) {
//		int res = 0;
//		for(int i=a; i<b; i++) {
//			Double sq = Math.sqrt(Double.parseDouble(Integer.toString(i)));
//			if (sq%1==0) {
//				res++;
//			}
//		}
//		return res;
//	}
	
	public static int getSquares(int a, int b) {
		return (int) Math.abs(Math.sqrt(Double.parseDouble(Integer.toString(b))) - Math.sqrt(Double.parseDouble(Integer.toString(a))));
	}
	
	public static void main(String args[]) {
		System.out.println(getSquares(21, 99));
	}
}