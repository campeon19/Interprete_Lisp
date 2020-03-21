package Condiciones;

public class MayorIgual extends MainCondition{

	@Override
	public boolean getResult(Object v1, Object v2) {
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return ((Integer)v1) >= ((Integer)v2);
		}else if (v1 instanceof Float && v2 instanceof Float) {
			return ((Float)v1) >= ((Float)v2);
		}
		return false;
	}

}
