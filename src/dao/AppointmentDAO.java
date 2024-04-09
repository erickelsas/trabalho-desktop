/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import trabalho_desktop.model.Appointment;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabalho_desktop.exceptions.InvalidDayException;
import trabalho_desktop.exceptions.InvalidHourException;
import trabalho_desktop.exceptions.InvalidMinutesException;
import trabalho_desktop.exceptions.InvalidMonthException;
import trabalho_desktop.exceptions.InvalidYearException;
import trabalho_desktop.exceptions.RgLengthException;
import trabalho_desktop.model.DateAndHour;
import trabalho_desktop.model.Doctor;
import trabalho_desktop.model.Patient;

/**
 *
 * @author erick
 */
public class AppointmentDAO {
    PatientDAO patDao = new PatientDAO();
    DoctorDAO docDao = new DoctorDAO();
    Connection connection;
    
    public AppointmentDAO(){
        
    }
    
    public AppointmentDAO(Connection connection){
        this.connection = connection;
    }
    
    public Appointment readAppointment(ResultSet rsDados){
        Appointment app = new Appointment();

        try{
            app.setId(rsDados.getInt("id"));
            app.setPatientRg(rsDados.getString("patient_rg"));
            app.setDoctorRg(rsDados.getString("doctor_rg"));
            app.setRole(3);
            app.getAppointmentDate().setAll(rsDados.getString("appointmentDate"));
            app.setDeleted(rsDados.getInt("deleted"));
                
            return app;

        } catch(SQLException | RgLengthException | InvalidDayException | InvalidMonthException | InvalidYearException | InvalidMinutesException | InvalidHourException erro){
            System.out.println(erro);
        }
        
        return null;
    }
    
    public boolean createAppointment(Appointment app){
        String query = "INSERT INTO appointment (Patient_id, Patient_rg, Doctor_id, Doctor_rg, role, appointmentDate) VALUES (?, ?, ?, ?, 3, ?";
        
        try{
            PreparedStatement stat = connection.prepareStatement(query);
            Doctor doc = docDao.selectDoctor(app.getDoctorRg());
            Patient pat = patDao.selectPatient(app.getPatientRg());
            
            stat.setInt(1, pat.getId());
            stat.setString(2, pat.getRg());
            stat.setInt(3, doc.getId());
            stat.setString(4, doc.getRg());
            stat.setString(5, app.getAppointmentDate().getAll());
            
            stat.execute();
            
            connection.commit();
            
            return true;
        }catch(SQLException sqle){
            System.out.println(sqle);
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public Appointment searchAppointment(String rgPat, String rgDoc){
        String query = "SELECT * FROM appointment WHERE Patient_rg = ? AND Doctor_rg = ?";
        
        try{
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, rgPat);
            stat.setString(2, rgDoc);
            ResultSet rsDados = stat.executeQuery();
            rsDados.next();
            
            Appointment app = new Appointment();
            
            app.setId(rsDados.getInt("id"));
            app.setPatientRg(rsDados.getString("patient_rg"));
            app.setDoctorRg(rsDados.getString("doctor_rg"));
            app.setRole(3);
            app.getAppointmentDate().setAll(rsDados.getString("appointmentDate"));
            app.setDeleted(rsDados.getInt("deleted"));
            
            return app;
        } catch(SQLException sqle){
            System.out.println(sqle + "AppointmentDAO");
        } catch (InvalidDayException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMonthException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidYearException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidHourException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMinutesException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RgLengthException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public boolean updateAppointment(Appointment app){
        String query = "UPDATE appointment SET appointmentDate = ? WHERE id = ?";
        
        try{
            PreparedStatement stat = connection.prepareStatement(query);
            stat.setString(1, app.getAppointmentDate().getAll());
            app = this.searchAppointment(app.getPatientRg(), app.getDoctorRg());
            stat.setInt(2, app.getId());
            stat.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException sqle){
            System.out.println(sqle);
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean deleteAppointment(Appointment app){
        String query = "UPDATE appointment SET deleted = 1 WHERE id = ?";
        
        try{
            PreparedStatement stat = connection.prepareStatement(query);
            app = searchAppointment(app.getPatientRg(), app.getDoctorRg());
            stat.setInt(1, app.getId());
            stat.executeUpdate();
            connection.commit();
            
            return true;
        }catch(SQLException sqle){
            System.out.println(sqle);
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        return false;
    }
}
