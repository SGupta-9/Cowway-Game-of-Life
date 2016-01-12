public class Farm {
	private FarmObject[ ][ ] farmGrid;
	private FarmObject[ ] fObjects; // contains all FarmObjects on Farm
	
//	in order to test this program, the values are hard-coded
	public void populateFarm(int numRows, int numColumns, FarmObject[ ] fObjects) {
//		initialize the farmGrid
		farmGrid = new FarmObject[numRows][numColumns];
		
		farmGrid[0][0] = fObjects[0];
		fObjects[0].setCoordinates(0, 0); // rows = y, columns = x
		
		farmGrid[1][0] = fObjects[1];
		fObjects[1].setCoordinates(0, 1);
		
		farmGrid[3][0] = fObjects[2];
		fObjects[2].setCoordinates(0, 3);
		
		farmGrid[0][1] = fObjects[3];
		fObjects[3].setCoordinates(1, 0);

		farmGrid[2][0] = fObjects[4];
		fObjects[4].setCoordinates(0, 2);
	}
	
	public FarmObject[ ][ ] getFarmGrid() {
		return farmGrid;
	}
	
	public void simulateFarmCycle(int curTime) {
//		traverse entire farm grid
//		for each farm object, call its doStuffEveryHour() 
		for (int i = 0; i < this.farmGrid.length; i++) {
			for (int j = 0; j < this.farmGrid[i].length; j++) {
//				 if square is null, don't do anything and move onto next square
				if (farmGrid[i][j] != null) {
					farmGrid[i][j].doStuffForAnHour(curTime, farmGrid);
				}
			}
		}
	}
}