/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_desktop.controller;

import dao.PatientDAO;
import java.sql.*;
import trabalho_desktop.model.Patient;
import java.util.ArrayList;
import trabalho_desktop.exceptions.CreateException;
import trabalho_desktop.exceptions.DeleteException;
import trabalho_desktop.exceptions.EditException;
import trabalho_desktop.exceptions.RgLengthException;

/**
 *
 * @author erick
 */
public class PatientController {
    Connection connection;
    PatientDAO patDao;
    
    public PatientController(){
        
    }
    
    public PatientController(Connection connection){
        this.connection = connection;
        
        patDao = new PatientDAO(connection);
    }
    public ArrayList<Patient> createPatient(Patient patient, ArrayList<Patient> patientList) throws CreateException{
        if(readPatient(patient, patientList) == null){
            boolean flag = false;
            flag = patDao.createPatient(patient);
            if(flag){
                patientList.add(patient);            
                return patientList;
            }
        } 
        
        throw new CreateException();
    }
    
    public ArrayList<Patient> editPatient(Patient patientEdited, ArrayList<Patient> patientList) throws RgLengthException, EditException{
        int index = 0;

        Patient patient; 
        patient = searchByRg(patientEdited.getRg(), patientList);
        if(patient != null){
            boolean flag = patDao.updatePatient(patientEdited, patientEdited.getRg());
            
            if(flag){
                index = patientList.indexOf(patient);
                patientList.set(index, patientEdited);  
            }

            return patientList;
        }

        throw new EditException();
    }
    
    public ArrayList<Patient> deletePatient(Patient patient, ArrayList<Patient> patientList) throws DeleteException{
        if(readPatient(patient, patientList) != null){
            boolean flag = patDao.deletePatient(patient.getRg());
            
            if(flag){
                patientList.remove(patient);
            }
            return patientList;
        }
        
        throw new DeleteException();
    }
    
    public Patient readPatient(Patient patient, ArrayList<Patient> patientList){
        int i = 0;
        while(i < patientList.size()){
            if(patientList.get(i).buscaRg(patient.getRg())){
                return patientList.get(i);
            }
            i++;
        }
        
        return null;
    }
    
    public Patient searchByRg(String rg, ArrayList<Patient> patientList) throws RgLengthException{
        Patient patient = new Patient();
        patient.setRg(rg);
        return readPatient(patient, patientList);
    }
}
