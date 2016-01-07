import java.util.Random;

/**
 * this classes work is to create trucks and assign these trucks to a bridge.
 * when the assignment is done, it tries to cross the bridge if there are not
 * than 4 trucks on the bridge.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class TruckManger extends Thread {

	private Object truckLock;

	private BridgeManager aBridgeManager;
	Random rand = new Random();

	public TruckManger(BridgeManager aBridgeManager) {
		this.aBridgeManager = aBridgeManager;
	}

	public Truck sendTruck() {
		int minWeight = 100;
		int maxWeight = 100000;

		String[] direction = { "left", "right" };
		return new Truck(rand.nextInt(maxWeight - minWeight) + minWeight, direction[rand.nextInt(1)], aBridgeManager);
	}

	// in this run method, truck tries to cross the bridge with the number which
	// is assigned to it in the truck manager.
	public void run() {

		while (true) {
			try {

				sendTruck().start();

				sleep(rand.nextInt(80));

			} catch (Exception ex) {

			}

		}
	}

}
