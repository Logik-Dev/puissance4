
public enum Symbol {
	JAUNE("X"),
	ROUGE("O"),
	VIDE(".");
	
	private String name;
	
	Symbol(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}
