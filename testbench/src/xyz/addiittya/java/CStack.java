package xyz.addiittya.java;

import java.util.ArrayList;
import java.util.List;

public class CStack {
	
	List<Integer> stack;
	
	public CStack() {
		stack = new ArrayList<>();
	}
	
	public void push(int a) {
		stack.add(a);
	}
	
	public int pop() {
		return stack.remove(stack.size()-1);
	}
	
	@Override
	public String toString() {
		return stack.toString();
	}
	
	public static void main(String args[]) {
		CStack cs = new CStack();
		cs.push(3);
		cs.push(4);
		cs.push(6);
		cs.push(5);
		cs.pop();
		cs.pop();
		cs.push(10);
		System.out.println(cs);
	}

}