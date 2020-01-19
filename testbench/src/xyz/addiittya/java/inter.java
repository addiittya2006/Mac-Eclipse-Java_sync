package xyz.addiittya.java;

import static java.lang.Math.min;
import static java.lang.Math.max;

class point {
	private final int x;
	private final int y;

	public point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}
}

public class inter{
	static void main(String[] args) {
		point p = new point(1, 1);
		point q = new point(11, 1);
		point r = new point(10, 1);

		System.out.println(onSegment(p, q, r));
	}

	static int orientation(point p, point q, point r) {
		int val = (q.y()-p.y())*(r.x()-q.x())-(q.x()-p.x())*(r.y()-q.y());
		if (val==0)
			return 0;
		return (val>0)?1:2;
	}

	static boolean onSegment(point p, point q, point r) {
		if (q.x() <= max(p.x(), r.x()) && q.x() >= min(p.x(), r.x()) && q.y() <= max(p.y(), r.y()) && q.y() >= min(p.y(), r.y())) {
			return true;
		} else {
			return false;
		}
	}

	static void convexHull(point points[], int n) {
	}
}