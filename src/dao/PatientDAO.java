/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.*;
import trabalho_desktop.controller.DBController;
import trabalho_desktop.exceptions.BloodTypeInvalidException;
import trabalho_desktop.exceptions.CrmLengthException;
import trabalho_desktop.exceptions.RgLengthException;
import trabalho_desktop.exceptions.StateException;
import trabalho_desktop.model.Patient;
import trabalho_desktop.model.Person;

/**
 *
 * @author erick
 */
public class PatientDAO {
    Connection connection;
    
    public PatientDAO(){
    }
    
    public PatientDAO(Connection connection){
        this.connection = connection;
    }
    
    public Patient readPatient(ResultSet rsDados){
        Patient pat = new Patient();
        try{
               pat.setId(rsDados.getInt("id"));
               pat.setName(rsDados.getString("name"));
               pat.setRg(rsDados.getString("rg"));
               pat.setRole(rsDados.getInt("role"));
               pat.setSus(rsDados.getInt("sus"));
               pat.setBloodType(rsDados.getString("bloodType"));
               
               return pat;
        }catch(RgLengthException | SQLException | BloodTypeInvalidException rge){
            System.out.println(rge);
        }
        
        return null;
    }
    
    public Patient readPatient(ResultSet rsDados, Person p){
        Patient pat = new Patient();
        try{
               pat.setId(p.getId());
               pat.setName(p.getName());
               pat.setRg(p.getRg());
               pat.setRole(1);
               pat.setSus(rsDados.getInt("sus"));
               pat.setBloodType(rsDados.getString("bloodType"));
               
               return pat;
        }catch(RgLengthException | SQLException | BloodTypeInvalidException rge){
            System.out.println(rge);
        }
        
        return null;
    }
    
    public boolean createPatient(Patient pat){
         String query = "INSERT INTO patient(name, role, rg, sus, bloodType) VALUES(?, 1, ?, ?, ?);";
         
         try{
             PreparedStatement stat = connection.prepareStatement(query);
             stat.setString(1, pat.getName());
             stat.setString(2, pat.getRg());
             stat.setInt(3, pat.getSus());
             stat.setString(4, pat.getBloodType());
             stat.execute();
             connection.commit();
             return true;
         } catch(SQLException sqle){
             System.out.println(sqle);
             try{
                 connection.rollback();
             }catch(SQLException e){
                 e.printStackTrace();
             }
         }
         
         return false;
    }
    
    public boolean updatePatient(Patient newPat, String rg){
        String query = "UPDATE patient SET name = ?, sus = ?, bloodType = ? WHERE rg = ?";
        
        try{
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, newPat.getName());
            stat.setInt(2, newPat.getSus());
            stat.setString(3, newPat.getBloodType());
            stat.setString(4, newPat.getRg());
            stat.executeUpdate();
            connection.commit();
            
            return true;
        } catch(SQLException sqe){
            System.out.println(sqe);
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean deletePatient(String rg){
        String query = "DELETE FROM patient WHERE rg = ?";
        
        try{
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, rg);
            stat.execute();
            connection.commit();
            
            return true;
        } catch(SQLException sqle){
            System.out.println(sqle);
            try{
                connection.rollback();
            }catch(SQLException erro){
                erro.printStackTrace();
            }
        }
        
        return false;
    }
    
    public Patient selectPatient(String rg){
        String query = "SELECT * FROM patient WHERE rg = ?";
        Patient pat = new Patient();
        
        try{
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, rg);
            
            ResultSet rsDados = stat.executeQuery();
            rsDados.next();
            
            pat.setId(rsDados.getInt("id"));
            pat.setName(rsDados.getString("name"));
            pat.setRg(rsDados.getString("rg"));
            pat.setRole(1);
            pat.setSus(rsDados.getInt("sus"));
            pat.setBloodType(rsDados.getString("bloodType"));
            
            rsDados.close();
            return pat;
        }catch(SQLException | RgLengthException | BloodTypeInvalidException sqle){
            System.out.println(sqle);
        }
        
        return null;
    }
}
