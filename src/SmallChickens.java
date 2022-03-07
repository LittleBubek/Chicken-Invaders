
public class SmallChickens {
	// drawing new small chickens at the top of the window, if the number of points is bigger than 20
	// then more chickens appear each time
		public void drawingRandomChickens(int counter3) {
			int n;
			if(ChickPanel.points<20)
				n=1;
			else
				n=3;
			if (Menu.menu == false && ChickPanel.pause == false) {
				if (counter3 == 15) {
					int los = 0;
					for (int k = 0; k <= n; k++) {
						los = (int) (Math.random() * 28);
						if (los - 1 >= 0 && los + 1 < ChickPanel.tab.length && ChickPanel.tab[0][los] != 1 && ChickPanel.tab[0][los - 1] != 1
								&& ChickPanel.tab[0][los + 1] != 1 && ChickPanel.tab[0][los] != 4 && ChickPanel.tab[0][los - 1] != 4 && ChickPanel.tab[0][los + 1] != 4
								&& ChickPanel.tab[0][los] != 6 && ChickPanel.tab[0][los - 1] != 6 && ChickPanel.tab[0][los + 1] != 6)
							ChickPanel.tab[0][los] = 1;

					}
				}			
			}
		}
		
		// small chickens fall towards the bottom of the window, if they hit the spaceship we lose one life
		public void fallingChickens(int counter2) {
			if (Menu.menu == false && ChickPanel.pause == false) {
				if (counter2 == 4) {
					for (int i = ChickPanel.tab.length - 1; i >= 0; i--) {
						for (int j = 0; j < ChickPanel.tab[i].length; j++) {
							if (ChickPanel.tab[i][j] == 1 && i + 1 < ChickPanel.tab.length - 1) {
								ChickPanel.tab[i][j] = 0;
								ChickPanel.tab[i + 1][j] = 1;
								if ((i + 1) * 20 >= ChickPanel.shipY && j * 20 >= ChickPanel.shipX && j * 20 <= ChickPanel.shipX + 100) {
									ChickPanel.life--;
									ChickPanel.tab[i + 1][j] = 0;
								}
							}
							if (ChickPanel.tab[i][j] == 1 && i + 1 == ChickPanel.tab.length - 1)
								ChickPanel.tab[i][j] = 0;
						}
					}
				}
			}
		}
		
	// laser shots move towards the top of the screen, if the laser shot meets with the small chicken,
	// the chicken disappear and the number of points raises by one
		public void shootingSmallChickens() {
			if (Menu.menu == false && ChickPanel.pause == false) {
				for (int i = 0; i < ChickPanel.tab.length; i++) {
					for (int j = 0; j < ChickPanel.tab[i].length; j++) {
						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && ChickPanel.tab[i - 1][j] != 1 && ChickPanel.tab[i - 1][j - 1] != 1
								&& ChickPanel.tab[i - 1][j] != 2 && ChickPanel.tab[i - 1][j] != 4 && ChickPanel.tab[i - 1][j - 1] != 4
								&& ChickPanel.tab[i - 1][j - 2] != 4 && ChickPanel.tab[i - 1][j] != 6 && ChickPanel.tab[i - 1][j - 1] != 6
								&& ChickPanel.tab[i - 1][j - 2] != 6) {
							ChickPanel.tab[i][j] = 0;
							ChickPanel.tab[i - 1][j] = 3;
						}
						if (ChickPanel.tab[i][j] == 3 && i - 1 < 0)
							ChickPanel.tab[i][j] = 0;

						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && ChickPanel.tab[i - 1][j] == 1) {
							ChickPanel.tab[i - 1][j] = 0;
							ChickPanel.tab[i][j] = 0;
							ChickPanel.points++;
						}
						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && j - 1 >= 0 && ChickPanel.tab[i - 1][j - 1] == 1) {
							ChickPanel.tab[i - 1][j - 1] = 0;
							ChickPanel.tab[i][j] = 0;
							ChickPanel.points++;
						}
						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && ChickPanel.tab[i - 1][j] == 2) {
							ChickPanel.tab[i - 2][j] = 3;
							ChickPanel.tab[i][j] = 0;
						}
					}
				}
			}
		}		
}
