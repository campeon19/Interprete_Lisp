package Modelos;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import Operadores.operadorFactory;
import Modelos.*;
import java.util.*;


public class Function implements Functionality{
	private String args0;
	private String functionName;
	private String parameter;
	HashMap<String,String> CONDS =new HashMap<String,String>();
	private operadorFactory oF = new operadorFactory();
	
	
	public Function(String n) {
		
		this.functionName = getName(n);
		this.parameter = getParameter(n);
		
		fillConds(n);
	}
	
	/**
	 * 
	 * @param instruction
	 * @return nombre de la funcion
	 */
	public String getName(String instruction) {//Evaluar por si se cambia a void
	//	System.out.println(instruction);
		String method = instruction.replaceAll("defun", "").replaceAll("DEFUN", "").replaceAll(" ", "");
		String nombre = new String();
		for (int i = 0; i < method.length(); i++) {
			if(!String.valueOf(method.charAt(i)).equals("(")) {

				nombre += method.charAt(i);
			}
			
			if(Character.toString(method.charAt(i+1)).equals("(")) {
				i = method.length();
			}
		}
	//	System.out.println(nombre);
		return nombre;
	}
	
	
	/**
	 * 
	 * @param instruction
	 * @return parametro de la instruccion
	 */
	public String getParameter(String instruction) {
	//	System.out.println(instruction);
		String method = instruction.replaceAll("defun", "").replaceAll("DEFUN", "").replaceAll("\\n", "").replaceAll(" ", "").replaceAll("\\(", "").replaceAll(functionName, "");
		String parametro = new String();
		for (int i = 0; i < method.length(); i++) {
			if(!String.valueOf(method.charAt(i)).equals(")")) {

				parametro += method.charAt(i);
			}
			
			if(Character.toString(method.charAt(i+1)).equals(")")) {
				i = method.length();
			}
		}
	//	System.out.println(parametro);
		return parametro;
		
	}
	
	
	/**
	 * 
	 * @param instrucciones, numero a operar
	 * @return el valor operado de la funcion
	 */
	public int getValueFunction(int instrucciones) {
		
		int Resultado = 0;
		boolean verificador = true;
		
		
		for(Map.Entry<String, String> entry : CONDS.entrySet()) {
			
			if(!(entry.getKey().equals("ELSE")) && verificador) {
				//=N1
				if(Character.toString(entry.getKey().charAt(0)).equals("=")) {
					String comparing = entry.getKey().replaceAll(parameter, "").replaceAll("=", "");
					if(instrucciones==Integer.parseInt(comparing)) 
					{
						Resultado = Integer.parseInt(entry.getValue());
						verificador = false;
						
						
						//return Integer.parseInt(entry.getValue());
					}	
				}
			}

			if(entry.getKey().equals("ELSE")  && verificador && instrucciones>=2) {
				//+(FIBONACCI(-N1))(FIBONACCI(-N2))
				
				
				if(entry.getValue().contains(functionName) ) {  //verifica si existe recursion
					String operador = "" + entry.getValue().charAt(0);
					String valueElse = entry.getValue().substring(1); //(FIBONACCI(-N1))(FIBONACCI(-N2))
					valueElse = valueElse.replaceAll(functionName, ""); //((-N1))((-N2))
					valueElse = valueElse.replaceAll("\\(", "").replaceAll(parameter, ""); //-1))-2))
					String arg1 = "("+ valueElse.charAt(0)+ " "+ instrucciones + " ";  // esto seria tipo "(+ 5 "
					valueElse = valueElse.substring(1); //1))-2))
					
					while(!(Character.toString(valueElse.charAt(0)).equals(")"))) { 
						arg1 += valueElse.charAt(0);   // va concatenando el numero
						
						
						valueElse = valueElse.substring(1); //aqui quita el caracter inicial para evitar while infinito
					}
					//Resultado:
					//Operacion1 = (+ 5 1
					//valueElse= ))-2))
					
					arg1 += ")"; //(+ 5 1)
					
					valueElse = valueElse.replaceAll("\\)", ""); //-2
					String arg2 = "(" + valueElse.charAt(0) +" "+ instrucciones + " ";//"(- 5 "
					valueElse = valueElse.substring(1); //2
					
					arg2 += valueElse + ")";  //(- 5 2)
					
					String operador1;
					String operador2;
					Object val1, val2, val3, val4;
					Stack<String> x1 = defOperation(arg1);
					Stack<String> x2 = defOperation(arg2);
					operador1 = x1.firstElement().trim();
					operador2 = x2.firstElement().trim();
					val1 = getParse(x1.get(1).trim());
					val2 = getParse(x1.get(2).trim());
					val3 = getParse(x2.get(1).trim());
					val4 = getParse(x2.get(2).trim());

					
					Object a1 = operadorFactory.getOp(operador1).getResult(val1, val2); //+getResult(defOperation(arg1));
					Object a2 = operadorFactory.getOp(operador2).getResult(val3, val4);//+getResult(defOperation(arg1));

					int v1 = Math.round((float) a1);
					int v2 = Math.round((float) a2);
					
					
					
					Resultado = getValueFunction(v1) +getValueFunction(v2);
					//return getValueFunction(v1) +getValueFunction(v2);		
				}			
			}
			
			
			
			
		}
		return Resultado;
		
		    

		 
		
		
		
		
		
		
		
	}

