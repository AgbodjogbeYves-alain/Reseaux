import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe client, pour demander un calcul fibonnacci, et avoir le résultat.
 *
 */
public class ClientFibonacci {
	private int compteur;
	private int result;
	private int localport;


	/**
	 * Constructeur de la classe client client.
	 * 
	 * @param x
	 *            le compteur nous permettra de savoir si la valeur est dans le cache sinon de la calculer
	 * @param port
	 *            le port ou on envoi et ou arrivent les informations.
	 */
	ClientFibonacci(int x, int localport1) {
		this.compteur = x;
		this.result = 1;
		this.localport = localport1;
	}
	
	/**
	 * Le port et le nombre pour le calcul devront etre entré en console
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClientFibonacci client = new ClientFibonacci(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		client.clientRun();
		System.out.println(client.getResult());
	}

	/**
	 * Le run sert a traiter la demande de calcul pour fibonacci. Il fait une demande de calcul au serveur  .
	 */
	public void clientRun() {
		try {
			Socket clientSocket = new Socket(InetAddress.getLocalHost(), this.localport);
			
			PrintStream output = new PrintStream(clientSocket.getOutputStream());
			
			
			String text = Integer.toString(this.compteur);
			
			Scanner sc = new Scanner(clientSocket.getInputStream());
			
			PrintWriter printWriter = new PrintWriter(output);
			printWriter.println(text);
			printWriter.flush();
			if (sc.hasNext()) {
				String texte = sc.nextLine();
				this.result = Integer.parseInt(texte);
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
	
	public int getResult() {
		return this.result;
	}

	public void setResult(int x) {
		this.result = x;
	}
}
