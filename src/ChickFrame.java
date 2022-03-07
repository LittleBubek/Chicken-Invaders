import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class ChickFrame extends JFrame implements MouseMotionListener, KeyListener, MouseListener {
	
	public ChickFrame() {
		addMouseMotionListener(this);
		addKeyListener(this);
		addMouseListener(this);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (ChickPanel.pause == false) {
			int mouseX = e.getX();
			int mouseY = e.getY();
			if (mouseX - 40 >= 0 && mouseX - 40 <= 520 && mouseY - 50 >= 0 && mouseY - 50 <= 520
					&& mouseX - 5 >= ChickPanel.shipX && mouseX - 5 <= ChickPanel.shipX + 80
					&& mouseY - 30 >= ChickPanel.shipY && mouseY - 30 <= ChickPanel.shipY + 80) {
				ChickPanel.shipX = mouseX - 40;
				ChickPanel.shipY = mouseY - 50;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 32) {
			for (int i = 0; i < ChickPanel.tab.length; i++)
				for (int j = 0; j < ChickPanel.tab[i].length; j++) {
					
					if (j == (ChickPanel.shipX + 40) / 20 && i == 0 )
						ChickPanel.tab[i][j] = 5;
				}
		}
		if (e.getKeyChar() == 's') {
			try {
				ChickPanel.save();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (Menu.menu == true && ChickPanel.name == false && e.getKeyChar() == 10) {
			try {
				Menu.readHighScores();
				Menu.saveScore();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getKeyChar() == 'p') {
			ChickPanel.pause();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 32) {
			for (int i = 0; i < ChickPanel.tab.length; i++)
				for (int j = 0; j < ChickPanel.tab[i].length; j++) {
					if (ChickPanel.tab[i][j] == 5)
						ChickPanel.tab[i][j] = 0;
				}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
		if(Menu.menu == true && clickX-5>=200 && clickX-5<=400 && clickY-30>=100 && clickY-30<=200) {
			ChickPanel.name = true;
		}
		if(Menu.menu == true && clickX-5>=200 && clickX-5<=400 && clickY-30>=250 && clickY-30<=350) {
			try {
				ChickPanel.read();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Menu.menu = false;
		}
		if(Menu.menu == true && clickX-5>=200 && clickX-5<=400 && clickY-30>=400 && clickY-30<=500) {
			ChickPanel.scoresTable = true;
		}
		if(Menu.menu == true && clickX-5>=200 && clickX-5<=400 && clickY-30>=450 && clickY-30<=550) {
			ChickPanel.scoresTable = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