	/**
	 * 
	 * @param number
	 * @retu numero parse para operar
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
	 * 
	 * @param inst
	 * @return operacion en stack
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
	 * 
	 * @param s
	 * @return  true si es string
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
	 * devuelve el valor de la funcion operada
	 */
	@Override
	public Object MakeFunction(String[] params) {
		return Integer.toString(getValueFunction(Integer.parseInt(params[0])));
	}
	
	
	
	
	
	/**
	 * llena el hashmap de las condiciones
	 * @param instructions
	 */
	public void fillConds(String instructions) {
		instructions = instructions.replaceAll("defun", "").replaceAll("DEFUN", "").replaceAll("\\n", "").replaceAll(" ", "");
		String condition = new String();
		
		int cantPar = 0;
		
		String nombreCond = "";
		boolean isCondFinish = false;
		int condEnd = 0;
		boolean globalCondFinish = false;
		
		boolean isntElse = true;
		
		String keyForCond = "";
		String valueForCond = "";
		
		for (int i = parameter.length()+3+functionName.length()+1; i < instructions.length(); i++) {
			if(String.valueOf(instructions.charAt(i)).equals("(")) {
				cantPar++;
			}
			//Aqui verifica si ya se cerro la expresion en parentesis
			if(String.valueOf(instructions.charAt(i)).equals(")")) {
				cantPar--;
			}
			
	
			if(String.valueOf(instructions.charAt(i)).equals("C")) { //Verifica si la expresion es COND
				nombreCond += instructions.charAt(i);
				nombreCond += instructions.charAt(i+1);
				nombreCond += instructions.charAt(i+2);
				nombreCond += instructions.charAt(i+3);
				if (nombreCond.equals("COND")) {
				condEnd = i+4;   // apunta una casilla ADELANTE para ver si ya paso COND
				}
				//isCondFinish = false;
				
			}
			if(i==condEnd) {
				globalCondFinish = true;
			}
			
			
			//Verifica cuando ya este adentro del else 
			
			if(String.valueOf(instructions.charAt(i-1)).equals("T")) { //Verifica si la expresion es COND
				if(String.valueOf(instructions.charAt(i-2)).equals("(") & String.valueOf(instructions.charAt(i)).equals("(")) {
					isntElse = false;
					condition = "";
					
					
				}
			}
			
			
			
			//verifica si la condicion se termino y si no existe else
			if(globalCondFinish && isntElse) {
				condition += instructions.charAt(i);
				if(cantPar==0) {
					for (int j = 0; j < condition.length(); j++) {
						if(!String.valueOf(condition.charAt(j)).equals("(") & !String.valueOf(condition.charAt(j)).equals(")")) {
							keyForCond +=condition.charAt(j);
						}
						if(String.valueOf(condition.charAt(j)).equals(")")) {
							j = condition.length();
						}
					}
					for(int j = keyForCond.length()+3; j < condition.length(); j++) {
						if(!String.valueOf(condition.charAt(j)).equals("(") & !String.valueOf(condition.charAt(j)).equals(")")) {
							valueForCond +=condition.charAt(j);
						}
						if(String.valueOf(condition.charAt(j)).equals(")")) {
							j = condition.length();
						}
					}
					
					//Aqui se hace el push de la instruccion
					CONDS.put(keyForCond, valueForCond);
					keyForCond = "";
					valueForCond ="";	
					condition = "";
				}
			}
			
			if(!isntElse) {
				condition += instructions.charAt(i);
				if(cantPar==0) {
					
					condition = condition.substring(1);
					condition = condition.substring(0, condition.length() - 1);
					condition = condition.substring(0, condition.length() - 1);
					CONDS.put("ELSE", condition);
					
					
				}
				
				
			}
			
			
			
		}
		
	}
	
	/**
	 * devuelve el nombre de la funcion
	 */
	@Override
	public String getNombre() {
		return functionName;
	}
}