/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import trabalho_desktop.exceptions.RgLengthException;
import trabalho_desktop.model.Person;

/**
 *
 * @author erick
 */
public class PersonDAO {
    public Person readPerson(ResultSet rsDados){
        Person p = new Person();
        
        try{
               p.setId(rsDados.getInt("id"));
               p.setName(rsDados.getString("name"));
               p.setRg(rsDados.getString("rg"));
               p.setRole(rsDados.getInt("role"));
        }catch(RgLengthException | SQLException rge){
            System.out.println(rge);
        }
        
        return p;
    }
}
