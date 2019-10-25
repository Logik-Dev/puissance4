import javax.imageio.ImageWriteParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Computer extends Player {
	
	private List<Position> playablePositions;
	private List<Position> oppositePositions;
	private Position positionToPlay;
	private int maxCount = 0;
	
	public Computer(String nom, Symbol symbol, Board board) {

		super(nom, symbol, board);
	}
	
	/**
	 * Définir les tableaux :
	 * - Positions jouables: playablePositions
	 * - Positions adverses jouées: oppositePositions
	 */
	private void definePositions(){
		
		List<Position> playables = new ArrayList<>();
		List<Position> opposites = new ArrayList<>();
		Symbol[][] grid = board.getGrid();

		// Parcourir la grille de jeu
		for(int y = 0; y < grid.length; y++) {

			for(int x = 0; x < grid[y].length; x++) {

				Position position = new Position(x, y);

				// si la position n'est ni vide ni le code couleur de l'ordinateur
				if(grid[y][x] != Symbol.VIDE && grid[y][x] != this.symbol) 
					opposites.add(position); // on l'ajoute aux positions adverses

				// si la position est vide
				if(grid[y][x] == Symbol.VIDE) {

					// si la postion du dessous n'est pas vide
					if(y < 5 && grid[y + 1][x] != Symbol.VIDE)
						playables.add(position); // on l'ajoute aux positions jouables

					// si la positon est la dernière de la colonne
					else if(y == 5)
						playables.add(position); // on l'ajoute aux positions jouables
				}
			}
		}
		
		this.playablePositions = playables;
		this.oppositePositions = opposites;
	}
	
	/**
	 * Définir la position de jeu dangereuse ou gagnante
	 * en la comparant aux positions voisines
	 * @param positionsToSearchIn Le tableau de positions jouées du joueur ou de l'ordinateur
	 * @param emptyPosition La position disponible à comparer
	 * @return  Un tableau contenant une position ou il y a le plus de jetons adjacents de meme symbole et ce nombre
	 */
	private Object[] countDangerousAndFindBetterPositions(List<Position> positionsToSearchIn, Position emptyPosition) {

		int[] counts = {0,0,0,0};

		for(int i = 1; i <= 3; i++) {

			// Test horizontal
			if(positionsToSearchIn.indexOf(new Position(emptyPosition.x + i, emptyPosition.y )) >= 0)
				counts[1] = ++counts[1];
			if(positionsToSearchIn.indexOf(new Position(emptyPosition.x - i, emptyPosition.y )) >= 0)
				counts[1] = ++counts[1];

			// Test vertical
			if(positionsToSearchIn.indexOf(new Position(emptyPosition.x, emptyPosition.y + i)) >= 0)
				counts[0] = ++counts[0];
			if(positionsToSearchIn.indexOf(new Position(emptyPosition.x, emptyPosition.y -i)) >= 0)
				counts[0] = ++counts[0];

			// Tests diagonal A
			if(positionsToSearchIn.indexOf(new Position(emptyPosition.x + i, emptyPosition.y - i)) >= 0)
				counts[2] = ++counts[2];
			if(positionsToSearchIn.indexOf(new Position(emptyPosition.x - i, emptyPosition.y + i)) >= 0)
				counts[2] = ++counts[2];

			// Tests diagonal B
			if(positionsToSearchIn.indexOf(new Position(emptyPosition.x - i, emptyPosition.y - i)) >= 0)
				counts[3] = ++counts[3];
			if(positionsToSearchIn.indexOf(new Position(emptyPosition.x + i, emptyPosition.y + i)) >= 0)
				counts[3] = ++counts[3];	
		}

		int max = Arrays.stream(counts).max().getAsInt();
		return new Object[]{max, emptyPosition};
	}
	
	/**
	 * Parcourir le tableau de positions jouables et déterminer
	 * laquelle jouer en fonction du nombre de jetons à côté
	 * @param positionsToSearchIn Le tableau de positions du joueur ou de l'ordinateur
	 */
	private void iteratePlayablePositions(List<Position> positionsToSearchIn) {

		int max = 0;
		Object[][] betterPositions = new Object[playablePositions.size()][2];

		// Obtenir les meilleurs positions
		for(int i = 0; i < playablePositions.size(); i++) {

			Position current = playablePositions.get(i);
			Object[] counts = countDangerousAndFindBetterPositions(positionsToSearchIn, current);

			if((int)counts[0] >= max) {
				max = (int)counts[0];
				betterPositions[i][0] = max;
				betterPositions[i][1] = counts[1];
			}
		}

		// Définir une position aléatoire
		List <Position> positions = new ArrayList<>();

		for (Object[] betterPosition : betterPositions) {

			if (betterPosition[0] != null && (int) betterPosition[0] >= max)
				positions.add((Position) betterPosition[1]);
		}

		int randomIndex = (int)(Math.random() * positions.size());
		this.positionToPlay = positions.get(randomIndex);
		this.maxCount = max;
	}
	
	/**
	 * Gestion du comportement de jeu de l'IA
	 */
	public void play(){
		
		// Initialiser les tableaux de positions et variables d'instances
		definePositions();

		if(playedPositions.size() == 0 && oppositePositions.size() == 0)
			playRandom();
		
		else {
			// Récupérer son propre nombre de jetons alignés et la position a jouer pour gagner
			iteratePlayablePositions(playedPositions);
			int selfCount = maxCount;
			Position selfPosition = positionToPlay;


			// Récuperer le nombre de jetons alignés de l'adversaire et la position à joueur pour le contrer
			iteratePlayablePositions(oppositePositions);
			int oppositeCount = maxCount;
			Position blockingPosition = positionToPlay; 


			// Décider des priorités
			if(selfCount == 3)
				playAtPosition(selfPosition);

			else if(oppositePositions.size() == 1 && oppositePositions.contains(new Position(3, 5)))
				playAtPosition(new Position(2, 5));

			else if (oppositeCount == 3)
				playAtPosition(blockingPosition);

			else
				playAtPosition(selfPosition);
		}
	}
	
	/**
	 * Jouer aléatoirement
	 */
	private void playRandom() {

		int randomIndex = (int)(Math.random() * this.board.getGrid()[0].length);
		Position position = this.board.getEmptyPositionForColumn(randomIndex);

		playAtPosition(position);
	}
	
	/**
	 * Jouer sur une position définie
	 * @param position La position à jouer
	 */
	private void playAtPosition(Position position) {

		board.setSymbolAtPosition(this.symbol, position);
		playedPositions.add(position);
		this.nbreJetons--;
		
	}

	
}
