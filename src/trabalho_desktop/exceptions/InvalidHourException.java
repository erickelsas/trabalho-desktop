package trabalho_desktop.exceptions;

//Nome: Erick Elsas de Freitas - RA: 2347938

public class InvalidHourException extends Exception{
	public String getError(){
		return "A hora deve ser um valor entre 0 e 23 [inclusos]!";
	}
}