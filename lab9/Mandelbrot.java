
//import statements are here
import java.awt.Color;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * this class extends threads class and colors pixel for each class. by invoking
 * creatSet2 method for parallel processing.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
class PixelThread extends Thread {
	boolean run = true;
	private Mandelbrot aMandelbrot;

	public PixelThread(Mandelbrot aMandelbrot) {
		this.aMandelbrot = aMandelbrot;
	}

	/**
	 * colors each pixel.
	 */
	public void run() {

		while (run) {
			try {
				synchronized (this) {
					String xy = Thread.currentThread().getName();
					String[] XY = xy.split(",");
					int x = Integer.parseInt(XY[0]);
					int y = Integer.parseInt(XY[1]);
					int z = Integer.parseInt(XY[2]);

					aMandelbrot.creatSet2(x, y);
					aMandelbrot.isWorkCompleted[z] = true;
					this.wait();
				}

			} catch (InterruptedException e) {

			}
		}

	}

}

/**
 * this class creates maximum number of pixelThreads and main thread acts as a
 * manager thread. The manager thread distributes the work to each pixelThread.
 */
public class Mandelbrot extends JFrame {

	private final int MAX = 5000;
	private final int LENGTH = 800;
	private final double ZOOM = 1000;
	private BufferedImage theImage;
	private int[] colors = new int[MAX];
	private boolean firstTime = true;
	int counter = 0;
	// ThreadComplitionCount keeps track of the number of
	// the threads for those whose creatSet2 methods execution is completed
	private int NumberOfProcessers = Runtime.getRuntime().availableProcessors();
	private PixelThread[] aMandelbrotsArray = new PixelThread[NumberOfProcessers];
	private int iCounter = 0, jCounter = 0, kCounter = 0;
	public volatile boolean[] isWorkCompleted = new boolean[NumberOfProcessers];
	Object[] locks = new Object[NumberOfProcessers];

	/**
	 * this constructor initializes parameters for painting.
	 */
	public Mandelbrot() {
		super("Mandelbrot Set");

		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		while (iCounter < NumberOfProcessers)
			aMandelbrotsArray[iCounter++] = new PixelThread(this);
		while (jCounter < NumberOfProcessers)
			locks[jCounter++] = new Object();
		while (kCounter < NumberOfProcessers)
			isWorkCompleted[kCounter++] = true;
	}

	/**
	 * this creatSet2 method creates pixel wise painting.
	 * 
	 * @param x
	 *            x coordinate of the pixel
	 * @param y
	 *            y coordinate of the pixel
	 */
	public void creatSet2(int x, int y) {
		double zx, zy, cX, cY;
		zx = zy = 0;
		cX = (x - LENGTH) / ZOOM;
		cY = (y - LENGTH) / ZOOM;
		int iter = 0;
		double tmp;
		while ((zx * zx + zy * zy < 10) && (iter < MAX - 1)) { // this is the
																// part for the
																// parallel part
			tmp = zx * zx - zy * zy + cX; // this is the part for the parallel
											// part
			zy = 2.0 * zx * zy + cY; // this is the part for the parallel part
			zx = tmp; // this is the part for the parallel part
			iter++; // this is the part for the parallel part
		} // this is the part for the parallel part
		if (iter > 0) // this is the part for the parallel part
			theImage.setRGB(x, y, colors[iter]); // this is the part for the
													// parallel part
		else
			// this is the part for the parallel part
			theImage.setRGB(x, y, iter | (iter << 8)); // this is the part for
														// the parallel part
	}

	/**
	 * This method assigne each thread a pixel according to the availibility of
	 * thread and calls createSet2 in the run method of pixel thread. by
	 * invoking the start method of the pixel thread.
	 */
	public void createSet() {
		theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				synchronized (locks[0]) {
					if (firstTime) {
						aMandelbrotsArray[counter]
								.setName(Integer.toString(x) + "," + Integer.toString(y) + "," + counter);
						aMandelbrotsArray[counter].start();
						isWorkCompleted[counter] = false;
						counter++;
						if (counter == NumberOfProcessers)
							firstTime = false;
					} else {
						out: while (true) {
							for (int i = 0; i < NumberOfProcessers; i++) {
								if (isWorkCompleted[i]) {

									synchronized (aMandelbrotsArray[i]) {
										aMandelbrotsArray[i].notify();
										aMandelbrotsArray[i]
												.setName(Integer.toString(x) + "," + Integer.toString(y) + "," + i);
										isWorkCompleted[i] = false;
									}
									break out;
								}
							}
						}
					}

				}

			}
		}

		// repaint();
		// waiting for the executions of all threads so that picture can be
		// shown by repaint method.

	}

	/**
	 * initialization of the painting
	 */
	public void initColors() {
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index / 256f, 1, index / (index + 8f));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	/**
	 * this method terminates all pixel threads after the completion of
	 * painting.
	 */
	public void termination() {
		int iCounter = 0;
		while (iCounter < NumberOfProcessers) {
			aMandelbrotsArray[iCounter].run = false;

			aMandelbrotsArray[iCounter++].interrupt();
		}

	}

	/**
	 * in this main method we create an object of the Mandelbrot class to invoke
	 * its createSet method to paint.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Mandelbrot aMandelbrot = new Mandelbrot();

		aMandelbrot.setVisible(true);

		aMandelbrot.createSet();
		aMandelbrot.repaint();
		aMandelbrot.termination();

	}
}
