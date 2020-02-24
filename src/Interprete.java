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
		String[] datosTotales = entry.split("");
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		String operador = "";
		
		for (int i = 0; i < datosTotales.length; i++) {
			
			if (isOperator(datosTotales[i])) {
				operador = datosTotales[i];
			}
			
			if (isNumber(datosTotales[i])) {
				numeros.add(Integer.parseInt(datosTotales[i]));
			}
		}  
		
		resultado = operar(operador,numeros);
		
		 return resultado;
	}
	
	private int operar(String op, ArrayList<Integer> data) {
		int resultado = 0;
		Integer[] dat = new Integer[data.size()];
		
		for (int i = 0; i<data.size();i++) {
			dat[i] = data.get(i);
		}
		
		switch(op) {
		
		case "+":
			resultado = Calculos.suma(dat);
			break;
		case "-":
			resultado = Calculos.resta(dat);
			break;
		case "*":
			resultado = Calculos.multiplicacion(dat);
			break;
		case "/":
			resultado = Calculos.division(dat);
			break;
		}
		return resultado;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
