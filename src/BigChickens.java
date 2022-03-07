
public class BigChickens {
	// a big chicken appears at the top of the window in a random place
		public void bigChicken(int counterBigChicken) {
			if (Menu.menu == false && ChickPanel.pause == false) {
				if (counterBigChicken == 20) {
					int los1 = 0;
					for (int k = 0; k < 1; k++) {
						los1 = (int) (Math.random() * 28);
						if (los1 - 2 >= 0 && los1 + 2 < ChickPanel.tab.length && ChickPanel.tab[0][los1] != 1 && ChickPanel.tab[0][los1 - 1] != 1
								&& ChickPanel.tab[0][los1 + 1] != 1 && ChickPanel.tab[0][los1] != 4 && ChickPanel.tab[0][los1 - 1] != 4
								&& ChickPanel.tab[0][los1 - 2] != 4 && ChickPanel.tab[0][los1 + 1] != 4 && ChickPanel.tab[0][los1 + 2] != 4
								&& ChickPanel.tab[0][los1] != 6 && ChickPanel.tab[0][los1 - 1] != 6 && ChickPanel.tab[0][los1 - 2] != 6
								&& ChickPanel.tab[0][los1 + 1] != 6 && ChickPanel.tab[0][los1 + 2] != 6)
							ChickPanel.tab[0][los1] = 4;
					}
				}
			}
		}

	// the big chicken can shoot using eggs that appear under the chicken
		public void bigChickenShooting(int counterChickenShooting, int counterEgg) {
			if (Menu.menu == false && ChickPanel.pause == false) {
				if (counterChickenShooting == 30) {
					for (int i = 0; i < ChickPanel.tab.length; i++)
						for (int j = 0; j < ChickPanel.tab[i].length; j++) {

							if (ChickPanel.tab[i][j] == 4 || ChickPanel.tab[i][j] == 6)
								ChickPanel.tab[i + 3][j + 1] = 7;
						}
				}
	// the egg is falling down, if it meets with the spaceship we lose one life
				if (counterEgg == 3) {
					for (int i = ChickPanel.tab.length - 1; i >= 0; i--)
						for (int j = ChickPanel.tab.length - 1; j >= 0; j--) {
							if (ChickPanel.tab[i][j] == 7 && i + 1 < ChickPanel.tab.length - 1) {
								ChickPanel.tab[i + 1][j] = 7;
								ChickPanel.tab[i][j] = 0;
								if ((i + 1) * 20 >= ChickPanel.shipY && j * 20 >= ChickPanel.shipX && j * 20 <= ChickPanel.shipX + 100) {
									ChickPanel.life--;
									ChickPanel.tab[i + 1][j] = 0;
								}
							}
							if (ChickPanel.tab[i][j] == 7 && i + 1 == ChickPanel.tab.length - 1)
								ChickPanel.tab[i][j] = 0;
						}
				}
			}
		}

	// laser shot must hit the big chicken two times to kill it and then we gain a point
	// here we shoot the big chicken for the first time
		public void shootingBigChickensFirst() {
			if (Menu.menu == false && ChickPanel.pause == false) {
				for (int i = 0; i < ChickPanel.tab.length; i++) {
					for (int j = 0; j < ChickPanel.tab[i].length; j++) {
						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && ChickPanel.tab[i - 1][j] == 4) {
							ChickPanel.tab[i - 1][j] = 6;
							ChickPanel.tab[i][j] = 0;
						}
						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && j - 1 >= 0 && ChickPanel.tab[i - 1][j - 1] == 4) {
							ChickPanel.tab[i - 1][j - 1] = 6;
							ChickPanel.tab[i][j] = 0;
						}
						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && j - 2 >= 0 && ChickPanel.tab[i - 1][j - 2] == 4) {
							ChickPanel.tab[i - 1][j - 2] = 6;
							ChickPanel.tab[i][j] = 0;
						}
					}
				}
			}
		}
		
	// here we shoot the big chicken for the second time	
		public void shootingBigChickensSecond() {
			if (Menu.menu == false && ChickPanel.pause == false) {
				for (int i = 0; i < ChickPanel.tab.length; i++) {
					for (int j = 0; j < ChickPanel.tab[i].length; j++) {
						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && ChickPanel.tab[i - 1][j] == 6) {
							ChickPanel.tab[i - 1][j] = 0;
							ChickPanel.tab[i][j] = 0;
							ChickPanel.points++;
						}
						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && j - 1 >= 0 && ChickPanel.tab[i - 1][j - 1] == 6) {
							ChickPanel.tab[i - 1][j - 1] = 0;
							ChickPanel.tab[i][j] = 0;
							ChickPanel.points++;
						}
						if (ChickPanel.tab[i][j] == 3 && i - 1 >= 0 && j - 2 >= 0 && ChickPanel.tab[i - 1][j - 2] == 6) {
							ChickPanel.tab[i - 1][j - 2] = 0;
							ChickPanel.tab[i][j] = 0;
							ChickPanel.points++;
						}
					}
				}
			}
		}	
}
