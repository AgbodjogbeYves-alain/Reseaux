import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class client2 {
	 public static void main(String args[]) {
	      try {
	    	 int N;			//AJOUT MATHIEU
	    	 int FactN; 	//AJOUT MATHIEU
	         Socket skt = new Socket("localhost", 5001);
	         BufferedReader in = new BufferedReader(new
	            InputStreamReader(skt.getInputStream()));
	         System.out.print("Message re�u: '");

	    while (true)
	    {
	         while (!in.ready()) {}
	        System.out.println(in.readLine()); // Read one line and output it

	         System.out.print("'\n");
	         
	         N = Factorielle.demanderNombre();				//AJOUT MATHIEU
	         FactN = Factorielle.factorielleRecursive(N);	//AJOUT MATHIEU
	         System.out.print(FactN);						//AJOUT MATHIEU
	         
	         in.close();}
	    }
	      catch(Exception e) {
	         System.out.print("Whoops! It didn't work!\n");
	      }
	    
	      
	      
	   }
}
