import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Classe du serveur, pour gerer les clients et pouvoir creer d'autres clients.
 * 
 * @return les resultats aux clients qui demandent une factorielle, avec un
 *         cache prevu.
 * 
 */
public class ServerFibonacci {
	private ServerSocket serverSocket1;
	private ServerSocket serverSocket2;

	/**
	 * Constructeur du serveur
	 * 
	 * @param serverSocket
	 *            le socket du serveur.
	 */
	ServerFibonacci(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	/**
	 * Classe du client en thread, utilise de fa�on recursive.
	 *
	 */
	public class ClientThread extends Thread {
		private Socket socket;
		private InputStream input;
		private OutputStream output;
		private Hashtable<Integer, Integer> resultats;

		/**
		 * Constructeur du clientThread
		 * 
		 * @param socket
		 *            le socket du serveur, recupere pour aller le retourner.
		 * @param Resultats
		 *            la Hashtable pour rentrer les r�sultats au fur et a
		 *            mesure.
		 */
		ClientThread(Socket socket, Hashtable<Integer, Integer> Resultats) {
			try {
				this.socket = socket;
				output = socket.getOutputStream();
				input = socket.getInputStream();
				resultats = Resultats;
			} catch (Exception e) {
			}
		}

		/**
		 * Run du client thread, permet de recevoir l'etape correspondante et
		 * demander au serveur le resultat suivant.
		 */
		@Override
		public void run() {
			Scanner sc = new Scanner(input);
			String text = "";
			if (sc.hasNext()) { // s'il y a un suivant
				text = sc.nextLine(); 
			}
			int N = Integer.parseInt(text);
			PrintWriter pw = new PrintWriter(output);
			if (N == 0 || N == 1) { // Si on arrive a zero ou un, on renvoie le
									// resultat.
				pw.println(N);
			} else {
				if (resultats.get(N) == null) {// On verifie si le
														// resultat est absent
														// des resultats deja
														// presents. Si oui, on
														// le calcule.
					ClientFibonacci client1 = new ClientFibonacci(N - 1, serverSocket1.getLocalPort());
					ClientFibonacci client2 = new ClientFibonacci(N - 2, serverSocket2.getLocalPort());
					client1.clientRun();
					client2.clientRun();
					int answer1 = client1.getAnswer();
					int answer2 = client2.getAnswer();
					client1.setAnswer(answer1);
					client2.setAnswer(answer2);
					resultats.put(N - 1, answer1);
					resultats.put(N - 2, answer2);
					
					pw.println(answer1 + answer2);
					
				} else {// On renvoie le resultat stocke si ce n'est pas le cas.
					pw.println(resultats.get(N));
				}

			}
			pw.flush();
			try {
				this.socket.close();
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
			ServerFacto server = new ServerFacto(serverSocket);
			Hashtable<Integer, Integer> resultats = new Hashtable<Integer, Integer>();

			while (true) {// On accepte tous les clients, et on fait un nouveau
							// thread a chaque fois.
				Socket socketClient = serverSocket.accept();
				ClientThread clientThread = server.new ClientThread(socketClient, resultats);
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