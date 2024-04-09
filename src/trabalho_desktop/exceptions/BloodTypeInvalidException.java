/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_desktop.exceptions;

/**
 *
 * @author erick
 */
public class BloodTypeInvalidException extends Exception {
    public String getError(){
        return "O tipo sanguineo digitado eh invalido!";
    }
}
