
public class Board {
	
	private Symbol[][] grid;
	
	public Board() {
		this.grid = new Symbol[6][7];
		initializeGrid();
	}
	
	/**
	 * Remplir la grille du symbole case vide
	 */
	public void initializeGrid() {
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) 
				grid[x][y] = Symbol.VIDE;
		}
	}
	/**
	 * Verifier si une colonne est pleine
	 * @param n Le numéro de la colonne
	 * @return Vrai ou Faux
	 */
	public boolean isColumnFull(int n) {
		return grid[0][n] != Symbol.VIDE;
	}

	/**
	 * Obtenir la position libre dans une colonne
	 * @param n Le numéro de la colonne
	 * @return Une position
	 */
	public Position getEmptyPositionForColumn(int n) {
		int y = grid.length - 1;
		while(grid[y][n] != Symbol.VIDE)
			y--;
		return new Position(n, y);
	}

	/**
	 * Assigne un symbol à une position dans la grille
	 * @param symbol le symbole à placer
	 * @param pos la position ou se trouvera le symbole
	 */
	public void setSymbolAtPosition(Symbol symbol, Position pos){
		this.grid[pos.y][pos.x] = symbol;
	}
	public Symbol[][] getGrid(){
		return this.grid;
	}
	
	public void setGrid(Symbol[][] grid) {
		this.grid = grid;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" 0 1 2 3 4 5 6\n");
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				sb.append("|"  + grid[y][x] );
			}
			sb.append("|\n");
		}
		return sb.toString();
	}

}
