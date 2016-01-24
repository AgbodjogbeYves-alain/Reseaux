
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * Le serveur va servir a creer les clients de calcul et renvoyer le resultat au client qui a demandé le calcul
 * Une des consigne etaient de creer un cache
 */
public class Server {
	private ServerSocket serverSocket;

	/**
	 * Constructeur de la classe serveurFibonacci
	 * 
	 * @param serverSocket
	 *   
	 */
	Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	
	public class ClientThread extends Thread {
		private Socket socketClientThread;
		private InputStream inputStream;
		private OutputStream outputStream;
		int[] cache;
		
		
		
		/**
		 * Constructeur de la classe clientThread
		 * 
		 * @param socket
		 *            le socket du serveur, recupere pour aller le retourner.
		 * @param cache1
		 *            Le tableau qui contient les valeurs deja calculée et qui contiendras les futures valeurs
		 */
		ClientThread(Socket skt, int[] cache2) {
			try {
				this.socketClientThread = skt;
				outputStream = skt.getOutputStream();
				inputStream = skt.getInputStream();
				cache = cache2;
			} catch (Exception e) {
			}
		}

		/**
		 * Dans le run on va verifier si le resultat est dans le cache, si le chiffre entré est superieur a 0 
		 * Si il n'est pas dans le cache on creer 2 nouveaux clients pour le calcul recursif.
		 * Si il est dans le cache on renvoie la valeur indexée a l'indice [compteur].
		 */
		@Override
		public void run() {
			Scanner sc = new Scanner(inputStream);
			String text = "bonjour";
			if (sc.hasNext()) { 
				text = sc.nextLine();
			}
			
			int compteur = Integer.parseInt(text);
			PrintWriter printWrite = new PrintWriter(outputStream);
			if (compteur == 0) { 
				printWrite.println(1);
			} 
			
			else
				{if(compteur < 0) 
					{printWrite.println("Entier negatif : entrez un entier valide");}
				
				else {	
					if (compteur >= 0)
					{
						if (cache[compteur]==0)
						{
							Client client = new Client(compteur - 1, serverSocket.getLocalPort());
							client.clientRun();
							int result = client.getResult();
							client.setResult(result * compteur);
							cache[compteur-1] = result ;
							printWrite.println(result * compteur);
						} else {
						printWrite.println(cache[compteur]);
						}
					}
				}
				}
			printWrite.flush();
			try {
				this.socketClientThread.close();
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			Server server = new Server(serverSocket);
			int[] cache = new int[9999999];
			while (true) {
				Socket socketClient = serverSocket.accept();
				ClientThread clientThread = server.new ClientThread(socketClient, cache);
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}