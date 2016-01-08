//SenderTCP.java

import java.util.*;
import java.net.*;
import java.io.*;

public class SenderTCP 
{
	InetAddress varIP = InetAddress.getByName("127.0.0.1");
	
	Socket varSocket = new Socket(varIP,50000);
	/*Le SenderTCP émettra les données vers le port 50000*/
	
	PrintStream output = new PrintStream(varSocket.getOutputStream());
	/*La méthode getOutputStream rend la stream de sortie (écriture)*/
	
	
	String varStr = varSc.nextline();
	
	output.println(varStr);
	
	varSocket.close();
}



/*Correction du prof TP1
class SenderTCP {

public static void main(String argv[])
 throws SocketException, IOException, UnknownHostException {
 InetAddress address = InetAddress.getLocalHost();
 // Socket toward address and port 50000
 Socket socket = new Socket(address, 50000);
 // To print over the socket
 PrintStream output = new PrintStream(socket.getOutputStream());
 Scanner sc = new Scanner(System.in);
 String msg;
 while (true) {
   msg = sc.nextLine();
   output.println(msg);
 }//while
}//main

}//SenderTCP
*/
