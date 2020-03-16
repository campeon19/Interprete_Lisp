import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

public class Interprete {
	
	private HashMap<Integer,String> operaciones;
	private ArrayList<String> lineas;
	
	public Interprete() {
		lineas = null;
		operaciones = new HashMap<Integer,String>();
		
	}
	
	public void interpreterManager() {
		leerPrograma();
		lineas = CleanProgram(lineas);
		separateFunctions();
	}
	
	public void leerPrograma() {
		lineas = LeerPrograma.txtRead();
	}
	
	


	public ArrayList<String> CleanProgram(ArrayList<String> lineass) {
		ArrayList<String> temp = new ArrayList<String>();
		for (String j: lineass) {
			if (!isComentario(j)) {
				temp.add(j);
			}
		}
		for (int i = 0;i<temp.size();i++) {
			if (temp.get(i).length() == 0) {
				temp.remove(i);
			}
		}
		return temp;	
	}
	
	
	private boolean isComentario(String linea) {
		char[] temp = linea.toCharArray();
		for (char h:temp) {
			if (h == ';') {
				return true;
			}
		}
		return false;
	}
	
	public void separateFunctions() {
		String all = "";
		for (String h: lineas) {
			all += h;
		}
		String[] split = all.split("");
		
		int pI = 0;
		int pD = 0;
		String func = "";
		int contador = 1;
		for (int i = 0; i< split.length;i++) {
			
			if (split[i].equals("(")) {
				pI++;
			}
			
			if (split[i].equals(")")) {
				pD++;
			}
			
			func += split[i];
			
			if (pI == pD) {
				operaciones.put(contador, func);
				contador++;
				pI = 0;
				pD = 0;
				func = "";
			}
		}
		 printMap();
	}
	
	public void printMap() {
		for (int i = 1;i<= operaciones.size();i++) {
			System.out.println(i + ",=    " +operaciones.get(i));
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
