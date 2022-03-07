import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Menu {
	
	static String[] nicks = new String[3];
	static int[] scores = new int[3];
	static boolean menu = true;
	
	// drawing a menu at the beginning of the game
		public static void menu(Graphics g) {
			if(menu == true && ChickPanel.points<50) {
				g.setColor(Color.WHITE);
				g.fillRect(200, 100, 200, 100);
				g.fillRect(200, 250, 200, 100);
				g.fillRect(200, 400, 200, 100);
				g.setColor(Color.BLACK);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
				g.drawString("New game", 205, 160);
				g.drawString("Load game", 200, 310);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 37));
				g.drawString("High scores", 200, 460);
			}	
		}
		
	// if you lose all the lives the game is over, press enter to return to the menu
		public void gameOver(Graphics g){
			if(menu == true && ChickPanel.life<1) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 600, 600);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 75));
				g.setColor(Color.BLACK);
				g.drawString("GAME OVER",75, 250);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
				g.drawString("for MENU press enter",75, 450);
				for(int i = 0; i<ChickPanel.tab.length; i++)
					for(int j = 0; j<ChickPanel.tab.length; j++) {
						ChickPanel.tab[i][j] = 0;
					}
				ChickPanel.shipX = 260;
				ChickPanel.shipY = 520;
				ChickPanel.spaceship = new ImageIcon("images\\spaceship1.png").getImage();	
				ChickPanel.upgrade = false;
			}			
		}
	//you win when you gain 50 points, to go back to the menu press enter
		public void victory(Graphics g) {
			if(menu == true && ChickPanel.points >= 50) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 600, 600);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
				g.setColor(Color.BLACK);
				g.drawString("VICTORY!",75, 250);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
				g.drawString("for MENU press enter",75, 450);
				for(int i = 0; i<ChickPanel.tab.length; i++)
					for(int j = 0; j<ChickPanel.tab.length; j++) {
						ChickPanel.tab[i][j] = 0;
					}
				ChickPanel.shipX = 260;
				ChickPanel.shipY = 520;
				ChickPanel.spaceship = new ImageIcon("images\\spaceship1.png").getImage();	
				ChickPanel.upgrade = false;
			}
		}
	//reading the high scores table from a file
		public static void readHighScores() throws FileNotFoundException {
			
			String line;
			String nick;
			String scoreStr;
			int score = 0;
			int i = 0;
			File highScores = new File("textFiles\\HighScores.txt");
			Scanner read = new Scanner(highScores);
			while(read.hasNextLine()) {
				line = read.nextLine();
				String[] parts = line.split(" ");
				nick = parts[0];
				scoreStr = parts[1];
				try {
					score = Integer.parseInt(scoreStr);
				}
				catch(NumberFormatException ex){
		            ex.printStackTrace();
		        }
				scores[i] = score;
				nicks[i] = nick;
				
				i++;
			}
			read.close();
			
			for(int j = scores.length-1; j>=0; j--) {
				if(ChickPanel.points>=scores[j]) {
					if(j+1 < scores.length) {
						scores[j+1] = scores[j];
						nicks[j+1] = nicks[j];
					}					
					scores[j] = ChickPanel.points;
					nicks[j] = ChickPanel.player;
				}
			}
	}
	//saving your score if it is one of the three best scores
		public static void saveScore() throws FileNotFoundException {
		File highScores = new File("textFiles\\HighScores.txt");
			PrintWriter save = new PrintWriter(highScores);
			for(int n = 0; n<scores.length; n++) {
				save.println(nicks[n]+" "+scores[n]);
			}
			save.close();
			ChickPanel.points = 0;
			ChickPanel.life = 2;
	}	
}
