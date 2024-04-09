package trabalho_desktop.exceptions;

//Nome: Erick Elsas de Freitas - RA: 2347938

public class InvalidYearException extends Exception{
	public String getError(){
		return "O ano deve ser um valor entre 1900 e 2022 [inclusos]!";
	}
}