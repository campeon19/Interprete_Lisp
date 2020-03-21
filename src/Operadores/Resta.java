package Operadores;

public class Resta extends MainOperador {

	@Override
	public Object getResult(Object v1, Object v2) {
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return Float.parseFloat(((Integer)v1).toString()) - Float.parseFloat(((Integer)v2).toString());
		}else if (v1 instanceof Float && v2 instanceof Float) {
			return ((Float)v1) - ((Float)v2);
		}
		return null;
	}

}
