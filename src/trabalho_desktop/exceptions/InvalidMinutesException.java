package trabalho_desktop.exceptions;

//Nome: Erick Elsas de Freitas - RA: 2347938

public class InvalidMinutesException extends Exception{
	public String getError(){
		return "Os minutos devem ser um valor entre 00 e 59 [inclusos]!";
	}
}