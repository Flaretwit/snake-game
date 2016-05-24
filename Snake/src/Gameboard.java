import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Gameboard implements Runnable, ActionListener, KeyListener {
	JFrame frame = new JFrame("Snake (Slither.io)");
	// contains all outside of game functions, help,
	Container east = new Container();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");

	Run run = new Run();
	// ADDED RANDOM COMMENT FOR TESTING
	boolean running = false;

	public Gameboard() {
		frame.setSize(1000, 800);
		frame.setLayout(new BorderLayout());
		frame.add(run, BorderLayout.CENTER);

		east.setLayout(new BorderLayout());
		east.setSize(800, 200);
		east.add(start, BorderLayout.NORTH);
		east.add(stop, BorderLayout.SOUTH);
		stop.addActionListener(this);
		start.addActionListener(this);
		frame.add(east, BorderLayout.EAST);
		frame.addKeyListener(this);
		start.addKeyListener(this);
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
			run.step();
			frame.repaint();
			try {
				Thread.sleep(500);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(start)) {
			System.out.println("Started!");
			if (running == false) {
				running = true;
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (e.getSource().equals(stop)) {
			if (running == true) {
				running = false;
			}
		}

	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			run.changeDir(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			run.changeDir(1);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			run.changeDir(2);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			run.changeDir(3);
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
