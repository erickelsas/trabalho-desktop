package trabalho_desktop.model;

//Nome: Erick Elsas de Freitas - RA: 2347938

import trabalho_desktop.exceptions.CrmLengthException;
import trabalho_desktop.exceptions.StateException;


public class Crm implements java.io.Serializable{
        private static final long serialVersionUID = 4;
        
	private String state;
	private int num;

	public Crm(){
		state = "";
		num = 0;
	}

	public String getEstado(){
		return state;
	}

	final public void setEstado(String estado) throws StateException{
		estado = estado.toUpperCase();
		if(estado.length() == 2){
			if(estado.matches("[A-Z]*")){
				this.state = estado;
			} else {
				throw new StateException();
			}
		} else {
			throw new StateException();
		}
	}

	public int getNum(){
		return num;
	}

	final public void setNum(int num) throws CrmLengthException{
		if(Integer.toString(num).length() == 6){
			this.num = num;
		} else {
			throw new CrmLengthException();
		}
	}
}