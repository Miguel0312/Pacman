package application;

public abstract class Fantome extends Entite{
	//Ne pas utiliser des valeur numeriques. Utiliser les variables de type final.
	//Ça permet de mettre à jour tous les valeurs en changeant le code en un seul lieu
	protected final int VITESSE_FANTOME = 100;
	protected Labyrinthe labyrinthe;
	protected final int RAYON = 20;
	
	public Fantome(Labyrinthe labyrinthe) {
		this.labyrinthe = labyrinthe;
		this.setPosition(60,60);
	}
	
	public int[] trouverRoute(int l, int c) {
		/* Implementation de l'algorithme de Dijkstra pour recherche de chemin.
		 *  Retourne la prochaine case vers laquelle le fantôme doit se déplacer pour arriver le plus rapidement
		 *  possible à la case (c,l)
		 */
		
		/* On doit garder dans la mémoire les distances actuelles plus courtes trouvées pour arriver à chaque case,
		 * les cases déjà visitées et la case où on était avant d'arriver à chaque case.
		 */
		int distances[][] = new int[20][20];
		boolean visited[][] = new boolean[20][20];
		int parents[][][] = new int[20][20][2];
		Cases[][] cases = labyrinthe.getMatrice();
		
		/*
		 * Pour l'instant, on ne sait pas s'il est possible d'arriver dans une case, donc toutes les cases
		 * sont à une distance "infinie" et aucune n'a été visitée
		 */
		for(int i=0;i<20;i++)
			for(int j=0;j<20;j++) {
				distances[i][j] = Integer.MAX_VALUE;
				visited[i][j] = false;
			}
		
		/*
		 * On met la case cible comme début du chemin et la case actuelle comme fin, ainsi, à la fin, la case
		 * vers laquelle le fantôme doit se déplacer c'est le parent de la case actuelle
		 */
		int targetX = x/(2*RAYON), targetY = y/(2*RAYON);
		int currentX = c, currentY=l;
		distances[currentY][currentX] = 0;
		
		// Tandis qu'on n'est pas arrivé à la fin du chemin et il y a des cases à parcourir
		while((currentX!=targetX || currentY!=targetY)&&distances[currentY][currentX]<Integer.MAX_VALUE) {
			visited[currentY][currentX] = true;
			
			int tempDistance = distances[currentY][currentX]+1;
			
			/* 
			 * La distance minimale pour arriver à chaque case voisine à la case actuelle est le minimum 
			 * de la distance déjà trouvée et celle en passant par la case actuelle.
			 * Si la nouvelle distance calculée est minimale, on met à jour le parent de cette case
			 */
			if(currentX>0 && !visited[currentY][currentX-1] && cases[currentY][currentX-1]!=Cases.MUR && tempDistance<distances[currentY][currentX-1]) {
				distances[currentY][currentX-1] = tempDistance;
				parents[currentY][currentX-1][0] = currentX;
				parents[currentY][currentX-1][1] = currentY;
			}
			if(currentX<19 && !visited[currentY][currentX+1] && cases[currentY][currentX+1]!=Cases.MUR && tempDistance<distances[currentY][currentX+1]) {
				distances[currentY][currentX+1] = tempDistance;
				parents[currentY][currentX+1][0] = currentX;
				parents[currentY][currentX+1][1] = currentY;
			}
			if(currentY>0 && !visited[currentY-1][currentX] && cases[currentY-1][currentX]!=Cases.MUR && tempDistance<distances[currentY-1][currentX]) {
				distances[currentY-1][currentX] = tempDistance;
				parents[currentY-1][currentX][0] = currentX;
				parents[currentY-1][currentX][1] = currentY;
			}
			if(currentY<19 && !visited[currentY+1][currentX] && cases[currentY+1][currentX]!=Cases.MUR && tempDistance<distances[currentY+1][currentX]) {
				distances[currentY+1][currentX] = tempDistance;
				parents[currentY+1][currentX][0] = currentX;
				parents[currentY+1][currentX][1] = currentY;
			}
			
			/* 
			 * La prochaine case qui sera visitée c'est celle qui est le plus proche possoble de la case de départ 
			 * et qui n'a pas été visitée
			*/
			int minDistance = Integer.MAX_VALUE;
			for(int i=0;i<20;i++)
				for(int j=0;j<20;j++) {
					if(!visited[i][j] && cases[i][j]!=Cases.MUR && distances[i][j]<minDistance) {
						minDistance = distances[i][j];
						currentX = j;
						currentY = i;
					}
				}
		}
		
		return parents[targetY][targetX];
	}
}
