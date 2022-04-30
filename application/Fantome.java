package application;

public abstract class Fantome extends Entite {
	// Ne pas utiliser des valeur numeriques. Utiliser les variables de type final.
	// �a permet de mettre � jour tous les valeurs en changeant le code en un seul
	// lieu
	protected final int VITESSE_FANTOME = 120;
	protected Labyrinthe labyrinthe;
	protected final int RAYON = 20;
	protected int[] prochain;
	protected int[] cible;
	boolean nouvelleCible;
	protected boolean fuite;
	protected int timerFuite = 0;
	protected int[] cibleFuite;
	protected int timerDebut;
	PacMan pacman;

	public Fantome(Labyrinthe labyrinthe, PacMan pacman) {
		this.labyrinthe = labyrinthe;
		this.pacman = pacman;
		this.fuite = false;
		prochain = new int[2];
		cible = new int[2];
		nouvelleCible = false;
	}

	public int[] trouverRoute(int l, int c) {
		/*
		 * Implementation de l'algorithme de Dijkstra pour recherche de chemin. Retourne
		 * la prochaine case vers laquelle le fant�me doit se d�placer pour arriver le
		 * plus rapidement possible � la case (c,l)
		 */

		/*
		 * On doit garder dans la m�moire les distances actuelles plus courtes trouv�es
		 * pour arriver � chaque case, les cases d�j� visit�es et la case o� on �tait
		 * avant d'arriver � chaque case.
		 */
		int distances[][] = new int[20][20];
		boolean visited[][] = new boolean[20][20];
		int parents[][][] = new int[20][20][2];
		Cases[][] cases = labyrinthe.getMatrice();

		/*
		 * Pour l'instant, on ne sait pas s'il est possible d'arriver dans une case,
		 * donc toutes les cases sont � une distance "infinie" et aucune n'a �t� visit�e
		 */

		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				distances[i][j] = Integer.MAX_VALUE;
				visited[i][j] = false;
			}

		/*
		 * On met la case cible comme d�but du chemin et la case actuelle comme fin,
		 * ainsi, � la fin, la case vers laquelle le fant�me doit se d�placer c'est le
		 * parent de la case actuelle
		 */
		int targetX = x / (2 * RAYON), targetY = y / (2 * RAYON);
		int currentX = c, currentY = l;
		distances[currentY][currentX] = 0;

		if (targetX == c && targetY == l)
			return new int[] { c, l };

		// Tandis qu'on n'est pas arriv� � la fin du chemin et il y a des cases �
		// parcourir
		while ((currentX != targetX || currentY != targetY) && distances[currentY][currentX] < Integer.MAX_VALUE) {
			visited[currentY][currentX] = true;
			int tempDistance = distances[currentY][currentX] + 1;

			/*
			 * La distance minimale pour arriver � chaque case voisine � la case actuelle
			 * est le minimum de la distance d�j� trouv�e et celle en passant par la case
			 * actuelle. Si la nouvelle distance calcul�e est minimale, on met � jour le
			 * parent de cette case
			 */
			if (currentX > 0 && !visited[currentY][currentX - 1] && cases[currentY][currentX - 1] != Cases.MUR
					&& tempDistance < distances[currentY][currentX - 1]) {
				distances[currentY][currentX - 1] = tempDistance;
				parents[currentY][currentX - 1][0] = currentX;
				parents[currentY][currentX - 1][1] = currentY;
			}
			if (currentX < 19 && !visited[currentY][currentX + 1] && cases[currentY][currentX + 1] != Cases.MUR
					&& tempDistance < distances[currentY][currentX + 1]) {
				distances[currentY][currentX + 1] = tempDistance;
				parents[currentY][currentX + 1][0] = currentX;
				parents[currentY][currentX + 1][1] = currentY;
			}
			if (currentY > 0 && !visited[currentY - 1][currentX] && cases[currentY - 1][currentX] != Cases.MUR
					&& tempDistance < distances[currentY - 1][currentX]) {
				distances[currentY - 1][currentX] = tempDistance;
				parents[currentY - 1][currentX][0] = currentX;
				parents[currentY - 1][currentX][1] = currentY;
			}
			if (currentY < 19 && !visited[currentY + 1][currentX] && cases[currentY + 1][currentX] != Cases.MUR
					&& tempDistance < distances[currentY + 1][currentX]) {
				distances[currentY + 1][currentX] = tempDistance;
				parents[currentY + 1][currentX][0] = currentX;
				parents[currentY + 1][currentX][1] = currentY;
			}

			/*
			 * La prochaine case qui sera visit�e c'est celle qui est le plus proche
			 * possoble de la case de d�part et qui n'a pas �t� visit�e
			 */
			int minDistance = Integer.MAX_VALUE;
			for (int i = 0; i < 20; i++)
				for (int j = 0; j < 20; j++) {
					if (!visited[i][j] && cases[i][j] != Cases.MUR && distances[i][j] < minDistance) {
						minDistance = distances[i][j];
						currentX = j;
						currentY = i;
					}
				}
		}

		return parents[targetY][targetX];
	}

	public void commencerFuite() {
		this.fuite = true;
		this.timerFuite = 5000;
	}

	// Ne fonctionne pas pour l'instant
	/*
	 * public void routeCible(int x ,int y) { int[] i= {x*40+20,y*40+20};
	 * while(getPosition()!=i) { this.cible=i; } }
	 */
}
