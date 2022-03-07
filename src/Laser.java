
public class Laser {

	// drawing a new short laser shot in front of the spaceship
		public void drawingBlast(int counter, int counterUpgrade) {
			if (Menu.menu == false && ChickPanel.pause == false) {
				if (counter == 3 && ChickPanel.upgrade == false) {
					for (int i = 0; i < ChickPanel.tab.length; i++)
						for (int j = 0; j < ChickPanel.tab[i].length; j++) {
							if (j == (ChickPanel.shipX + 40) / 20 && i == (ChickPanel.shipY - 20) / 20)
								ChickPanel.tab[i][j] = 3;
						}
				}
	// if the ship is already upgraded, shots appear more often
				if (counterUpgrade == 2 && ChickPanel.upgrade == true && ChickPanel.pause == false) {
					for (int i = 0; i < ChickPanel.tab.length; i++)
						for (int j = 0; j < ChickPanel.tab[i].length; j++) {
							if (j == (ChickPanel.shipX + 40) / 20 && i == (ChickPanel.shipY - 20) / 20)
								ChickPanel.tab[i][j] = 3;
						}
				}
			}
		}
		
	// if we press space on the keyboard we can use a big laser weapon that can kill all the chicken
	// that it meets going from the bottom to the top of the window - we gain points
		public void laserShooting() {
			if (Menu.menu == false && ChickPanel.pause == false) {
				for (int i = 0; i < ChickPanel.tab.length; i++)
					for (int j = 0; j < ChickPanel.tab[i].length; j++) {
						if (ChickPanel.tab[i][j] == 5) {

							for (int k = 0; k < ChickPanel.tab.length; k++)
								for (int l = 0; l < ChickPanel.tab[k].length; l++) {
									if (ChickPanel.tab[k][l] == 1 && l == j) {
										ChickPanel.tab[k][l] = 0;
										ChickPanel.points++;
									}
									if (l - 1 >= 0 && ChickPanel.tab[k][l - 1] == 1 && l == j - 1) {
										ChickPanel.tab[k][l - 1] = 0;
										ChickPanel.points++;
									}
								}
						}
					}
			}
		}
}
