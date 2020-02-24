
public class Calculos {

	public static int suma(Integer[] datos) {
		int resultado = 0;
		for (float h : datos) {
			resultado += h;
		}
		return resultado;
	}
	
	public static int resta(Integer[] datos) {
		int resultado = datos[0];
		if (datos.length > 1) {
			for (int i = 1; i< datos.length ; i++) {
				resultado -= datos[i];
			}
		}
		return resultado;
	}
	
	public static int multiplicacion(Integer[] datos) {
		int resultado = 1;
		for (float h : datos) {
			resultado *= h;
		}
		return resultado;
	}
	
	
	public static int division(Integer[] datos) {
		int resultado = datos[0];
		if (datos.length > 1) {
			for (int i = 1; i< datos.length ; i++) {
				resultado /= datos[i];
			}
		}
		return resultado;
	}
	
	
	
	
	
}
