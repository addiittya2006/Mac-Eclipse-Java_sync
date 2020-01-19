// Java program to find LPSS (Longest Palindromic SubString), Using Recursion
package xyz.addiittya.java;

public class lpss1
{ static String lpss(String str, int left, int right)
   { if(left<right)                                                                                                                      
       {  int  start=left, len=right-left+1;  
	      String ret1,ret2;  // to keep return strings
	 	  while(left<right && str.charAt(left)==str.charAt(right))
            {left++; right--; }
	 	
	 	  if(left>=right)
	 	     return str.substring(start,start+len);
          else
            { ret1= lpss(str,start+1,len+start-1);
              	
               ret2= lpss(str,start,start+len-2);
               
              // retunr string which is larger one. 
               if(ret1.length()>ret2.length()) 
                 return ret1;
               return ret2;
            }
        
        }
      return "#"; // any character, here i used '#'
   }

   public static void main(String args[])
     {  String str="12";
        int lastIndex=str.length()-1;
        String str1=lpss(str,0,lastIndex);
        
        if(str.length()==1)  // if ony one character in string
          System.out.println(str1.charAt(0));
         else
          System.out.println(str1);

     }
}