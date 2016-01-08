// ReceiverTCP.java

import java.util.*;
import java.net.*;
import java.io.*;

public class ReceiverTCP
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket varSocket = new ServerSocket(50000);
		/*Le Receiver TCP recevra les donn�es sur le port 50000*/
		
		Socket varSock = varSocket.accept();
		/* La m�thode accept accepte une connexion TCP avec le client
		 * et retourne la socket vers ce client */
		
		Scanner varScanner = new Scanner(varSock.getInputStream());
		/*La m�thode getOutputStream rend la stream d'entr�e (lecture)*/
		String varS = "null";
		while (!varS.equals("stop"))
		{
			varS = varS.nextline();
			System.out.println(s3);
		}
		
		varSocket.close();
		sock.close();
		/* Les sockets clients et serveurs doivent �tre ferm�es avec 
		 * la m�thode close*/
	}
}




/*Correction du prof:
class ReceiverTCP {

  public static void main(String argv[]) throws SocketException, IOException {
    // Socket that waits packets over port 50000
    ServerSocket socket = new ServerSocket(50000);
    String msg;
    // To accept a TCP connection
    Socket s = socket.accept();
    // To read from the socket
    Scanner sc = new Scanner(s.getInputStream());
    while (true) {
      msg = sc.nextLine();
      System.out.println("Received: " + msg);
    }//while
  }//main

}//ReceiverTCP
*/
