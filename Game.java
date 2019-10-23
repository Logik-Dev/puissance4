
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	
	Board board;
	Player[] players;
	Scanner sc = new Scanner(System.in);
	Player playerA, playerB;
	boolean gameIsFinished = false;
	
	
	/**
	 * Sélectionner aléatoirement une valeur entière entre 0 et 1
	 * @return  0 ou 1
	 */
	public int randomize() {
		return (int) (Math.random() * 2);	
	}
	
	/**
	 * Définir les conditions de fin de jeu
	 * @return True si le jeu est fini sinon False
	 */
	public boolean isFinished() {
		if(playerA.hasWin() || playerB.hasWin())
			return true;
		if(playerA.nbreJetons == 0 && playerB.nbreJetons == 0)
			return true;
		return false;
	}
	
	/**
	 * Initialiser les couleurs, les symboles et les affectent aux joueurs  aléatoirement
	 * @param nameA Nom du joueur A
	 * @param nameB Nom du joueur B
	 */
	public void initialize(String nameA, String nameB) {
		Symbol symbolA = randomize() == 0 ? Symbol.JAUNE : Symbol.ROUGE;
		Symbol symbolB = symbolA == Symbol.JAUNE ? Symbol.ROUGE :Symbol.JAUNE;
		int positionA = randomize();
		int positionB = positionA == 0 ? 1 : 0;
		players[positionA] = new Player(nameA, symbolA, this.board);
		players[positionB] = nameB.equals("Ordinateur") ? new Computer(nameB, symbolB, this.board) : new Player(nameB, symbolB, this.board);
		playerA = players[0];
		playerB = players[1];
	}
	
	/**
	 * Démarrer la partie et saisir les choix utilisateurs
	 * @throws InterruptedException
	 */
	public void start() throws InterruptedException {
		System.out.println("Bonjour ! \n1)Joueur contre Joueur\n2)Joueur contre Ordinateur");
		int choice = sc.nextInt();
		System.out.println("Entrez le nom du premier joueur:");
		sc.nextLine();
		String nameA =  sc.nextLine();
		if(choice == 1)
			System.out.println("Entrez le nom du second joueur:");
		String nameB = choice == 1 ? sc.nextLine() : "Ordinateur";
		initialize(nameA, nameB);
		jouerManche();
	}
	
	/**
	 * Jouer le tour d'un joueur
	 * @param j Le joueur qui doit jouer
	 * @throws InterruptedException
	 */
	public void jouerTourSolo(Player j) throws InterruptedException{
		gameIsFinished = isFinished();

		if(!gameIsFinished) {
			Symbol[][] newGrid;
			System.out.println("Au tour de "+ j.name);
			Thread.sleep(1500);

			if(!(j instanceof Computer)) {
				int column = -1;
					do {
						System.out.println(j.name + " quelle colonne choisi tu (0 à 6)?");
						while(!sc.hasNextInt()) {
							System.out.println("Ce n'est pas un chiffre, merci de recommencer:");
							sc.next();
						}
						column = sc.nextInt();
					} while (column > 6 || column < 0 );

				while (board.isColumnFull(column)) {
					System.out.println("Cette colonne est pleine, merci d'en choisir une autre:");
					column = sc.nextInt();
				}
				j.playAtColumn(column);
			}
			else 
				((Computer)j).play();

			System.out.println(board);
		}
	}
	/**
	 * Déroulement de la partie
	 * @throws InterruptedException
	 */
	public void jouerManche() throws InterruptedException{
		String colorA = playerA.symbol == Symbol.JAUNE ? "jaune" : "rouge";
		String colorB = colorA == "jaune" ? "rouge" : "jaune";
		System.out.println(playerA.name + " commencera avec la couleur "  + colorA + " et le symbole " + playerA.symbol);
		System.out.println(playerB.name + " sera second avec la couleur " + colorB + " et le symbole " + playerB.symbol);
		Thread.sleep(1500);
		System.out.println(board);
		
		while(!gameIsFinished) {
			jouerTourSolo(playerA);
			jouerTourSolo(playerB);					
		}

		if(gameIsFinished) {
			if(playerA.hasWin() || playerB.hasWin()) {
				String winner = " est le gagnant !";
				winner = playerA.hasWin() ? playerA.name + winner : playerB.name + winner;
				System.out.println(winner);
			}
			else 
				System.out.println("Match Nul!");
			
			System.out.println("Voulez vous rejouez (O/N)?");
			sc.nextLine();
			String answer = sc.nextLine();
			
			if(answer.equalsIgnoreCase("o")) {
				gameIsFinished = false;
				this.board = new Board();
				start();
			}
			else
				System.out.println("Au revoir!");
		}
	}
	
	public Game() {
		board = new Board();
		players = new Player[2];
	}
	
	public static void main(String[] args) throws InterruptedException {
		Game game =  new Game();
		game.start();
	}	
}
