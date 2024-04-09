/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_desktop.controller;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.PatientDAO;
import dao.PersonDAO;
import java.sql.*; 
import trabalho_desktop.model.Patient;
import trabalho_desktop.model.Doctor;
import trabalho_desktop.model.Appointment;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import trabalho_desktop.exceptions.BloodTypeInvalidException;
import trabalho_desktop.exceptions.RgLengthException;
import trabalho_desktop.model.Person;

/**
 *
 * @author erick
 */
public class DBController {
    private static ArrayList<Patient> patientList = new ArrayList<>();
    private static ArrayList<Doctor> doctorList = new ArrayList<>();
    private static ArrayList<Appointment> appointmentList = new ArrayList<>();
    
    private Connection connection = null;
    private ResultSet rsDados = null;
    
    public boolean acessaBD(){
        try{
            String usuario = "root";
            String senha = "root";
            
            Class.forName("com.mysql.jdbc.Driver");
            String urlConexao = "jdbc:mysql://localhost:3306/desktop_erick";
            connection = DriverManager.getConnection(
                    urlConexao,
                    usuario,
                    senha
            );
            connection.setAutoCommit(false);
        } catch(ClassNotFoundException erro){
            System.out.println("Falha ao carregar o driver JDBC" + erro);
            return false;
        } catch(SQLException erro){
            System.out.println("Falha na conexão, comando sql = " + erro);
            return false;
        }
        return true;
    }
    
    public void readDatabase(){
        try{
            PreparedStatement stat = connection.prepareStatement("SELECT * FROM patient");
            rsDados = stat.executeQuery();
            
            PersonDAO pDao = new PersonDAO();
            PatientDAO patDao = new PatientDAO();
            while(rsDados.next() && rsDados != null){
               Patient pat = new Patient();
               pat = patDao.readPatient(rsDados, pDao.readPerson(rsDados));
               
               if(pat != null){
                   patientList.add(pat);
               }
            }
            
            stat = connection.prepareStatement("SELECT * FROM doctor");
            rsDados = stat.executeQuery();
            DoctorDAO docDao = new DoctorDAO();
            
            while(rsDados.next() && rsDados != null){
               Doctor doc = new Doctor();
               doc = docDao.readDoctor(rsDados, pDao.readPerson(rsDados));
               
               if(doc != null){
                   doctorList.add(doc);
               }
            }
            
            stat = connection.prepareStatement("SELECT * FROM appointment");
            rsDados = stat.executeQuery();
            AppointmentDAO appDao = new AppointmentDAO();
            
            while(rsDados.next() && rsDados != null){
               Appointment app = new Appointment();
               app = appDao.readAppointment(rsDados);
               
               if(app != null && app.getDeleted() == 0){
                   appointmentList.add(app);
               }
            }
            
            rsDados.close();
        } catch(SQLException sqe){
            System.out.println(sqe);
        }
    }
    
    public void SairBD(){
        try{
            if(rsDados != null){
                rsDados.close();
                connection.close();
            }
        }catch(SQLException erro){
            System.out.println("Nao foi possivel a desconexão" + erro);
        }
    }
    
    public void setPatientList(ArrayList<Patient> patientList){
        this.patientList = patientList;
    }
    
    public ArrayList<Patient> getPatientList(){
        return this.patientList;
    }
    
    public void setDoctorList(ArrayList<Doctor> doctorList){
        this.doctorList = doctorList;
    }
    
    public ArrayList<Doctor> getDoctorList(){
        return this.doctorList;
    }
    
    public void setAppointmentList(ArrayList<Appointment> appointmentList){
        this.appointmentList = appointmentList;
    }
    
    public ArrayList<Appointment> getAppointmentList(){
        return this.appointmentList;
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    
    public ResultSet getResultSet(){
        return this.rsDados;
    }
}
