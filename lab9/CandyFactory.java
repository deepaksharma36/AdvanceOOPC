/**
 * this class initializes various components of the candy factory and starts
 * them.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class CandyFactory {
	public Object lock1 = new Object();
	public Object lock2 = new Object();
	public Object lock3 = new Object();
	public Object lock4 = new Object();
	int k = 21, l = 21, m = 10, n = 5;

	public static void main(String[] args) {
		// created the object of candyFactory and run the factory.
		CandyFactory aCandyFactory = new CandyFactory();
		aCandyFactory.runFactor();
	}

	public void runFactor() {
		// created a candy producer
		CandyProducer aCandyProducer = new CandyProducer(k, lock1);
		// created a wrapping paper producer.
		WrappingPaperProducer aWrappingPaperProducer = new WrappingPaperProducer(l, lock2);
		// created candy wrapper.
		ConsumerWrapper aConsumerWrapper = new ConsumerWrapper(m, lock3, aCandyProducer, aWrappingPaperProducer);
		// created candy box producer.
		CandyBoxProducer aCandyBoxProducer = new CandyBoxProducer(m, lock4);
		// created box filler.
		ConsumerBoxFiller aConsumerBoxFiller = new ConsumerBoxFiller(n, aCandyBoxProducer, aConsumerWrapper);

		try {

			aCandyProducer.start();
			aWrappingPaperProducer.start();
			aCandyBoxProducer.start();
			aConsumerWrapper.start();
			aConsumerBoxFiller.start();
			aConsumerBoxFiller.join();
			aCandyProducer.run = false;
			aWrappingPaperProducer.run = false;
			aCandyBoxProducer.run = false;
			aConsumerWrapper.run = false;
			aConsumerBoxFiller.run = false;

			aCandyProducer.interrupt();
			aWrappingPaperProducer.interrupt();
			aCandyBoxProducer.interrupt();
			aConsumerWrapper.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
