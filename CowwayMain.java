public class CowwayMain {
	public static final int HOURS = 3;
	public static final int numFarmsInWorld = 1;
	public static final int FARM_ROWS = 4;
	public static final int FARM_COLS = 3;
	
	public static void main(String[ ] args) {
//		create a world
		World world = new World(numFarmsInWorld);
		
//		initialize both farms
		Farm farm1 = world.getAFarm(0);

//		create the FarmObjects
		Cow cow = new Cow("cow ");
		FlyingCow fCow = new FlyingCow("fcow");
		NocturnalCow nCow = new NocturnalCow("ncow");
		Grass grass = new Grass();
		PoisonedGrass pGrass = new PoisonedGrass();
		
//		initialize fObjects that contains all farm animals on a farm
		FarmObject[ ] fObjects = {cow, fCow, nCow, grass, pGrass};
		
//		populate both farms
		farm1.populateFarm(FARM_ROWS, FARM_COLS, fObjects);
		
//		print initial farm states
		System.out.println("Initial State of Farm1: ");
		System.out.println("------------------------");
		printFarmState(farm1.getFarmGrid());
		System.out.println("------------------------");
		
		world.simulateWorld(HOURS);
		
//		print final farm states after HOURS hours
		System.out.println("Final State of Farm1: ");
		System.out.println("------------------------");
		printFarmState(farm1.getFarmGrid());
		System.out.println("------------------------");
	}
	
	private static void printFarmState(FarmObject[ ][ ] farmGrid) {
		String str = "";
		for (int i = 0; i < farmGrid.length; i++) {
			for (int j = 0; j < farmGrid[i].length; j++) {
				if(farmGrid[i][j] == null) {
					str = "null";
				} else { // check type of the object
					if (farmGrid[i][j] instanceof FlyingCow) {
						str = "fCow";
					} else if (farmGrid[i][j] instanceof NocturnalCow) {
						str = "nCow";
					} else if (farmGrid[i][j] instanceof Cow) {
						str = "cow ";
					} else if (farmGrid[i][j] instanceof PoisonedGrass) {
						str = "pgrs";
					} else if (farmGrid[i][j] instanceof Grass) {
						str = "gras";
					}
				}
				System.out.print(str + " ");
			}
			System.out.println();
		}
	}
}