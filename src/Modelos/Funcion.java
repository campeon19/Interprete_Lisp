package Modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;

import Condiciones.condFactory;
import Operadores.operadorFactory;

public class Funcion implements Functionality{
	private String funcion;
	private String nombre;
	private HashMap<Integer, Assossiative<String,Object>> parametros;
	private HashMap<String,Object> variables;
	private HashMap<String,Assossiative<Object,Object>> condiciones;
	//private HashMap<Integer,Assossiative<String,Object>> operaciones;
	private int ccc ;
	
	
	public Funcion(String funcion) {
		ccc = 0;
		this.funcion = funcion;
		this.nombre = "";
		parametros = new HashMap<Integer, Assossiative<String,Object>>();
		variables = new HashMap<String, Object>();
		condiciones = new HashMap<String,Assossiative<Object,Object>>();
		//operaciones = new HashMap<Integer,Assossiative<String,Object>>();
		nombre = funcion.split(" ")[1];
	}
	
	/**
	 * Esta funcion va a agarrar los parametros y va a interpretar la funcion en base a los valores de los parametros y va a dar un resultado
	 */
	public Object MakeFunction(String[] items) {

		Stack<String> stack =  new Stack<String>();
		getFunctionArray(stack);
		setParameters(stack);
		for (int i=0; i<items.length; i++) {
			parametros.get(i + 1).setValue(items[i]);
		}
		Object valor = null;
		
		for (String inst: stack) {
			if (!inst.contains("COND") && !inst.contains("SETQ")) {
				String operador = "";
				Object v1 = "";
				Object v2 = "";
				Stack<String> terms = defOperation(inst);
				operador = terms.firstElement().trim();
				terms.remove(0);
				Object[] datos = new Object[2];
				
				for (String t:terms) {
					if (!isNumber(t,null)) {
						if (t.contains("+") || t.contains("-") || t.contains("*") || t.contains("/")) {
							Stack<String> term = defOperation("("+t+")");
							String operador1 = term.get(0).trim();
							Object v11 = null;
							Object v21 = null;
							
							if (isNumber(term.get(1).trim(),null)) {
								v11 = getParse(term.get(1).trim());
							}else {
								for (int i = 1 ; i<= parametros.size();i++) {
									if (parametros.get(i).getKey().equals(term.get(1).trim())) {
										v11 = getParse((String)parametros.get(i).getValue());
									}
								}
							}
							
							if (isNumber(term.get(2).trim(),null)) {
								v21 = getParse(term.get(2).trim());
							}else {
								for (int i = 1 ; i<= parametros.size();i++) {
									if (parametros.get(i).getKey().equals(term.get(2).trim())) {
										v21 = getParse((String)parametros.get(i).getValue());
									}
								}
							}
							
							
							datos[terms.indexOf(t)] = operadorFactory.getOp(operador1).getResult(v11, v21);
							
							
						}else {
							for (int i = 1 ; i<= parametros.size();i++) {
								if (parametros.get(i).getKey().equals(t)) {
									datos[terms.indexOf(t)] = getParse((String)parametros.get(i).getValue());
								}
							}
						}
					}else {
						datos[terms.indexOf(t)] = getParse((String)t);
					}
					
				}
				valor = operadorFactory.getOp(operador).getResult(datos[0], datos[1]);
			}else if (inst.contains("COND")) {
				
				
			}
		}
		
		return valor;
	}
	
	/**
	 * Esta funcion toma una expresion (+ 5 6) y devuelve un stack con los valores del operador y los operandos
	 * @param inst
	 * @return
	 */
	private Stack<String> defOperation(String inst) {
		Stack<String>temp =  new Stack<String>();
		String[] split = inst.split("");
		split[0] = "";
		split[split.length - 1] = "";
		int pi = 0;
		int pd = 0;
		String txt = "";
		for (String jk: split) {
			if (jk.equals("(")) 
				pi ++;
			if (jk.equals(")"))
				pd ++;
			if (!jk.equals("(") && !jk.equals(")")) {
				txt += jk;
			}
			if ((jk.equals("") || jk.equals(" ")) && txt.length() > 0 && pi == 0 && isText(txt)) {
				temp.add(txt.trim());
				txt = "";
			}
			if (pi == pd && pi>0 && isText(txt)) {
				temp.add(txt.trim());
				txt = "";
				pi = 0;
				pd = 0;
			}
		}
		return temp;
	}
	
	
	/**
	 * Comprobar que string tenga texto en su interior y no sea solo espacios en blanco
	 * @param s
	 * @return
	 */
	private boolean isText(String s) {
		int i = 0;
		for (String h: s.split("")) {
			if (!h.equals(" ")) {
				i++;
			}
		}
		if (i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * funcion para obtener un parseo de string si contiene numeros
	 * @param number
	 * @return
	 */
	private Object getParse(String number) {
		try {
			return  Integer.parseInt(number);
		}catch(Exception e) {}
		try {
			return  Float.parseFloat(number);
		}catch(Exception e) {}
		
		return null;
	}
	
	/**
	 * Comprobar si un string en un numero
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
	
	
	/**
	 * Funcion para crear objetos en el mapa que tenga nombre de parametro y el valor que en este caso son tipo object
	 * @param arreglo
	 */
	private void setParameters(Stack<String> arreglo) {
		Stack<String> temp =  new Stack<String>();
		String[] p = arreglo.firstElement().split("");
		arreglo.remove(0);
		
		for (String o : p) {
			if (!o.equals("(") && !o.equals(")") && !o.equals(" ")) {
				temp.add(o);
			}
		}
		
		for (String l: temp) {
			boolean hay = false;
			Collection<Assossiative<String, Object>> params = parametros.values();
			for (Assossiative<String, Object> hh : params) {
				if (hh.getKey().equals(l)) {
					hay = true;
				}
			}
			
			if (hay == false) {
				parametros.put(parametros.size() + 1, new Assossiative<String,Object>(l,null));
			}
			
		}
		
	}
	
	/**
	 * Separa todas las funcionalidades o partes de la funcion
	 * @param arreglo
	 */
	private void getFunctionArray(Stack<String> arreglo) {
		
		String[] split = funcion.split("");

		int pi = 0;
		int pd = 0;
		
		split[0] = "";
		split[split.length - 1] = "";
		String as = "";
		for (String h: split) {
			if (h.equals("(")) {
				pi++;
			}
				
			if (h.equals(")")) {
				pd++;
			}
			
			if (pi > 0) {
				as += h;
			}
			
			if (pi == pd && pi > 0 && pd > 0) {
				arreglo.add(as);
				as = "";
				pi = 0;
				pd = 0;
			}
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
	
	
	
	
}
