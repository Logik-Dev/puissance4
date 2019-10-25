import java.util.ArrayList;
import java.util.List;

public class Player {

	private final String name;
	private int nbreJetons;
	private final List<Position> playedPositions;
	private final Symbol symbol;
	private final Board board;
	
	public Player(String name, Symbol symbol, Board board) {
		this.nbreJetons = 21;
		this.playedPositions = new ArrayList<>();
		this.name = name;
		this.symbol = symbol;
		this.board = board;
	}

	/**
	 * Ajouter un jeton dans la colonne, décrémenter le total de jetons
	 * et ajouter la position dans le tableau des positions jouées
	 * @param column La colonne ou déposer le jeton
	 */
	public void playAtColumn(int column){

		Position position = board.getEmptyPositionForColumn(column);

		board.setSymbolAtPosition(symbol, position);
		playedPositions.add(position);
		nbreJetons--;
	}
	
	/**
	 * Parcourir les positions jouées et les compter selon leur
	 * pour savoir si le joueur a gagné
	 * @return True si le joueur a 4 jetons consécutifs ou False sinon
	 */
	public boolean hasWin() {

		int vCount, hCount, dUpCount, dDownCount;

		if(playedPositions.size() >= 4) {

			for(int i = 0; i < playedPositions.size(); i++) {

				Position currentJeton = playedPositions.get(i);
				vCount = 0; hCount = 0; dUpCount = 0; dDownCount = 0;

				for(int y = 1 ; y <= 3; y++) {

					Position vToFind = new Position(currentJeton.x, currentJeton.y + y);
					Position hToFind = new Position(currentJeton.x + y, currentJeton.y);
					Position dUpToFind = new Position(currentJeton.x + y, currentJeton.y - y);
					Position dDownToFind = new Position(currentJeton.x + y, currentJeton.y + y);

					if(playedPositions.indexOf(vToFind) >= 0)
						vCount++;

					if(playedPositions.indexOf(hToFind) >= 0)
						hCount++;

					if(playedPositions.indexOf(dUpToFind) >= 0)
						dUpCount++;

					if(playedPositions.indexOf(dDownToFind) >= 0)
						dDownCount++;
				}

				if(vCount == 3 || hCount == 3 || dUpCount == 3 || dDownCount == 3) 
						return true;
			}
		}
		return false;
		
	}

	public String getName() {
		return name;
	}

	public int getNbreJetons() {
		return nbreJetons;
	}

	public List<Position> getPlayedPositions() {
		return playedPositions;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public Board getBoard() {
		return board;
	}

	public void decrementNumOfCoin(){
		this.nbreJetons--;
	}
}
