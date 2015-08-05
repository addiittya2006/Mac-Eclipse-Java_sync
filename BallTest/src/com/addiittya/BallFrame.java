package com.addiittya;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;

@SuppressWarnings("serial")
public class BallFrame extends Frame implements Runnable{

	Graphics g;
	//	Color pcolor;
	Label label;
	//	Button blue, red, green, yellow;
	Thread t;
	Panel p;
	int x = 10, y = 10;

	public BallFrame(){
		t = new Thread(this);
		setLayout(new FlowLayout());
		//		setLayout(new GridLayout());
		setBackground(Color.RED);
		//		add(blue = new Button("BLUE"));
		//		add(red = new Button("RED"));
		//		add(yellow = new Button("YELLOW"));
		//		add(green = new Button("GREEN"));
		p = new Panel();
		p.setSize(200, 200);
		p.setBackground(Color.YELLOW);
		add(p);
		setLocation(200, 0);
		setSize(800, 500);
		setVisible(true);
		t.start();
	}

	public void run(){
		//		for (int i = 1; i <= 10; i++) {
		//			System.out.print(i+" ");
		//		}
		while(true){
			try {
				t.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//			if(x<500 && y<500){
			//				x+=10;y+=10;
			//			}
			//			else if(x<800 && y<=500){
			//				x+=10;y-=10;
			//			}
			//			else if(x<=800 && y>=10){
			//				x-=10;y-=10;
			//			}
			if(x<=800){
				x+=10;
				if(y<500){
					y+=10;
				}else if(y<=500){
					y-=10;
				}
			}
			repaint();
		}
	}

	public void paint(Graphics g){
		g.fillOval(x, y, 20, 20);
		this.g = this.p.getGraphics();
	}

}
