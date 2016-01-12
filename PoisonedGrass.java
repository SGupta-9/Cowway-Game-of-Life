import java.util.Random;

public class PoisonedGrass extends Grass{
	
	public void doStuffForAnHour(int curHour, FarmObject[ ][ ] farmGrid) {
//		increase poisoned grass amount by 2
		setAmtGrass(getAmtGrass() + 2);
		
		int nRows = farmGrid.length;
		int nCols = farmGrid[0].length;
		
//		check growing chance 
		if (canGrow()) { 
//			grow square of grass in adjacent square
/*
* 			generate random number between 0 and 3
* 			0 means East, 1 means West, 2 means North, 3 means South
*/
			Random rand = new Random();
			int dir = rand.nextInt(4);
								
			int nextx = this.getX();
			int nexty = this.getY();
							
			if (dir == 0) { // trying to move one right (east)
				if ((this.getX()) >= (nCols - 1)) { // if the grass is in the last column
//					grass does not grow
					return;
				} else {
					nextx = this.getX() + 1;
				}
			} else if (dir == 1) { // trying to move one left (west)
				if ((this.getX()) <= 0) { // if the grass is in the first column
//					grass does not grow
					return;
				} else {
					nextx = this.getX() - 1;
				}
			} else if (dir == 2) { // trying to move one up (north)
				if ((this.getY()) <= 0) { // if the grass is in the first row
//					grass does not grow
					return;
				} else {
					nexty = this.getY() + 1;
				}
			} else if (dir == 3) { // trying to move one down (south)
				if ((this.getY()) >= (nRows - 1)) { // if the grass is in the last row
//					grass does not grow
					return;
				} else {
					nexty = this.getY() - 1;
				}
			}
							
//			check if nextx and nexty coordinates have any type of cow or pgrass
			if ((farmGrid[nexty][nextx] instanceof Cow)) {  // don't do anything if adj square is cow
				return;
			} else if ((farmGrid[nexty][nextx] instanceof PoisonedGrass)) { // if nextx and nexty square have pgrass
//				increase amt of pgrass for existing pgrass object
				((PoisonedGrass) farmGrid[nexty][nextx]).setAmtGrass(getAmtGrass() + 2);
				return;
			} else if (farmGrid[nexty][nextx] instanceof Grass) { // don't do anything if adj square is grass
				return;
			}
			
//			finally grow the grass to the new square
			this.setCoordinates(nextx, nexty);
		}
	}
}