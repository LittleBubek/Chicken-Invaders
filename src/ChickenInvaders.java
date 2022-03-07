
import java.io.FileNotFoundException;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class ChickenInvaders {

	public static void main(String[] args) throws FileNotFoundException {

		ChickPanel myp = new ChickPanel();

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ChickFrame window = new ChickFrame();
				window.setVisible(true);
				window.setTitle("Chicken invaders");
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window.add(myp);
				window.pack();
				TextField field = new TextField();
				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle("Enter your name to start a new game");
				frame.add(field);
				frame.pack();
				frame.add(field);
			}
		});
		myp.timer();	
	}
}
