/**
 * in this method every truck has a random weight between 100 lbs and 100000
 * lbs.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
class Truck extends Thread {

	private int weight;
	private String direction;
	private BridgeManager aBridgeManager;

	// gets the direction of the thread
	public String getDirection() {
		return direction;
	}

	private int waitingNumber;

	// gets the waiting number of the thread
	public int getWaitingNumber() {
		return waitingNumber;
	}

	// this method sets the waiting number to the thread.
	public void setWaitingNumber(int waitingNumber) {
		this.waitingNumber = waitingNumber;
	}

	// this method gets the weight of the truck.
	public int getWeight() {
		return weight;
	}

	/**
	 * this constructor assigns the class variables with its values
	 * 
	 * @param weight
	 *            assigns the weight
	 * @param direction
	 *            assigns the direction of the truck.
	 * @param aBridgeManager
	 */
	public Truck(int weight, String direction, BridgeManager aBridgeManager) {
		this.weight = weight;
		this.direction = direction;
		this.aBridgeManager = aBridgeManager;
	}

	public void run() {

		aBridgeManager.crossthisTruck(this);
	}
}