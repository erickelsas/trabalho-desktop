package trabalho_desktop.model;

//Nome: Erick Elsas de Freitas - RA: 2347938

import trabalho_desktop.exceptions.InvalidDayException;
import trabalho_desktop.exceptions.InvalidHourException;
import trabalho_desktop.exceptions.InvalidMinutesException;
import trabalho_desktop.exceptions.InvalidMonthException;
import trabalho_desktop.exceptions.InvalidYearException;
import trabalho_desktop.model.Date;


public class DateAndHour extends Date implements java.io.Serializable{
        private static final long serialVersionUID = 5;
        
	private int hora;
	private int minutos;

	public DateAndHour(){
                super.dia = 0;
                super.mes = 0;
                super.ano = 0;
		hora = 0;
		minutos = 0;
	}

	public int getHora(){
		return hora;
	}	

	final public void setHora(int hora) throws InvalidHourException{
		if(hora >= 00 && hora <= 23){
			this.hora = hora;
		} else {
			throw new InvalidHourException();
		}
	}

	final public int getMinutos(){
		return minutos;
	}

	final public void setMinutos(int minutos) throws InvalidMinutesException{
		if(minutos >= 00 && minutos <= 59){
			this.minutos = minutos;
		} else {
			throw new InvalidMinutesException();
		}
	}
        
        final public void setAll(String date) throws InvalidDayException, InvalidMonthException, InvalidYearException, InvalidHourException, InvalidMinutesException {
            String[] dataHora = new String[2];
            String[] data = new String[3];
            String[] hora = new String[2];
            
            dataHora = date.split(" ");
            data = dataHora[0].split("/");
            hora = dataHora[1].split(":");
            
            this.setDia(Integer.parseInt(data[0]));
            this.setMes(Integer.parseInt(data[1]));
            this.setAno(Integer.parseInt(data[2]));
            this.setHora(Integer.parseInt(hora[0]));
            this.setMinutos(Integer.parseInt(hora[1]));
        }
        
        final public String getAll(){
            return (this.getDia() + "/" + this.getMes() + "/" + this.getAno() + " " + this.getHora() + ":" + this.getMinutos());
        }
        
        public boolean comparaData(DateAndHour date){
            return (super.dia == date.getDia() && super.mes == date.getMes() && super.ano == date.getAno() && this.hora == date.getHora() && this.minutos == date.getMinutos());
    }
}