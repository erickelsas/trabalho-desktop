/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_desktop.controller;

import dao.DoctorDAO;
import java.sql.Connection;
import trabalho_desktop.model.Doctor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import trabalho_desktop.exceptions.CreateException;
import trabalho_desktop.exceptions.DeleteException;
import trabalho_desktop.exceptions.EditException;
import trabalho_desktop.exceptions.RgLengthException;

/**
 *
 * @author erick
 */
public class DoctorController {
    Connection connection;
    DoctorDAO docDao;
    
    public DoctorController(){
        
    };
    
    public DoctorController(Connection connection){
        this.connection = connection;
        docDao = new DoctorDAO(connection);
    }
    public ArrayList<Doctor> createDoctor(Doctor doctor, ArrayList<Doctor> doctorList) throws CreateException{
        if(readDoctor(doctor, doctorList) == null){
            boolean flag = false;
            flag = docDao.createDoctor(doctor);
            if(flag){
                doctorList.add(doctor);            
                return doctorList;
            }
    }
            
        throw new CreateException();
}
    
    public ArrayList<Doctor> editDoctor(Doctor doctorEdited, ArrayList<Doctor> doctorList) throws RgLengthException, EditException{
        int index = 0;

        Doctor doctor; 
        doctor = searchByRg(doctorEdited.getRg(), doctorList);
        if(doctor != null){
            boolean flag = docDao.updateDoctor(doctorEdited, doctorEdited.getRg());
            
            if(flag){
                index = doctorList.indexOf(doctor);
                doctorList.set(index, doctorEdited);
            }
            
            return doctorList;
        }

        throw new EditException();
    }
    
    public ArrayList<Doctor> deleteDoctor(Doctor doctor, ArrayList<Doctor> doctorList) throws DeleteException{
        int i = 0;
        
        if(readDoctor(doctor, doctorList) != null){
            boolean flag = docDao.deleteDoctor(doctor.getRg());
            
            if(flag){
                doctorList.remove(doctor);
            }
            
            return doctorList;
        }
        
        throw new DeleteException();
    }
    
    public Doctor readDoctor(Doctor doctor, ArrayList<Doctor> doctorList){
        int i = 0;
        
        while(i < doctorList.size()){
            if(doctorList.get(i).buscaRg(doctor.getRg())){
                return doctorList.get(i);
            }
            i++;
        }
        
        return null;
    }
    
    public Doctor searchByRg(String rg, ArrayList<Doctor> doctorList) throws RgLengthException{
        Doctor doctor = new Doctor();
        doctor.setRg(rg);
        return readDoctor(doctor, doctorList);
    }
}
