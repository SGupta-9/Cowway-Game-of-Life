import java.util.Random;

public class Cow extends FarmObject {
	private String name;
	private int hungriness;
	private int age;
	private int sicknessLevel;
	
	public static final int startTime = 6; // active starting at 6AM
	public static final int endTime = 18; // active until 6PM
	public static final int deathAge = 90001;
	public static final int deathHungriness = 100;
	
	public Cow(String name){
		this.name = name;
	}
	
	public int getSicknessLevel() {
		return this.sicknessLevel;
	}
	
	public int getHungriness() {
		return this.hungriness;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setSicknessLevel(int a) {
		this.sicknessLevel = a;
	}
	
	public void setHungriness(int a) {
		this.hungriness = a;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int a) {
		this.age = a;
	}
	

	
	public void doStuffForAnHour(int curTime, FarmObject[ ][ ] farmGrid) {
		int nRows = farmGrid.length;
		int nCols = farmGrid[0].length;
		
//		if((curTime >= startTime) && (curTime >= endTime)) { // between 6AM and 6PM
		if(isActive(curTime)) {
			
//			cow traverses one square in a random direction
//			if square is occupied by everything except grass
			
/*
 * 			generate random number between 0 and 3
 * 			0 means East, 1 means West, 2 means North, 3 means South
 */
			Random rand = new Random();
			int dir = rand.nextInt(4);
			
			int nextx = this.getX();
			int nexty = this.getY();
			
			if (dir == 0) { // trying to move one right (east)
				if ((this.getX()) >= (nCols - 1)) { // if the cow is in the last column
//					cow does nothing for the hour
					return;
				} else {
					nextx = this.getX() + 1;
				}
			} else if (dir == 1) { // trying to move one left (west)
				if ((this.getX()) <= 0) { // if the cow is in the first column
//					cow does nothing for the hour
					return;
				} else {
					nextx = this.getX() - 1;
				}
			} else if (dir == 2) { // trying to move one up (north)
				if ((this.getY()) <= 0) { // if the cow is in the first row
//					cow does nothing for the hour
					return;
				} else {
					nexty = this.getY() - 1;
				}
			} else if (dir == 3) { // trying to move one down (south)
				if ((this.getY()) >= (nRows - 1)) { // if the cow is in the last row
//					cow does nothing for the hour
					return;
				} else {
					nexty = this.getY() + 1;
				}
			}
			
//			check if nextx and nexty coordinates have cows
			if (farmGrid[nexty][nextx] instanceof Cow) {
//				some type of cow already exists in this square
				return;
			}
			
//			check if nextx and nexty coordinates have poisonous grass
			if (farmGrid[nexty][nextx] instanceof PoisonedGrass) {
//				cow's sickness level increases by the amount of poisoned grass
				this.sicknessLevel += ((PoisonedGrass) farmGrid[nexty][nextx]).getAmtGrass();
				removeFarmObject(farmGrid, nextx, nexty); // remove the poisoned grass object from grid
			} else if (farmGrid[nexty][nextx] instanceof Grass) {
//				if nextx and nexty coordinates have grass
				this.hungriness -= ((Grass) farmGrid[nexty][nextx]).getAmtGrass(); // decrease hungriness by amt of grass
				removeFarmObject(farmGrid, nextx, nexty); // remove the grass object from grid
			}
//			finally move the cow to the new square
			farmGrid[nexty][nextx] = this; // move cow to new square
			farmGrid[getY()][getX()] = null; // make old square null
			
//			update object's x and y coordinates
			this.setCoordinates(nextx, nexty);
		}
		
		this.age++; // increase age by 1
		this.hungriness += 2; // increase hungriness level by 2
		
//		if cow's age reaches deathAge or deathHungriness, remove the cow from grid
		if ((this.age >= deathAge) || (this.hungriness >= deathHungriness)) {
			removeFarmObject(farmGrid, this.getX(), this.getY());
		}
		
//		check the chance of the cow dying
		if(isDeadByChance()) { // if dying chance is true, remove cow from grid
			removeFarmObject(farmGrid, this.getX(), this.getY());
		}
	}
	
	public boolean isDeadByChance() {
//		cow has a 0.0001*age*sickness % of dying... 
//		if that percentage is greater than 50, cow dies
		double deathChance = 0.0001 * this.age * this.sicknessLevel;
		if (deathChance < 0.5) {
			return false;
		} else {
			return true;
		}
	}
	
//	checks if cow is active at this curTime
	public boolean isActive(int curTime) {
		if((curTime >= startTime) && (curTime <= endTime)) {
			return true;
		} else {
			return false;
		}
	}

}