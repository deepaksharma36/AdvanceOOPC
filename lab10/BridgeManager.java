
//import statements are placed here.
import java.util.Random;

/**
 * this class manages the bridge and enable trucks to cross the bridge. at any
 * given time the bridge can only hold 200000 lbs and no more than 4 trucks can
 * be on the bridge.The trucks can arrive the bridge from both sides.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class BridgeManager extends Thread {

	private volatile int BridgeWeight = 0;
	private final int maxBridgeWeight = 200000;
	private int waitingTruckCountLeftSide = 0;
	private int waitingTruckCountRightSide = 0;
	private Object bridgeLock = new Object();
	private Object waitingLock = new Object();
	private volatile int bridgeLeftCount = 0;
	private volatile int bridgeRightCount = 0;
	private volatile int servingTokenNumber = 1;

	// this method enables the truck to cross the bridge
	public void crossthisTruck(Truck aTruck) {

		checkTheBridge(aTruck);
		// truck is on the road out side synchronized Block
		try {
			Thread.sleep(new Random().nextInt(200));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (bridgeLock) {
			if (aTruck.getDirection().equals("left"))
				bridgeLeftCount--;
			else
				bridgeRightCount--;
			BridgeWeight -= aTruck.getWeight();

		}

		System.out.println(aTruck.getWaitingNumber() + "leaving Bridge");
	}

	// this method will enable the incoming truck to cross the bride.
	public int updateWaitingTruck(int side, int num) {
		synchronized (waitingLock) {

			waitingTruckCountLeftSide += num;
			return waitingTruckCountLeftSide;
		}

	}

	/*
	 * this method always checks the status of the bridge and updates the status
	 * of the bridge before letting the bus in , and also gives the waiting
	 * numbers to the subsequent trucks if there are already 4 trucks on the
	 * bridge
	 */
	public void checkTheBridge(Truck aTruck) {
		if (aTruck.getDirection().equals("left") && aTruck.getWaitingNumber() == 0)
			aTruck.setWaitingNumber(updateWaitingTruck(1, 1));
		else {
			if (aTruck.getWaitingNumber() == 0)
				aTruck.setWaitingNumber(updateWaitingTruck(2, 1));
		}

		System.out.println("For waiting number " + aTruck.getWaitingNumber() + "new truck on the bridge");
		synchronized (bridgeLock) {
			try {
				while (aTruck.getWeight() + BridgeWeight > maxBridgeWeight || bridgeLeftCount + bridgeRightCount >= 4
						|| aTruck.getWaitingNumber() != servingTokenNumber) {
					bridgeLock.notifyAll();
					bridgeLock.wait();
				}

				BridgeWeight = BridgeWeight + aTruck.getWeight();
				if (aTruck.getDirection().equals("left"))
					bridgeLeftCount++;
				else
					bridgeRightCount++;
				System.out.println("Number of Trucks on Bridge " + (bridgeLeftCount + bridgeRightCount)
						+ " Weight of the bridge " + BridgeWeight);
				servingTokenNumber++;
				bridgeLock.notifyAll();

			} catch (Exception e) {
				System.out.println("Error:" + e);
			}
		}
	}

	public static void main(String[] args) {
		BridgeManager aBridgeManager = new BridgeManager();
		TruckManger aTruckManger = new TruckManger(aBridgeManager);
		aTruckManger.start();
		// aBridgeManager.crossthisTruck(aTruckManger.sendTruck());

	}

}
