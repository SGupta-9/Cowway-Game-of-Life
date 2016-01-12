public class World {
	private Farm[ ] allFarms; // array of all farms in the world
	private int curTime = 7; // holds the current time as 24-hour clock (curTime = 0 means midnight, curTime = 12 means noon)
	
	public World(int numFarms) {
//		create array of all the farms in the world
		allFarms = new Farm[numFarms];
		for (int i = 0; i < numFarms; i++) {
			allFarms[i] = new Farm();
		}
	}
	
//	increment the time
	public void incTime() {
		this.curTime++;
	}
	
	public Farm getAFarm(int index) {
		return allFarms[index];
	}
	
	public void simulateWorld(int hours) {
		for (int i = 0; i < hours; i++) {
			for (int j = 0; j < allFarms.length; j++) {
				allFarms[j].simulateFarmCycle(this.curTime);
			}
			incTime();
		}
	}
}