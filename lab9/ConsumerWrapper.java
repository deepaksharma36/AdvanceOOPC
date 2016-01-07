/**
 * this class consumerWrapper consumes one piece of candy and one piece of
 * wrapping paper and wraps the candy in wrapping paper, if a candy and wrapping
 * paper exists, and stores the wrapped candy in a fixed length storage area
 * ((lenwrappedCandyStorageLimitgth=m, m > 4).
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class ConsumerWrapper extends Thread {

	public volatile int wrappedCandy = 0;
	Object lock3;
	int wrappedCandyStorageLimit;
	CandyProducer aCandyProducer;
	WrappingPaperProducer aWrappingPaperProducer;
	public volatile boolean run = true;

	/**
	 * this method wraps the candy.
	 * 
	 * @param wrappedCandyStorageLimit
	 *            the maximum number of candies that can be stored in the
	 *            storage.
	 * @param lock3
	 *            object of the object class for synchronization.
	 * @param aCandyProducer
	 *            object of the candy producer produces candy.
	 * @param aWrappingPaperProducer
	 *            object of the wrappingPaperProducer produces wrapping paper.
	 */
	public ConsumerWrapper(int wrappedCandyStorageLimit, Object lock3, CandyProducer aCandyProducer,
			WrappingPaperProducer aWrappingPaperProducer) {

		this.lock3 = lock3;
		this.wrappedCandyStorageLimit = wrappedCandyStorageLimit;
		this.aWrappingPaperProducer = aWrappingPaperProducer;
		this.aCandyProducer = aCandyProducer;

	}

	/**
	 * this method gets one candy and one wrapper and wraps the candy.
	 */
	public void run() {
		while (run) {

			addWrappedCandy(1);

		}
		System.out.println("***********************Candy wraping machine is closed******************");

	}

	/**
	 * this method stores the wrapped candy in the storage.
	 * 
	 * @param num
	 *            the number of candies that can be stored in the storage.
	 */
	public void addWrappedCandy(int num) {
		try {
			synchronized (lock3) {

				while (wrappedCandy + num >= wrappedCandyStorageLimit && run) {
					if (run)
						lock3.wait();
					else
						break;
				}
				aCandyProducer.removeCandy(1);
				aWrappingPaperProducer.removeWrappingPaper(1);
				wrappedCandy += num;
				lock3.notify();
			}
		} catch (InterruptedException e) {

		}
	}

	/**
	 * this method removes the num number of candies from the storage.
	 * 
	 * @param num
	 *            number of candies that need o removed from the storage.
	 */
	public void removeWrappedCandy(int num) {

		synchronized (lock3) {
			try {

				while (wrappedCandy - num < 0 && run) {
					if (run)
						lock3.wait();
					else
						break;
				}
				wrappedCandy = wrappedCandy - num;
				lock3.notify();
			}

			catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

}
