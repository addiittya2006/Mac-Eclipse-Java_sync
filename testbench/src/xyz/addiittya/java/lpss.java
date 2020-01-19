package xyz.addiittya.java;

public class lpss {
	
	static String fun(String str, int left, int right) {
		if(right-left>0){
			// int left = 0, right = len;
			int len = right-left+1;
			int  start=left;
			String ret1,ret2;
			while(left<right && str.charAt(left)==str.charAt(right)) {
				left++; right--;
			}

			if(left>=right)
				return str.substring(start,start+len);
			else {
				ret1= fun(str,start+1,len+start-1);
				ret2= fun(str,start,start+len-2);
				if(ret1.length()>ret2.length())
					return ret1;
				return ret2;
			}
		} else {
			return "no pal";
		}
	}

	public static void main(String[] args) {
		// String str = "adityaaytida";
		String str = "1212111211211";
		// String str = ""
		System.out.println(fun(str, 0, str.length()-1));
	}
}