public class FirstAidKit {
	// a first aid kit appears in a random place at the top of the window
		public void firstAidKit(int counterFirstAid, int counter4) {
			int los = 0;
			if (Menu.menu == false && ChickPanel.pause == false) {
				if (counterFirstAid == 40) {
					for (int k = 0; k < 1; k++) {
						los = (int) (Math.random() * 28);
						if (los - 1 >= 0 && ChickPanel.tab[0][los] != 1 && ChickPanel.tab[0][los - 1] != 1 && ChickPanel.tab[0][los] != 4
								&& ChickPanel.tab[0][los - 1] != 4 && ChickPanel.tab[0][los] != 6 && ChickPanel.tab[0][los - 1] != 6)
							ChickPanel.tab[0][los] = 2;
					}
				}
	// the first aid kit is falling down, if it hits the spaceship we gain one life
				if (counter4 == 4) {
					for (int i = ChickPanel.tab.length - 1; i >= 0; i--) {
						for (int j = 0; j < ChickPanel.tab[i].length; j++) {
							if (ChickPanel.tab[i][j] == 2) {
								ChickPanel.tab[i][j] = 0;
								ChickPanel.tab[i + 1][j] = 2;
								if ((i + 1) * 20 >= ChickPanel.shipY && j * 20 >= ChickPanel.shipX && j * 20 <= ChickPanel.shipX + 100) {
									ChickPanel.life++;
									ChickPanel.tab[i + 1][j] = 0;
								}
							}
							if (ChickPanel.tab[i][j] == 2 && i + 1 == ChickPanel.tab.length - 1)
								ChickPanel.tab[i][j] = 0;
						}
					}
				}
			}
		}
}
