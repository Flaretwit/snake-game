import java.awt.Graphics;
import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.Random;

public class Snake_Section  {
	//0 is facing up, 1 is facing east, 2 is facing south, 3 is facing west
	//(randomly chosen upon snake spawn)
	int direction;
	ArrayList<Point> snakeloc = new ArrayList<Point>();
	//length will be equal to 4 at beginning of the game
	int length = snakeloc.size();
	Point head = new Point();

	//initializes a snake
	public Snake_Section() {
		Random random = new Random();
		
		direction = random.nextInt(4);
		head.setX(random.nextInt(10) + 5);
		head.setY(random.nextInt(10) + 5);
		snakeloc.add(head);
		System.out.println("Direction" + direction);
		
		if(direction == 0) {
			for(int i = head.getY(); i > head.getY() - 4; i--) {
				Point newPoint = new Point();
				newPoint.setX(head.getX());
				newPoint.setY(i);
				snakeloc.add(newPoint);
			}
		}
		if(direction == 1) {
			for(int i = head.getX(); i > head.getX() - 4; i--) {
				Point newPoint = new Point();
				newPoint.setX(i);
				newPoint.setY(head.getY());
				snakeloc.add(newPoint);
			}
		}
		if(direction == 2) {
			for(int i = head.getY(); i < head.getY() + 4; i++) {
				Point newPoint = new Point();
				newPoint.setX(head.getX());
				newPoint.setY(i);
				snakeloc.add(newPoint);
			}
		}
		if(direction == 3) {
			for(int i = head.getX(); i < head.getX() + 4; i++) {
				Point newPoint = new Point();
				newPoint.setX(i);
				newPoint.setY(head.getY());
				snakeloc.add(newPoint);
			}
		}
		for(int i = 0; i < 4; i++) {
		System.out.println(snakeloc.get(i).getX());
		}
	}

	

	public int getDir() {
		return direction;
	}
	public void setDir(int dir) {
		direction = dir;
	}
	
	public void setPoint(int x, int y) {
		Point tail = new Point();
		tail.setX(x);
		tail.setY(y);
		snakeloc.add(tail);
	}
	public Point getPoint(int i) {
		return snakeloc.get(i);
	}
	
}

class Point {
	int x;
	int y;
	//this.x refers to the class variable	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	
}
