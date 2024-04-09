/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import trabalho_desktop.exceptions.CrmLengthException;
import trabalho_desktop.exceptions.RgLengthException;
import trabalho_desktop.exceptions.StateException;
import trabalho_desktop.model.Doctor;
import trabalho_desktop.model.Person;

/**
 *
 * @author erick
 */
public class DoctorDAO {
    Connection connection;
    
    public DoctorDAO(){
    }
    
    public DoctorDAO(Connection connection){
        this.connection = connection;
    }
    
    public Doctor readDoctor(ResultSet rsDados){
        Doctor doc = new Doctor();
        try{
               doc.setId(rsDados.getInt("id"));
               doc.setName(rsDados.getString("name"));
               doc.setRg(rsDados.getString("rg"));
               doc.setRole(rsDados.getInt("role"));
               doc.setEspec(rsDados.getString("espec"));
               doc.getCrm().setNum(rsDados.getInt("crmNum"));
               doc.getCrm().setEstado(rsDados.getString("crmState"));
               
               return doc;
        }catch(RgLengthException | SQLException | StateException | CrmLengthException error){
            System.out.println(error);
        }
        
        return null;
    }
    
    public Doctor readDoctor(ResultSet rsDados, Person p){
        Doctor doc = new Doctor();
        try{
               doc.setId(p.getId());
               doc.setName(p.getName());
               doc.setRg(p.getRg());
               doc.setRole(2);
               doc.setEspec(rsDados.getString("espec"));
               doc.getCrm().setNum(rsDados.getInt("crmNum"));
               doc.getCrm().setEstado(rsDados.getString("crmState"));
               
               return doc;
        }catch(RgLengthException | SQLException | StateException | CrmLengthException error){
            System.out.println(error);
        }
        
        return null;
    }

    public boolean createDoctor(Doctor doc){
         String query = "INSERT INTO doctor(name, role, rg, espec, crmNumber, crmState) VALUES(?, 2, ?, ?, ?, ?);";
         
         try{
             PreparedStatement stat = connection.prepareStatement(query);
             stat.setString(1, doc.getName());
             stat.setString(2, doc.getRg());
             stat.setString(3, doc.getEspec());
             stat.setInt(4, doc.getCrm().getNum());
             stat.setString(5, doc.getCrm().getEstado());
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
    
    public boolean updateDoctor(Doctor newDoc, String rg){
        String query = "UPDATE doctor SET name = ?, espec = ?, crmNum = ?, crmState = ? WHERE rg = ?";
        
        try{
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, newDoc.getName());
            stat.setString(2, newDoc.getEspec());
            stat.setInt(3, newDoc.getCrm().getNum());
            stat.setString(4, newDoc.getCrm().getEstado());
            stat.setString(5, newDoc.getRg());
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
    
    public boolean deleteDoctor(String rg){
        String query = "DELETE FROM doctor WHERE rg = ?";
        
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
    
    public Doctor selectDoctor(String rg){
        String query = "SELECT * FROM doctor WHERE rg = ?";
        Doctor doc = new Doctor();
        
        try{
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, rg);
            
            ResultSet rsDados = stat.executeQuery();
            rsDados.next();
            
            doc.setId(rsDados.getInt("id"));
            doc.setName(rsDados.getString("name"));
            doc.setRg(rsDados.getString("rg"));
            doc.setRole(2);
            doc.setEspec(rsDados.getString("espec"));
            doc.getCrm().setNum(rsDados.getInt("crmNum"));
            doc.getCrm().setEstado(rsDados.getString("crmState"));
            
            rsDados.close();
            return doc;
        }catch(SQLException | StateException | CrmLengthException | RgLengthException sqle){
            System.out.println(sqle);
        }
        
        return null;
    }
}
