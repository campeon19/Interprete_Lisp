import java.io.ObjectInputStream.GetField;
import java.util.HashMap;

public class Function {
	private String args0;
	private String functionName;
	private String parameter;
	HashMap<String,String> CONDS =new HashMap<String,String>();
	
	
	public Function(String n) {
		this.parameter = getParameter(n);
		this.functionName = getName(n);
		fillConds(n);
	}
	public String getName(String instruction) {//Evaluar por si se cambia a void
		String method = instruction.replaceAll("defun", "").replaceAll("DEFUN", "").replaceAll("\\n", "").replaceAll(" ", "");
		String nombre = new String();
		for (int i = 0; i < method.length(); i++) {
			if(!String.valueOf(method.charAt(i)).equals("(")) {

				nombre += method.charAt(i);
			}
		}
		return nombre;
	}
	
	public String getParameter(String instruction) {
		String method = instruction.replaceAll("defun", "").replaceAll("DEFUN", "").replaceAll("\\n", "").replaceAll(" ", "").replaceAll("(", "").replaceAll(functionName, "");
		String parametro = new String();
		for (int i = 0; i < method.length(); i++) {
			if(!String.valueOf(method.charAt(i)).equals(")")) {

				parametro += method.charAt(i);
			}
		}
		
		return parametro;
		
	}
	
	public int getValueFunction(String instrucciones) {
		if(Integer.parseInt(instrucciones.replaceAll(functionName, "").replaceAll(" ", "").replaceAll("(", "").replaceAll(")", ""))==1 ||Integer.parseInt(instrucciones.replaceAll(functionName, "").replaceAll(" ", "").replaceAll("(", "").replaceAll(")", ""))==0) {
	    	return 1;
		}
		
	//	for(HashMap.Entry<String, String> entry : CONDS.entrySet()) {
			
			
			
			
			
			
			
			/**if(Character.getNumericValue(entry.getKey().charAt(entry.getKey().length()-1)) 
	    	== Integer.parseInt(instrucciones.replaceAll(functionName, "").replaceAll(" ", "").replaceAll("(", "").replaceAll(")", ""))) {
	    		return Integer.parseInt(entry.getValue());
	    	}
		    if(entry.getKey().equals("ELSE")) {
		    	
		    }
		    else {
		    	
		    	
		    	return 1;
		    	
		    	
		    	//lo de abajo siempre va a dar 1
		    	/**if(Character.getNumericValue(entry.getKey().charAt(entry.getKey().length()-1)) 
		    	== Integer.parseInt(instrucciones.replaceAll(functionName, "").replaceAll(" ", "").replaceAll("(", "").replaceAll(")", ""))) {
		    		return Integer.parseInt(entry.getValue());**/
		    	
		   // }
		    

		 
		
		
		else {
			
			String fibo = CONDS.get("ELSE").replaceAll(functionName, "").replaceAll(" ", "").replaceAll("(", "").replaceAll(")", "");
			String arg1 = "("+fibo.charAt(0)+" "+fibo.charAt(1)+" "+fibo.charAt(2)+")";
			String arg2 = "("+fibo.charAt(2)+" "+fibo.charAt(4)+" "+fibo.charAt(5)+")";
			arg1.replaceAll(parameter, instrucciones.replaceAll(functionName, "").replaceAll(" ", "").replaceAll("(", "").replaceAll(")", ""));
			arg2.replaceAll(parameter, instrucciones.replaceAll(functionName, "").replaceAll(" ", "").replaceAll("(", "").replaceAll(")", ""));
			
			int a1 = 0;//+getResult(defOperation(arg1));
			int a2 = 0;//+getResult(defOperation(arg1));
			
				
			
			return a1+a2;
			
			
			
		}
		
		
		
		
	}
	
	
	
	
	public Object MakeFunction(String[] params) {
		
		
		
		
		return getValueFunction(params[0]);
	}
	
	
	
	
	
	
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
				condEnd = i+4;   // apunta una casilla ADELANTE para ver si ya paso COND
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
			if(globalCondFinish || isntElse) {
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
					CONDS.put("ELSE", condition);
					
					
				}
				
				
			}
			
			
			
		}
		
	}
	

}
