public abstract class FarmObject {
	private int x; // x-coordinate of farm
	private int y; // y-coordinate of farm
	
	public abstract void doStuffForAnHour(int curTime, FarmObject[ ][ ] farmGrid);
	
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
//	removes FarmObject at x,y on the farmGrid
	public void removeFarmObject(FarmObject[ ][ ] farmGrid, int x, int y) {
		farmGrid[y][x] = null;
	}
}