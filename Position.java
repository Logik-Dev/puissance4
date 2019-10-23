
public class Position {
	int x;
	int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		return ((Position)o).x == x && ((Position)o).y == y;
	}
}
