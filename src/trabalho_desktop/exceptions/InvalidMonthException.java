package trabalho_desktop.exceptions;

//Nome: Erick Elsas de Freitas - RA: 2347938

public class InvalidMonthException extends Exception{
	public String getError(){
		return "O mes deve ser um valor entre 1 e 12 [inclusos]!";
	}
}