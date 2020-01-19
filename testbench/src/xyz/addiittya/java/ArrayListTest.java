package xyz.addiittya.java;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * under testbench
 * Created by addiittya on 06/09/16.
 */

public class ArrayListTest {

    public static void main(String[] args) {

        ArrayList<String> alist = new ArrayList<>();
        alist.add("hello");
        alist.add("hello1");
        alist.add("hello2");
        alist.add("hello3");
        alist.add("hello4");

        String list = alist.toString().replace("[", "").replace("]", "");
        String list1 = alist.toString();

//		ArrayList<String> listback = (ArrayList<String>) Arrays.asList(list.split(","));
        ArrayList<String> listback = new ArrayList<String>(Arrays.asList(list.split(", ")));
        ArrayList<String> listback1 = new ArrayList<String>(Arrays.asList(list1.split(", ")));
//		List<String> listback = Arrays.asList(list.split(", "));
        ArrayList<String> array = new ArrayList<String>(Arrays.asList(list.split(", ")));

        System.out.println(alist);
        System.out.println(array.get(0));
        System.out.println(listback);
        System.out.println(listback.get(0));
        System.out.println(listback.contains("hello"));
        System.out.println(listback1.contains("hello"));
        System.out.println(listback.get(2));

    }

}