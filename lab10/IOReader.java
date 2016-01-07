/**
 * this class, in the main method creates an object of input/output class and
 * and reads from the buffer.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class IOReader extends Thread {
	static String inputFileName = "words.txt";

	public static void main(String args[]) {
		System.out.println("Program has started");
		IO aIo = new IO(inputFileName);
		aIo.open();
		while (aIo.read() != null) {
			try {
				sleep(100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
