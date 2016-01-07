
//import statements are placed here.
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * this class implements a buffer of 10k and the reader which fills this buffer
 * reads 1k data at a time. this is a buffer manager which reads data from a
 * file and input the data into buffer, where buffer is in the form of queue
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class IO extends Thread {
	final int MAX = 1024;
	static Object Lock = new Object();
	byte buffer[] = new byte[1024];
	static boolean run = true;
	IO aIoThread1;
	static String inputFileName;
	static MyQueue myQueue = new MyQueue();
	static BufferedInputStream input;

	IO(String inputFileName) {
		IO.inputFileName = inputFileName;
		try {
			input = new BufferedInputStream(new FileInputStream(inputFileName), 1024);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// we have used this constructor to create a thread.
	IO() {

	}

	/**
	 * this method reads the data from the file and enqueue the data in the
	 * queue 1k at a time. queue is our buffer
	 */
	public void writer() {

		try {
			synchronized (Lock) {
				if (input.read(buffer, 0, 1024) == -1) {
					myQueue.push(null);
					close();
				}
				Lock.notify();
				if (myQueue.getSize() < 10) {
					myQueue.push(buffer);
					System.out.println("Data pushed in the queue now size is" + myQueue.getSize());
				} else {
					Lock.wait();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * this method reads the data from the queue 1k at a time.
	 * 
	 * @return returns the data of 1k from the queue/buffer.
	 */
	public byte[] read() {

		try {
			synchronized (Lock) {

				Lock.notify();
				if (myQueue.getSize() > 0) {
					System.out.println("size of the newly pulled data from the queue is" + myQueue.getSize());
					return myQueue.pop();

				} else {
					System.out.println("Reader going for wait No data found");
					Lock.wait();
					System.out.println("Reader got notification executing now");
					return myQueue.pop();
				}

			}

		}

		catch (Exception e) {
			return null;
		}
	}

	/**
	 * this method constantly calls writer method and keeps reading data from
	 * the source file and writes it in the queue/buffer independently from the
	 * reader method.
	 */
	public void run() {
		while (run) {
			// System.out.println("Run method started");
			// try {
			// sleep(0);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			writer();
		}
	}

	/**
	 * this method will initiate the thread for reading the data from the source
	 * file and writing into the buffer.
	 */
	public void open() {
		IO aIoThread1 = new IO();
		// IO aIoThread2 = new IO();
		aIoThread1.start();
		// aIoThread2.start();
		System.out.println("Writer Thread has started running");
	}

	/**
	 * once the source file is read that is when it reaches end of file, then we
	 * will close the write process.
	 */
	public void close() {
		System.out.println("End of file has reached");
		run = false;
		try {
			aIoThread1.interrupt();

		} catch (Exception e) {

		}
		/*
		 * try{ aIoThread2.interrupt(); } catch(Exception e) {
		 * 
		 * }
		 */
	}

}