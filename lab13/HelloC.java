import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * this class is the rmi client.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */
public class HelloC {
	/**
	 * this method tests the local remote.
	 * 
	 * @param obj
	 */
	public static void localRemoteTest(HelloInterface obj) {
		try {
			if (obj.test(InetAddress.getLocalHost().toString()))
				System.out.println("Local Object");
			else
				System.out.println("Remote Object");
			// your code here
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		int portNumber = 1235;
		String sPortNumber = "5050";
		try {
			// startRegistry(portNumber);
			// String registryURL = "//spiegel/thisOne";
			// String registryURL = "rmi://192.168.1.14:"+sPortNumber
			// +"/thisOne";
			// HelloInterface aHelloInterface =new HelloImplementation() ;
			// registryURL = "rmi://localhost:" + portNum+"/game";
			// Naming.rebind(registryURL, exportedGameServer);
			// Naming.rebind(registryURL, aHelloInterface);
			// System.out.println("HelloImplemetation  has been put on Registry and stated the registry");
			// Thread.sleep(1000);
			// localRemoteTest(
			// (HelloInterface)Naming.lookup("rmi://localhost:"+portNumber+"/thisOne")
			// );
			HelloInterface object = (HelloInterface) Naming
					.lookup("rmi://129.21.111.128:" + sPortNumber + "/hello");
			System.out.println(object.toString());
			localRemoteTest(object);
			localRemoteTest(new HelloImplementation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void startRegistry(int portNum) throws RemoteException {
		try {
			Registry aRegistry = LocateRegistry.getRegistry(portNum);
			aRegistry.list();
		} catch (RemoteException e) {
			// No registry exist on this port so a new one will be created here
			Registry aRegistry = LocateRegistry.createRegistry(portNum);
		}
	}

}
