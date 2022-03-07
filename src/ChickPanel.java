import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

class ChickPanel extends JPanel {

	Image chicken;
	static Image spaceship;
	Image blaster;
	static Image cosmos;
	Image cross;
	Image laser;
	Image bigChicken;
	Image egg;
	static int shipX = 260;
	static int shipY = 520;
	static int[][] tab = new int[30][30];
	static int points = 0;
	static int life = 2;
	static String player = "";
	static boolean upgrade = false;
	static boolean pause = false;
	static boolean scoresTable = false;
	static boolean nameField = false;
	static boolean name = false;
	Laser shot = new Laser();
	SmallChickens sChick = new SmallChickens();
	BigChickens bChick = new BigChickens();
	FirstAidKit aid = new FirstAidKit();
	Menu start = new Menu();
	
	public ChickPanel() throws FileNotFoundException {
		chicken = new ImageIcon("images\\OrdinaryChick.png").getImage();
		spaceship = new ImageIcon("images\\spaceship1.png").getImage();
		blaster = new ImageIcon("images\\IonBlaster.png").getImage();
		cosmos = new ImageIcon("images\\space.png").getImage();
		cross = new ImageIcon("images\\cross.png").getImage();
		laser = new ImageIcon("images\\laser.png").getImage();
		bigChicken = new ImageIcon("images\\BigChicken.png").getImage();
		egg = new ImageIcon("images\\egg.png").getImage();
	}
	
// if we gain 25 points then the spaceship gets an upgrade and the number of our lives rises to 10
	public void spaceshipUpgrade() {
		if(points == 25) {
			spaceship = new ImageIcon("images\\spaceship2.png").getImage();	
			life += 5;
			upgrade = true;
		}
	}

// all the counters rise by one every 100 ms, some of the methods use the counters to measure time,
// so certain things like shooting are repeated
	public void timer() {
		
			int counter = 0;
			int counter2 = 0;
			int counter3 = 0;
			int counter4 = 0;
			int counterFirstAid = 0;
			int counterBigChicken = 0;
			int counterChickenShooting = 0;
			int counterEgg = 0;
			int counterUpgrade = 0;
			while (true) {
				try {
					Thread.sleep(100);
					counter++;
					counter2++;
					counter3++;
					counter4++;
					counterFirstAid++;
					counterBigChicken++;
					counterChickenShooting++;
					counterEgg++;
					counterUpgrade++;
					
					shot.drawingBlast(counter, counterUpgrade);
					if (counter >= 3)
						counter = 0;
					if (counterUpgrade >= 2)
						counterUpgrade = 0;

					sChick.shootingSmallChickens();
					bChick.shootingBigChickensFirst();
					bChick.shootingBigChickensSecond();

					sChick.drawingRandomChickens(counter3);
					sChick.fallingChickens(counter2);
					if (counter3 >= 15)
						counter3 = 0;
					if (counter2 >= 4)
						counter2 = 0;

					aid.firstAidKit(counterFirstAid, counter4);
					if (counterFirstAid >= 40)
						counterFirstAid = 0;
					if (counter4 >= 4)
						counter4 = 0;

					bChick.bigChicken(counterBigChicken);
					bChick.bigChickenShooting(counterChickenShooting, counterEgg);
					if (counterBigChicken >= 20)
						counterBigChicken = 0;
					if (counterChickenShooting >= 30)
						counterChickenShooting = 0;
					if (counterEgg >= 3)
						counterEgg = 0;

					repaint();

				} catch (Exception e) {
				}
			}
	}
	
	public void name(Graphics g) {
		if(name == true) {
		g.drawImage(cosmos, 0, 0, 600, 1200, this);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g.drawString("To start a new game enter", 50, 200);
		g.drawString("your name and press ENTER", 50, 300);
		}
	}

