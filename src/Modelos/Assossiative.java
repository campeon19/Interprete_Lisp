package Modelos;

import java.util.Map.Entry;

public class Assossiative<k, v> implements Entry<k,v>{

	private k key;
	private v value;
	
	public Assossiative(k key, v value) {
		this.key = key;
		this.value  = value;
	}
	
	@Override
	public k getKey() {
		// TODO Auto-generated method stub
		return key;
	}
	@Override
	public v getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	@Override
	public v setValue(Object arg0) {
		v temp = value;
		this.value = (v) arg0;
		return temp;
	}
	
	
}
