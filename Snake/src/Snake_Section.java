
import java.util.ArrayList;
import java.util.Random;

public class Snake_Section {
	// 0 is facing up, 1 is facing east, 2 is facing south, 3 is facing west
	// (randomly chosen upon snake spawn)
	int direction;
	ArrayList<Point> snakeloc = new ArrayList<Point>();
	// length will be equal to 4 at beginning of the game
	int length = snakeloc.size();
	Point head = new Point();

	// initializes a snake
	public Snake_Section() {
		Random random = new Random();

		direction = random.nextInt(4);
		head.setX(random.nextInt(10) + 5);
		head.setY(random.nextInt(10) + 5);
		snakeloc.add(head);
		//initializes a snake of length 4
		for(int i = 0; i < 3; i++) {
			addLength(direction);
		}
		
	}

	public void addLength(int direction) {
		Point newPoint = new Point();
		if (direction == 0) {
			// if the snake is facing direction 0, which is down in this case
			// the next snake section is defined as such
			newPoint.setX(snakeloc.get(snakeloc.size() - 1).getX());
			newPoint.setY(snakeloc.get(snakeloc.size() - 1).getY() + 1);
		}
		if (direction == 1) {
			newPoint.setX(snakeloc.get(snakeloc.size() - 1).getX() - 1);
			newPoint.setY(snakeloc.get(snakeloc.size() - 1).getY());
		}
		if (direction == 2) {
			newPoint.setX(snakeloc.get(snakeloc.size() - 1).getX());
			newPoint.setY(snakeloc.get(snakeloc.size() - 1).getY() - 1);
		}
		if (direction == 3) {
			newPoint.setX(snakeloc.get(snakeloc.size() - 1).getX() + 1);
			newPoint.setY(snakeloc.get(snakeloc.size() - 1).getY());
		}
		snakeloc.add(newPoint);

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

	// this.x refers to the class variable
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
