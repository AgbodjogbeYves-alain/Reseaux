import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class Client {
	
	
	private int result;
	private int localport;
	private int compteur;
	/**
	 * Constructeur du clientfactorielle.
	 * 
	 * @param x
	 *            le compteur nous permettra de savoir si la valeur est dans le cache sinon de la calculer
	 * @param localport
	 *            le port ou on envoi et ou arrivent les informations.
	 */
   
	public Client(int x, int localPort) {
		this.compteur = x;
		this.result = 1;
		this.localport = localPort;
		
		}

	/**
	 * Le port et le nombre pour le calcul devront etre entré en console
	 * @param args
	 */
	public static void main(String[] args) throws IOException {// On recupere les arguments de la
		// console pour creer le client, on
		// le lance puis on affiche le
		// resultat.

		Client client = new Client(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		client.clientRun();
		System.out.println(client.getResult());
	}
	
	/**
	 * Le clientrun sert a traiter la demande de calcul pour factorielle. Il fait une demande de calcul au serveur  .
	 */
	public void clientRun() {
		// TODO Auto-generated method stub
			try {
				 InetAddress address = InetAddress.getLocalHost();
				Socket clientSocket = new Socket(address, this.localport);
				PrintStream output = new PrintStream(clientSocket.getOutputStream());
				String text = Integer.toString(this.compteur);
				Scanner sc = new Scanner(clientSocket.getInputStream());
				PrintWriter printWrite= new PrintWriter(output);
				printWrite.println(text);
				printWrite.flush();// Nécessaire pour le bon fonctionnement.
				if (sc.hasNext()) {
					String texte2 = sc.nextLine();
					this.result = Integer.parseInt(texte2);
				}
				clientSocket.close();
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/** 
	 * Les getteurs et setteurs servent à modifier le resultat ou a le recuperer
	 * 
	 * @return
	 */
	public void setResult(int x) {
		// TODO Auto-generated method stub
			this.result = x;
		}

		public int getResult() {
			// TODO Auto-generated method stub
			return this.result;
		}
   	}

//}