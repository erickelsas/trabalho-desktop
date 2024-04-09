package trabalho_desktop.model;

//Nome: Erick Elsas de Freitas - RA: 2347938

import java.util.GregorianCalendar;
import trabalho_desktop.exceptions.InvalidDayException;
import trabalho_desktop.exceptions.InvalidMonthException;
import trabalho_desktop.exceptions.InvalidYearException;

public class Date implements java.io.Serializable{
        private static final long serialVersionUID = 6;
        
	protected int dia;
	protected int mes;
	protected int ano;

	public Date(){
		dia = 0;
		mes = 0;
		ano = 0;
	}

	public int getDia(){
		return dia;
	}

	final public void setDia(int dia) throws InvalidDayException{
		if(dia >= 1 && dia <= 31){
			this.dia = dia;
		} else {
			throw new InvalidDayException();
		}
	}

	public int getMes(){
		return mes;
	}

	final public void setMes(int mes) throws InvalidMonthException{
		if(mes >= 1 && mes <= 12){
			this.mes = mes;
		} else {
			throw new InvalidMonthException();
		}
	}

	public int getAno(){
		return ano;
	}

	final public void setAno(int ano) throws InvalidYearException{
		if(ano >= 1900 && ano <= 2023){
			this.ano = ano;
		} else {
			throw new InvalidYearException();
		}
	}

	final public void dataCad(){
		GregorianCalendar data = new GregorianCalendar();
		dia = data.get(data.DAY_OF_MONTH);
		mes = data.get(data.MONTH)+1;
		ano = data.get(data.YEAR);
	}
}