//import files are here.
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * This Class will Initiate the RMI server on the port no given by server
 * Administrator.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */

public class ServerRMI {
	/**
	 * this remote server creates registry at giver url and port number,then
	 * registers game serve object on the registry.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String portNum, registryURL;
		try {
			System.out.println("Please Enter the RMIregistry port number:");
			portNum = (sc.nextLine()).trim();
			int RMIPortNum = Integer.parseInt(portNum);
			startRegistry(RMIPortNum);
			GameServer exportedGameServer = new GameServer();
			registryURL = "rmi://localhost:" + portNum + "/game";
			Naming.rebind(registryURL, exportedGameServer);
			System.out.println("Game Sever has been put on Registry");
			GameRunner aGameRunner = new GameRunner(exportedGameServer);
			aGameRunner.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// below method starts a RMI registry on the local host, this also can be
	// done with command Line Argument
	private static void startRegistry(int RMIPortNum) throws RemoteException {
		try {
			Registry aRegistry = LocateRegistry.getRegistry(RMIPortNum);
			aRegistry.list();
		} catch (RemoteException e) {
			// No registry exist on this port so a new one will be created here
			Registry aRegistry = LocateRegistry.createRegistry(RMIPortNum);
		}
	}

}
