import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import Modelos.Funcion;
import Modelos.Function;
import Modelos.Functionality;

public class Interprete {
	
	private HashMap<Integer,String> operaciones;
	private ArrayList<String> lineas;
	private HashMap<Integer, Functionality> funciones;
	
	public Interprete() {
		lineas = null;
		operaciones = new HashMap<Integer,String>();
		funciones = new HashMap<Integer,Functionality>();
		
	}
	
	
	/**
	 * Lleva el control de los pasos que se realizaran en el programa
	 */
	public void interpreterManager() {
		leerPrograma();
		lineas = CleanProgram(lineas);
		separateFunctions();
		Interpretar();

	}
	
	/**
	 * Leer el programa del txt o .lisp
	 */
	public void leerPrograma() {
		lineas = LeerPrograma.txtRead();
	}
	
	


	/**
	 * Esta funcion limpia el programa de los comentarios
	 * @param lineass
	 * @return
	 */
	public ArrayList<String> CleanProgram(ArrayList<String> lineass) {
		ArrayList<String> temp = new ArrayList<String>();
		for (String j: lineass) {
			temp.add(CleanComentario(j));
		}
		for (int i = 0;i<temp.size();i++) {
			if (temp.get(i).length() == 0) {
				temp.remove(i);
			}
		}
		return temp;	
	}
	
	/**
	 * Esta funcion limpia los comentarios
	 * @param linea
	 * @return
	 */
	private String CleanComentario(String linea) {
		String r = "";
		String[] split = linea.split("");
		boolean salida = true;
	
		for (int i = 0; i< split.length;i++) {
			if (split[i].equals(";")) {
				salida = false;
			}
				
			if (salida) {
				r += split[i];
			}
		}
		

		return r;
	}
	
	/**
	 * Esta funcion separa todas las operaciones o metodos del programa de lisp
	 */
	public void separateFunctions() {
		operaciones.clear();
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
			
			if (pI == pD && pI >0 && pD > 0) {
				operaciones.put(contador, func.trim());
				contador++;
				pI = 0;
				pD = 0;
				func = "";
			}
		}
	}
	
	public void printMap(String a) {
		for (int i = 1;i<= funciones.size();i++) {
			System.out.println(i + ",=" +a);
		}
	}
	
	/**
	 * Este metodo se encarga de interpretar todo el programa desde los metodos hasta los print y write
	 */
	public void Interpretar() {
		for (int i = 1; i<= operaciones.size();i++) {
			  if (operaciones.get(i).contains("DEFUN")  && !operaciones.get(i).contains("COND")) {
				  funciones.put(funciones.size() + 1, new Funcion(operaciones.get(i)));
			  }else if (operaciones.get(i).contains("DEFUN")  && operaciones.get(i).contains("COND")) {
				  funciones.put(funciones.size() + 1, new Function(operaciones.get(i)));
			  }

			  if (operaciones.get(i).contains("print")) {
				  String[] split = operaciones.get(i).split("");
				  String texto = "";
				  int p = 0;
				  for (String l: split) {
					  if (l.equals("\"")) {
						  p++;
					  }
					  if (p>0 && p<2 && !l.equals("\"")){
						  texto += l;
					  }	  
				  }
				  System.out.println(texto);
			  }
			  
			  if (operaciones.get(i).contains("write")) {
				  String[] split = operaciones.get(i).split("");
				  int p = 0;
				  String texto = "";
				  for (String l: split) {
					  if (l.equals("(")) {
						  p++;
					  }
					  if (p==2 && !l.equals("(")  && !l.equals(")")){
						  texto += l;
					  }	  
				  }
				  texto = texto.trim();
				  String[] s = texto.split(" ");
				  
				  for (int ij = 1; ij<= funciones.size();ij++) {
					  if (funciones.get(ij).getNombre().equals(s[0])) {
						  
						  if (isNumber(s[1],null)) {
							  System.out.println(funciones.get(ij).MakeFunction(new String [] {s[1]}));
						  }else {
							  for (int ii = 1; ii<= funciones.size();ii++) {
								  if (funciones.get(ij).getNombre().equals(s[1])) {
									  System.out.println(funciones.get(ij).MakeFunction(new String[] {}));
								  }
							  }
						  }
						  
						 
					  }
				  }
				  
			  }
			  
			 
			 
		}
		// funciones.get(1).MakeFunction(new String[] {"4"});
	}
	
	
	/**
	 * FUncion para comprobar que un string en numero o no
	 * @param number
	 * @param i
	 * @return
	 */
	private boolean isNumber(String number,Object i) {
		try {
			i = Integer.parseInt(number);
			return true;
			
		}catch(Exception e) {i = null;}
		try {
			i = Float.parseFloat(number);
			return true;
		}catch(Exception e) {i = null;}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
