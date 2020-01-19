package com.addiittya;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

@SuppressWarnings("serial")
public class Calculator extends Frame implements ActionListener{
	
	Button btn1, btn2, btn3, btn4;
	TextField tf1, tf2, tf3;
	
	public Calculator(){
		tf1 = new TextField(25);
		tf2 = new TextField(25);
		tf3 = new TextField(25);
		
		btn1 = new Button("+");
		btn2 = new Button("x");
		btn3 = new Button("-");
		btn4 = new Button("÷");
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				Frame f = (Frame)e.getSource();
				f.dispose();
				System.out.println("Exiting...");
				try{
					System.exit(0);
				}catch(Exception e2){}
			}
		});
		
		tf3.setEditable(false);
		
		add(new Label("First: "));
		add(tf1);
		add(new Label("Second: "));
		add(tf2);
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(new Label("Result: "));
		add(tf3);
		
		setSize(200, 235);
		setLayout(new FlowLayout());
		setResizable(false);
		setLocation(400, 400);
		setVisible(true);
		for (int i=1;i>0;i++){
			setBackground(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				System.out.println("Exception Caught!");
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.println("Starting App..");
		new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		double res = 0.0;
//		System.out.println("Button Clicked!");
		try {
			Object ob = e.getSource();
			if(ob == btn1){
				res = Integer.parseInt(tf2.getText()) + Integer.parseInt(tf1.getText());
			}
			else if(ob == btn2){
				res = Integer.parseInt(tf2.getText()) * Integer.parseInt(tf1.getText());
			}
			else if(ob == btn3){
				if((Integer.parseInt(tf2.getText()) - Integer.parseInt(tf1.getText())) > 0){
					res = Integer.parseInt(tf2.getText()) - Integer.parseInt(tf1.getText());
				}else{
					res = Integer.parseInt(tf1.getText()) - Integer.parseInt(tf2.getText());
				}
			}
			else if(ob == btn4){
				res = (double)Integer.parseInt(tf1.getText()) / Integer.parseInt(tf2.getText());
			}
			tf3.setText(String.valueOf(res));
		} catch (NumberFormatException e1) {
			tf3.setText("Invalid Input");
			System.out.println("Error Caught");
		} catch (ArithmeticException e2) {
			tf3.setText("infinity");
			System.out.println("Error Caught");
		}
	}
}