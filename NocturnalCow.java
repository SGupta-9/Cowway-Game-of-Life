public class NocturnalCow extends Cow {
	
	public NocturnalCow(String name) {
		super(name);
	}

//	checks if cow is active at this curTime
	public boolean isActive(int curTime) {
//		 only active between 6PM and 6AM
		if(!((curTime >= startTime) && (curTime <= endTime))) {
			return true;
		} else {
			return false;
		}
	}
}