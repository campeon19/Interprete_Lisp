import java.util.ArrayList;
import java.util.Iterator;

public class Interprete {
	private ArrayList<Integer> datosNumericos;
	private int resultado;
	private String Programa;
	
	public Interprete() {
		datosNumericos = new ArrayList<Integer>();
		Programa = "";
	}

	
	
	public void leerPrograma() {
		Programa = LeerPrograma.txtRead();
	}

	private boolean isOperator(String operator) {
		
		if (operator.equals("*")|| operator.equals("/")|| operator.equals("-")|| operator.equals("+"))
			return true;
		else
		return false;
	}

	private boolean isNumber(String number) {
		
		if (number.equals("0")||number.equals("1")||number.equals("2")||number.equals("3")||number.equals("4")||number.equals("5")||number.equals("6")||number.equals("7")||number.equals("8")||number.equals("9"))
			return true;
		else
		return false;
	}
	
	public int readData(String entry) {
		String[] datosTotales = entry.replaceAll(" ", "").split("");
		
		for (int i = 0; i < datosTotales.length; i++) {
			if(isOperator(datosTotales[i])) {
				if(isNumber(datosTotales[i+1]) && isNumber(datosTotales[i+2])) {
					resultado= Integer.parseInt(datosTotales[i+1]) + Integer.parseInt(datosTotales[i+2]);
				}
			}
			
		}  
		
		 return resultado;
	}

}
