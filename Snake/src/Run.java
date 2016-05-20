import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;
import java.awt.Color;

public class Run extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// this will contain the gameboard and track what each grid is
	final int BLANK = 0;
	final int FOOD = 1;
	final int OCCUPIED = 2;
	final int HEIGHT = 800;
	final int WIDTH = 800;
	final int PIXELS_PER_SQUARE = 40;
	Snake_Section snake = new Snake_Section();
	final int NUMSQUARES = HEIGHT / PIXELS_PER_SQUARE;
	//20 squares
	int[][] field = new int[NUMSQUARES][NUMSQUARES]; // 40 pixels per grid for
														// now
	//0 is blank
	//1 is food
	//2 is snake
	Point foodloc = new Point();

	public Run() {
		super();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.RED);
		g.fillRect(800, 0, getWidth(), getHeight());
		// paints columns lines
		for (int i = 0; i < WIDTH; i += PIXELS_PER_SQUARE) {
			g.setColor(Color.RED);
			g.drawLine(i, 0, i, 800);
		}
		// paints row lines for the grid
		for (int j = 0; j < HEIGHT; j += PIXELS_PER_SQUARE) {
			g.drawLine(0, j, 800, j);
		}
		paintSnake(g);
	}

	public void step() {
		if (!checkDeath()) {
			moveSnake(snake.getDir());
			System.out.println("snake length" + snake.snakeloc.size());
			if(snake.head.getX() == foodloc.getX() && snake.head.getY() == foodloc.getY()) {
				placeFood();
				
			}

		} else {
			// quits the game running to false
		}

	}
	
	public void paintSnake(Graphics g) {
		System.out.println("method");
		g.setColor(Color.BLUE);
		for(int k = 0; k < snake.snakeloc.size(); k++) {
			System.out.println("painting snake");
			int x = snake.getPoint(k).getX();
			int y = snake.getPoint(k).getY();
			System.out.println("x" + x + "y" + y);
			g.fillRect(x*40, y*40, 40, 40);
			
		}
	}
	
	public void changeDir(int dir) {
		snake.direction = dir;
	}
	
	// moves the snake body
	public void moveSnake(int direction) {
		// sets the coordinates of each snake_section to the
		// coordinates of the one previous to it
		for (int i = snake.snakeloc.size() - 1; i >= 1; i--) {
			snake.getPoint(i).setX(snake.getPoint(i - 1).getX());
			snake.getPoint(i).setY(snake.getPoint(i - 1).getY());
		}
		// moves the head of the snake depending on the snake's direction
		if (direction == 0) {
			snake.head.setY(snake.head.getY() + 1);
		}
		if (direction == 1) {
			snake.head.setX(snake.head.getX() + 1);
		}
		if (direction == 2) {
			snake.head.setY(snake.head.getY() - 1);
		}
		if (direction == 3) {
			snake.head.setX(snake.head.getX() - 1);
		}
		//updates the field array so that it knows where the snake is. 
		for(int j = 0; j < snake.length; j++) {
			field[snake.getPoint(j).getX()][snake.getPoint(j).getX()] = 2;
		}
	}

	// checks for snake death by either
	public boolean checkDeath() {
		for (int i = 0; i < snake.length; i++) {
			if (snake.getPoint(i).getX() == snake.head.getX() || snake.snakeloc.get(i).getX() == snake.head.getY()) {
				return true;
			}
			if (snake.getPoint(i).getX() >= NUMSQUARES || snake.getPoint(i).getX() >= NUMSQUARES) {
				return true;
			}
		}
		return false;

	}

	// places food once it is consumed
	// also does not place food on the snake
	public void placeFood() {
		int x = 0;
		int y = 0;
		boolean onSnake = false;
		Random random = new Random();
		while (onSnake == true) {
			onSnake = false;
			x = random.nextInt(NUMSQUARES);
			y = random.nextInt(NUMSQUARES);
			for (int i = 0; i < snake.length; i++) {
				if (snake.getPoint(i).getX() == x && snake.getPoint(i).getY() == y) {
					onSnake = true;
				}
			}
		}
		foodloc.setX(x);
		foodloc.setY(y);
		field[x][y] = 1;
	}
}
