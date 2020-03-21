import static org.junit.Assert.*;

import org.junit.Test;

import Condiciones.condFactory;
import Modelos.Funcion;
import Operadores.MainOperador;
import Operadores.operadorFactory;

public class PruebasUnitarias {

	@Test
	public void test() {
		Funcion funcion = new Funcion("(DEFUN farhenheitCelcius (c) (* (- c 32) 0.5556))");
		assertEquals("farhenheitCelcius", funcion.getNombre());
		assertEquals("37.7808", funcion.MakeFunction(new String[] {"100"}).toString());
	}
	
	
	@Test
	public void test1() {
		Object op = operadorFactory.getOp("+").getResult(10, 20);
		assertEquals("30.0", op.toString());
		
		op = operadorFactory.getOp("-").getResult(10, 20);
		assertEquals("-10.0", op.toString());
		
		op = operadorFactory.getOp("*").getResult(10, 20);
		assertEquals("200.0", op.toString());
		
		op = operadorFactory.getOp("/").getResult(10, 20);
		assertEquals("0.5", op.toString());
	}
	
	@Test
	public void test2() {
		boolean resp = condFactory.getCond("==").getResult(10, 10);
		assertEquals(true, resp);
		
		resp = condFactory.getCond(">").getResult(10, 10);
		assertEquals(false, resp);
		
		resp = condFactory.getCond("<").getResult(5, 10);
		assertEquals(true, resp);
	}

}
