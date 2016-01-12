import java.util.Random;

public class FlyingCow extends Cow {
	
	public FlyingCow(String name) {
		super(name);
	}
	
	public void doStuffForAnHour(int curTime, FarmObject[ ][ ] farmGrid) {
		int nRows = farmGrid.length;
		int nCols = farmGrid[0].length;
		
// 		between 6AM and 6PM
		if(isActive(curTime)) {
			
//			cow traverses one square in a random direction
//			if square is occupied by everything except grass
			
/*
 * 			generate random number (y) between 0 and (nRows - 1) for random row
 * 			generate random number (x) between 0 and (nCols - 1) for random column
 * 			
 */
			Random rand = new Random();
			int nextx = rand.nextInt(nCols);
			int nexty = rand.nextInt(nRows);
			
//			check if nextx and nexty coordinates have cows
			if (farmGrid[nexty][nextx] instanceof Cow) {
//				some type of cow already exists in this square
				return;
			}
			
//			check if nextx and nexty coordinates have poisonous grass
			if (farmGrid[nexty][nextx] instanceof PoisonedGrass) {
//				cow's sickness level increases by the amount of poisoned grass
				this.setSicknessLevel(this.getSicknessLevel() + ((PoisonedGrass) farmGrid[nexty][nextx]).getAmtGrass());
				removeFarmObject(farmGrid, nextx, nexty); // remove the poisoned grass object from grid
			} else if (farmGrid[nexty][nextx] instanceof Grass) {
//				if nextx and nexty coordinates have grass
//				decrease hungriness by amt of grass
				this.setHungriness(this.getHungriness() - ((Grass) farmGrid[nexty][nextx]).getAmtGrass()); 
				removeFarmObject(farmGrid, nextx, nexty); // remove the grass object from grid
			}
//			finally move the cow to the new square
			farmGrid[nexty][nextx] = this; // move cow to new square
			farmGrid[getY()][getX()] = null; // make old square null
			
//			update object's x and y coordinates
			this.setCoordinates(nextx, nexty);
		}
		
		this.setAge(this.getAge() + 1); // increase age by 1
		this.setHungriness(this.getHungriness() + 2); // increase hungriness level by 2
		
//		if cow's age reaches deathAge or deathHungriness, remove the cow from grid
		if ((this.getAge() >= deathAge) || (this.getHungriness() >= deathHungriness)) {
			removeFarmObject(farmGrid, this.getX(), this.getY());
		}
		
//		check the chance of the cow dying
		if(isDeadByChance()) { // if dying chance is true, remove cow from grid
			removeFarmObject(farmGrid, this.getX(), this.getY());
		}
	}
}