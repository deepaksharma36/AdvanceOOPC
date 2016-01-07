/**
 * this program creates 99 threads where each thread prints out information 
 * from o to 98 and then repeats
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class ZeroOne extends Thread {
	private String info;
	static Object o = new Object();
	volatile static int oneIsRunning = 1; volatile static int runningThread = 1;
	
	public ZeroOne(String info) {
		this.info = info;
	}
	/**
	 * this method creates 99 threads where each thread prints out information 
	 * from o to 98 and then repeats.
	 */
	public void run() {
		while (true) {
			synchronized (o) {
				try {
					
			
					while (!this.info.equals(""+(runningThread-1)))
					{
						o.notify();
						o.wait();
					}
					
					System.out.print(""+this.info);
					

					if (oneIsRunning < 99) {
						ZeroOne x = new ZeroOne(Integer.toString(oneIsRunning++));
					
						x.start();
						runningThread=oneIsRunning;
					}
					else if(runningThread==99){
					
					    runningThread=1;
					    o.notify();
					    
					
					    
					 }
					else
					{
						runningThread++;
					
						o.notify();
						
					}
					
					
					sleep(300);
					o.wait();
					
					
				} catch (Exception e) {
				}
			}
		}
	}
	
	public static void main(String args[]) {
		
		new ZeroOne("0").start();
	}
}
