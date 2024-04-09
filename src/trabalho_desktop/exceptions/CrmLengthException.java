/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_desktop.exceptions;

/**
 *
 * @author erick
 */
public class CrmLengthException extends Exception{
    public String getError(){
        return "CRM deve ser composto por 6 números!";
    }
}
