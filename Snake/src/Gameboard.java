import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Gameboard implements Runnable, ActionListener {
	//contains all outside of game functions, help, 
	Container east = new Container();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");

	JFrame frame = new JFrame("Snake (Slither.io)");
	Run run = new Run();


	boolean running = false;
	public Gameboard() {
		frame.setSize(1000,800);
		frame.setLayout(new BorderLayout());
		frame.add(run, BorderLayout.CENTER);
		
		east.setLayout(new BorderLayout());
		east.setSize(800,200);
		east.add(start);
		start.addActionListener(this);
		frame.add(east, BorderLayout.EAST);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.repaint();
		frame.setResizable(false);
		
		
	}
	
	public static void main(String[] args) {
		new Gameboard();
	}
	
	public void run() {	
		while(running == true) {
			run.step();
			frame.repaint();
			try {
				Thread.sleep(1000);
				System.out.println("Running!");
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(start)) {
			System.out.println("Started!");
			if(running == false) {
				running = true;
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (e.getSource().equals(stop)) {
			if(running == true) {
				running = false;
			}
		}
		
	}
	
}
