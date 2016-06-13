import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Gameboard implements Runnable, ActionListener, KeyListener {
	Run run = new Run();
	static int difficulty = 1;
	JFrame frame = new JFrame("Snake (Slither.io)");
	JFrame scoreboard = new JFrame("Scoreboard");
	// contains all outside of game functions, help,
	Container east = new Container();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	Container topEast = new Container();
	Container midEast = new Container();
	Container subEast = new Container();
	JLabel score = new JLabel("Score: " + run.score);
	JLabel diffiLabel = new JLabel("Current difficulty: " + difficulty);
	JButton downDiffi = new JButton("-");
	JButton upDiffi = new JButton("+");
	JButton pause = new JButton("Pause");
	boolean paused = false;
	static boolean running = false;

	// limits number of keypresses to 1?
	int keypresses = 0;

	public Gameboard() {
		pause.setEnabled(false);
		stop.setEnabled(false);
		upDiffi.setFocusable(false);
		downDiffi.setFocusable(false);
		start.setFocusable(false);
		pause.setFocusable(false);
		stop.setFocusable(false);
		
		frame.setSize(913, 830);
		frame.setLayout(new BorderLayout());
		frame.add(run, BorderLayout.CENTER);
		
		topEast.setLayout(new GridLayout(5, 1));
		east.setLayout(new GridLayout(3,1));
		east.setSize(800, 200);
		topEast.add(start);
		
		east.add(stop, BorderLayout.SOUTH);
		
		stop.addActionListener(this);
		start.addActionListener(this);
		
		frame.add(east, BorderLayout.EAST);
		frame.addKeyListener(this);
		start.addKeyListener(this);
		midEast.setLayout(new BorderLayout());
		subEast.setLayout(new GridLayout(1, 2));
		subEast.add(downDiffi);
		downDiffi.addActionListener(this);			
		upDiffi.addActionListener(this);
		pause.addActionListener(this);
		subEast.add(upDiffi);
		midEast.add(subEast, BorderLayout.SOUTH);
		midEast.add(diffiLabel, BorderLayout.NORTH);
		topEast.add(midEast);
		topEast.add(pause);
		topEast.add(score);
		east.add(topEast, BorderLayout.NORTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		frame.repaint();

		frame.setResizable(false);

	}

	public static void main(String[] args) {
		new Gameboard();
	}

	public void run() {
		while (running == true) {
			//updates score
			score.setText("Score: " + run.score);
			keypresses = 0;
			run.step();
			frame.repaint();
			try {
				Thread.sleep((long) (200 / Math.sqrt(difficulty)));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(start)) {
			if (running == false) {
				running = true;
				upDiffi.setEnabled(false);
				downDiffi.setEnabled(false);
				start.setEnabled(false);
				pause.setEnabled(true);
				stop.setEnabled(true);

				Thread t = new Thread(this);
				t.start();
			}
		} else if (e.getSource().equals(stop)) {
			if (running == true || paused == true) {
				running = false;
				start.setEnabled(true);
				pause.setEnabled(false);
				stop.setEnabled(false);
				upDiffi.setEnabled(true);
				downDiffi.setEnabled(true);
				int n = JOptionPane.showConfirmDialog(
					    frame,
					    "Are you sure you want to quit this amazing game of Snake?",
					    "An Innocent Warning",
					    JOptionPane.YES_NO_OPTION);
				if(n == 0) {
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
				else {
					
				}
			}
		} else if (e.getSource().equals(upDiffi)) {
			difficulty++;
			diffiLabel.setText("Current difficulty: " + difficulty);
		} else if (e.getSource().equals(downDiffi)) {
			if (difficulty != 1) {
				difficulty--;
				diffiLabel.setText("Current difficulty: " + difficulty);
			}
		} else if (e.getSource().equals(pause)) {
			if (running == true) {
				running = false;
				pause.setText("Unpause");
				paused = true;
				
			} else if(running == false) {
				running = true;
				Thread t = new Thread(this);
				t.start();
				pause.setText("Pause");
				paused = false;
			}
		} 
	}

	// checks for press of the arrow keys to change the snake's direction

	public void keyPressed(KeyEvent e) {
		if (keypresses == 0) {
			if (e.getKeyCode() == KeyEvent.VK_UP && run.snake.getDir() != 2) {
				run.changeDir(0);
				keypresses++;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && run.snake.getDir() != 3) {
				run.changeDir(1);
				keypresses++;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && run.snake.getDir() != 4) {
				run.changeDir(2);
				keypresses++;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT && run.snake.getDir() != 1) {
				run.changeDir(3);
				keypresses++;
			}
		}
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