	public void showHighScores(Graphics g) throws FileNotFoundException {
		if(scoresTable == true) {
			int y = 200;
			int a = 0;
			int number = 1;
			g.drawImage(ChickPanel.cosmos, 0, 0, 600, 1200, (ImageObserver) this);
			Menu.readHighScores();
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g.drawString("High scores: ", 175, 100);
			for(int i =0; i<Menu.scores.length; i++) {
				g.drawString(number+". "+Menu.nicks[i]+"   "+Menu.scores[i], 200, y+a);
				a += 100;
				number ++;
			}
			g.fillRect(200, 450, 200, 100);
			g.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g.drawString("MENU", 225, 515);
		}
	}
		
// drawing the number of lives and points
	public void drawingStrings(Graphics g) {
		if (Menu.menu == false) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.drawString(player, 500, 530);
			g.drawString("Points: " + points, 500, 590);
			g.drawString("Lives: " + life, 500, 560);

			if (life < 1) {
				Menu.menu = true;
			}
			if (points>=20) {
				Menu.menu = true;
			}
		}
	}
	
// drawing all the graphic elements
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(cosmos, 0, 0, 600, 1200, this);
		Menu.menu(g);
		start.gameOver(g);
		start.victory(g);
		name(g);
		try {
			showHighScores(g);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Menu.menu == false) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				if (tab[i][j] == 1)
					g.drawImage(chicken, j * 20, i * 20, 40, 40, this);
				if (tab[i][j] == 4 || tab[i][j] == 6)
					g.drawImage(bigChicken, j * 20, i * 20, 60, 60, this);
				if (tab[i][j] == 3)
					g.drawImage(blaster, j * 20, i * 20, 20, 40, this);
				if (tab[i][j] == 2)
					g.drawImage(cross, j * 20, i * 20, 20, 20, this);
				if (tab[i][j] == 5)
					g.drawImage(laser, j * 20, i * 20, 20, 500, this);
				if (tab[i][j] == 7)
					g.drawImage(egg, j * 20, i * 20, 20, 20, this);
			}
		}

		g.drawImage(spaceship, shipX, shipY, 80, 80, this);

		shot.laserShooting();
		spaceshipUpgrade();
		drawingStrings(g);
		}
		repaint();
	}
	
// press 'p' to pause the game
	public static void pause () {
		if (pause == false)
			pause = true;
		else
			pause = false;
	}

// saving the game if you press 's'
	public static void save() throws FileNotFoundException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm");
		String date = LocalDateTime.now().format(format);
		PrintWriter save = new PrintWriter("textFiles\\Chicken_"+date+".txt");	
		for (int i = 0; i<tab.length; i++) {
			for(int j = 0; j<tab[i].length; j++) {
				save.println(tab[i][j]);
			}
		}
		save.println(shipX);
		save.println(shipY);
		save.println(points);
		save.println(life);
		save.println(player);
		save.close();
	}
	
// loading the game that was saved before
	public static void read() throws FileNotFoundException {
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		String savedFile = " ";
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       savedFile = chooser.getSelectedFile().getName();
	    }
	 
		Scanner read = new Scanner(new File("textFiles\\"+savedFile));
		String tabTemp;
		String shipXTemp;
		String shipYTemp;
		String pointsTemp;
		String lifeTemp;
		for(int i = 0; i<tab.length; i++)
			for(int j = 0; j<tab.length; j++) {
				tabTemp = read.nextLine();
				try {
					tab[i][j] = Integer.parseInt(tabTemp);
				}
				catch(NumberFormatException ex){
		            ex.printStackTrace();
		        }
			}
		shipXTemp = read.nextLine();
		shipYTemp = read.nextLine();
		pointsTemp = read.nextLine();
		lifeTemp = read.nextLine();
		try {
			shipX = Integer.parseInt(shipXTemp);
			shipY = Integer.parseInt(shipYTemp);
			points = Integer.parseInt(pointsTemp);
			life = Integer.parseInt(lifeTemp);
		}
		catch(NumberFormatException ex){
            ex.printStackTrace();
        }
		player = read.nextLine();
		read.close();
	}
	
// setting the width and height of the window
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}
}