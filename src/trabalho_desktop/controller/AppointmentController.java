/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_desktop.controller;

import dao.AppointmentDAO;
import trabalho_desktop.model.Appointment;
import java.util.ArrayList;
import java.sql.*;
import trabalho_desktop.exceptions.CreateException;
import trabalho_desktop.exceptions.DeleteException;
import trabalho_desktop.exceptions.EditException;

/**
 *
 * @author erick
 */
public class AppointmentController {
    Connection connection;
    AppointmentDAO appDao;
    
    public AppointmentController(){
        
    };
    
    public AppointmentController(Connection connection){
        this.connection = connection;
        appDao = new AppointmentDAO(connection);
    }
    
    public ArrayList<Appointment> createAppointment(Appointment appointment, ArrayList<Appointment> appointmentList) throws CreateException{
        if(isDoctorFree(appointment, appointmentList) == null){
            boolean flag = appDao.createAppointment(appointment);
            
            if(flag){
                appointmentList.add(appointment);
            }
            
            return appointmentList;
        }
        
        throw new CreateException();
    }
    
    public ArrayList<Appointment> editAppointment(Appointment appointmentEdited, ArrayList<Appointment> appointmentList) throws EditException{
        int index = 0;
        int i = 0;
        
        Appointment appointment = null;
        while(i < appointmentList.size()){
            if(appointmentEdited.getPatientRg().equals(appointmentList.get(i).getPatientRg()) && appointmentEdited.getDoctorRg().equals(appointmentList.get(i).getDoctorRg())){
                appointment = appointmentList.get(i);
            }
            i++;
        }
        if(appointment != null){
            boolean flag = appDao.updateAppointment(appointmentEdited);
            if(flag){
                index = appointmentList.indexOf(appointment);
                appointmentList.set(index, appointmentEdited);     
            }
            return appointmentList;
        }
        
        throw new EditException();
    }
    
    public ArrayList<Appointment> deleteAppointment(Appointment appointment, ArrayList<Appointment> appointmentList) throws DeleteException{
        if(readAppointment(appointment) != null){
            boolean flag = appDao.deleteAppointment(appointment);
            
            if(flag){
                appointment.setDeleted(0);
                appointmentList.remove(appointment);
            }
            
            return appointmentList;
        }
        
        throw new DeleteException();
    }
    
    public Appointment isDoctorFree(Appointment appointment, ArrayList<Appointment> appointmentList){
        int i = 0;
        while(i < appointmentList.size()){
            if(appointment.getDoctorRg().equals(appointmentList.get(i).getDoctorRg()) && appointment.getAppointmentDate().comparaData(appointmentList.get(i).getAppointmentDate())){
                return appointmentList.get(i);
            }
            i++;
        }
        return null;
    }
    
    public Appointment readAppointment(Appointment appointment){
        Appointment app = appDao.searchAppointment(appointment.getPatientRg(), appointment.getDoctorRg());
        return app;
    }
    
        public Appointment readAppointment(String rgPat, String rgDoc){
            Appointment app = appDao.searchAppointment(rgPat, rgDoc);
        return app;
    }
}
