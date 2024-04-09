package trabalho_desktop.exceptions;

//Nome: Erick Elsas de Freitas - RA: 2347938

public class InvalidDayException extends Exception{
	public String getError(){
		return "O dia deve ser um valor entre 1 e 31 [inclusos]!";
	}
}