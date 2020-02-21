/**
 * 
 */

/**
 * @author josej
 *
 */
public class Main {

	public static boolean isOperator(String operator) {
		
		if (operator.equals("*")|| operator.equals("/")|| operator.equals("-")|| operator.equals("+"))
			return true;
		else
		return false;
	}

	public static boolean isNumber(String number) {
		
		if (number.equals("0")||number.equals("1")||number.equals("2")||number.equals("3")||number.equals("4")||number.equals("5")||number.equals("6")||number.equals("7")||number.equals("8")||number.equals("9"))
			return true;
		else
		return false;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
