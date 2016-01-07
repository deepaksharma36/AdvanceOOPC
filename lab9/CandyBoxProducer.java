
/***
 * this class produces the empty boxes and stores them in fixed length storage.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class CandyBoxProducer extends Thread {
	public volatile int candyBox = 0;
	public int limitcandyBox;
	Object lock4;

	public volatile boolean run = true;

	public CandyBoxProducer(int limitcandyBox, Object lock4) {
		candyBox = 0;
		this.limitcandyBox = limitcandyBox;
		this.lock4 = lock4;
	}

	/**
	 * this method produces one candy at a time.
	 */
	public void run() {
		while (run) {

			addCandyBox(1);

		}
		System.out.println("********Empty Candy box production closed*****************");
	}

	/**
	 * this method stores the produced candy boxes one at a time.
	 * 
	 * @param num
	 *            number of boxes produced.
	 */
	public void addCandyBox(int num) {
		try {
			synchronized (lock4) {

				while (candyBox + num > limitcandyBox && run)
					lock4.wait();
				candyBox += num;
				System.out.println("empty Box prepared");
				lock4.notify();
			}
		} catch (InterruptedException e) {

		}
	}

	/**
	 * this method removes num number of candy boxes from the fixed storage.
	 * 
	 * @param num
	 *            number of candy boxes to be removed.
	 */
	public void removeCandyBox(int num) {

		synchronized (lock4) {
			try {

				while (candyBox - num < 0 && run) {
					lock4.wait();
				}
				candyBox = candyBox - num;
				System.out.println("empty box removed");
				lock4.notify();
			}

			catch (InterruptedException e) {

			}
		}
	}

}
