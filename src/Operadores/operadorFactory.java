package Operadores;

public class  operadorFactory {
	
	public static MainOperador getOp(String op) {
		switch (op) {
		case "+":
			return new Suma();
		case "-":
			return new Resta();
		case "*":
			return new Multiplicacion();
		case "/":
			return new Division();
		default: return null;
		}
	}

}
