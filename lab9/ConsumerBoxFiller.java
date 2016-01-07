/**
 * this class consumerBoxFiller fills the box if enough wrapped candy is
 * available to fill a complete box, and stores the filled boxes in a fixed
 * length storage area (packedBoxesStorageLimit=n)
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class ConsumerBoxFiller extends Thread {
	public volatile int filledBoxes = 0;
	public volatile boolean run = true;
	int packedBoxesStorageLimit;
	CandyBoxProducer aCandyBoxProducer;
	ConsumerWrapper aConsumerWrapper;

	/**
	 * this method picks wrapped candy and empty box and fill them, and then
	 * store them.
	 * 
	 * @param packedBoxesStorageLimit
	 *            the maximum number of boxes that can be stored.
	 * @param aCandyBoxProducer
	 *            object of the candy box producer.
	 * @param aConsumerWrapper
	 *            object of the candy wrapper.
	 */
	public ConsumerBoxFiller(int packedBoxesStorageLimit, CandyBoxProducer aCandyBoxProducer,
			ConsumerWrapper aConsumerWrapper) {

		this.aCandyBoxProducer = aCandyBoxProducer;
		this.aConsumerWrapper = aConsumerWrapper;
		this.packedBoxesStorageLimit = packedBoxesStorageLimit;

	}

	/**
	 * this method picks four wrapped candy ad a box and fill the box with
	 * candies.
	 */
	public void run() {
		while (run) {
			try {
				if (filledBoxes >= packedBoxesStorageLimit) {
					System.out.println("all packed boxes are cerated");
					break;
				}
				aCandyBoxProducer.removeCandyBox(1);
				aConsumerWrapper.removeWrappedCandy(4);
				updatePackedBox();

				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("*****************Box Filling Machine is Closed**************");
	}

	/**
	 * this method places the filled box into the storage.
	 */
	public void updatePackedBox() {

		filledBoxes++;
		System.out.println("Boxes prepared");

	}

}
