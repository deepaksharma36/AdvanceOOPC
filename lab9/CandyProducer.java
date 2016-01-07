/**
 * this class produces one piece of candy at a time and stores the candy in a
 * fixed length storage area(candyLimit=k).
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class CandyProducer extends Thread {
	public volatile int candy = 0;
	public int candyLimit;
	public volatile boolean run = true;
	Object lock1;

	/**
	 * this constructor initializes the candy producer.
	 * 
	 * @param candyLimit
	 *            the maximum number of candies that can be produced.
	 * @param lock1
	 *            lock object of object class used for synchronization
	 */
	public CandyProducer(int candyLimit, Object lock1) {
		candy = 0;
		this.lock1 = lock1;
		this.candyLimit = candyLimit;
	}

	/**
	 * this method produces one candy at a time.
	 */
	public void run() {
		while (run) {

			addCandy(1);

		}
		System.out.println("********************candy Procution is closed************************************");

	}

	/**
	 * this method stores the num number of candies produced one at a time.
	 * 
	 * @param num
	 *            number of candies produced.
	 */
	public void addCandy(int num) {
		try {
			synchronized (lock1) {

				while (candy + num >= candyLimit && run) {

					lock1.wait();
				}
				candy = candy + num;
				System.out.println("new candy produced" + candy);
				lock1.notify();
			}
		} catch (InterruptedException e) {

			// e.printStackTrace();
		}
	}

	/**
	 * this method removes the num number of candies from tyhe fixed storage.
	 * 
	 * @param num
	 *            number of candies removed from the storage.
	 */
	public void removeCandy(int num) {

		synchronized (lock1) {
			try {

				while (candy - num < 0 && run) {
					lock1.wait();
				}
				candy = candy - num;
				System.out.println("candy removed" + candy);
				lock1.notify();
			}

			catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

}
