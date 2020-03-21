package Condiciones;

public class condFactory {

	
	public static MainCondition getCond(String predicado) {
		switch(predicado) {
		case"<":
			return new Menor();
		case ">":
			return new Mayor();
		case "==":
			return new IgualIgual();
		case "<=":
			return new MenorIgual();
		case ">=":
			return new MayorIgual();
		default: return null;
		}
	}
	
	
	
}
