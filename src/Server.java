
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
 * Classe du serveur, pour gerer les clients et pouvoir creer d'autres clients.
 * 
 * @return les resultats aux clients qui demandent une factorielle, avec un
 *         cache prevu.
 * 
 */
public class Server {
	private ServerSocket serverSocket;

	/**
	 * Constructeur du serveur
	 * 
	 * @param serverSocket
	 *            le socket du serveur.
	 */
	Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	/**
	 * Classe du client en thread, utilise de façon recursive.
	 *
	 */
	public class ClientThread extends Thread {
		private Socket socketClientThread;
		private InputStream inputStream;
		private OutputStream outputStream;
		int[] cache;
		
		
		
		/**
		 * Constructeur du clientThread
		 * 
		 * @param socket
		 *            le socket du serveur, recupere pour aller le retourner.
		 * @param Resultats
		 *            la Hashtable pour rentrer les résultats au fur et a
		 *            mesure.
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
		 * Run du client thread, permet de recevoir l'etape correspondante et
		 * demander au serveur le resultat suivant.
		 */
		@Override
		public void run() {
			Scanner sc = new Scanner(inputStream);
			String text = "bonjour";
			if (sc.hasNext()) { // s'il y a un suivant
				text = sc.nextLine();
			}
			int compteur = Integer.parseInt(text);
			PrintWriter printWrite = new PrintWriter(outputStream);
			if (compteur == 0) { // Si on arrive a zero, on renvoie le
									// resultat.
				printWrite.println(1);
			} else {	
					if (cache[compteur] == 0)
					{
						Client client = new Client(compteur - 1, serverSocket.getLocalPort());
						client.clientRun();
						int result = client.getResult();
						client.setResult(result * compteur);
						cache[compteur-1] = result ;
						printWrite.println(result * compteur);
					} else {// On renvoie le resultat stocke si ce n'est pas le cas.
						//printWrite.println(cache.get(compteur));
						printWrite.println(cache[compteur]);
						}
					}
			printWrite.flush();
			try {
				this.socketClientThread.close();
				sc.close();// On le ferme une fois fini.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {// Un nouveau serveur avec comme parametre le port donne lors de la
				// commande.
			serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			Server server = new Server(serverSocket);
			int[] cache = new int[999999999];
			while (true) {// On accepte tous les clients, et on fait un nouveau
							// thread a chaque fois.
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