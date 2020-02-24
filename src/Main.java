import java.util.Scanner;

/**
 * 
 */

/**
 * @author josej
 * @author andrei portales 19825
 *
 */
public class Main {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Interprete interprete = new Interprete();
		boolean salida = false;
		
		while(!salida) {
			System.out.println("\n\n\nIngrese la expresion posfix que desea operar o 'salir' para finalizar\n ");
			String input = scan.nextLine();
			
			if (input.equals("salir")) {
				salida = true;
			}else {
				System.out.println("Resultado = " + interprete.readData(input));
			}
		}
		
		System.out.println("\n\n\nFin");
		System.exit(0);
		
		
		
	}

}
