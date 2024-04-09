/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_desktop.model;

import trabalho_desktop.exceptions.RgLengthException;

/**
 *
 * @author erick
 */
public class Appointment implements java.io.Serializable {
    private static final long serialVersionUID = 3;
    
    private int id;
    private String patientRg;
    private String doctorRg;
    private DateAndHour appointmentDate;
    private int role;
    private int deleted;
        
        public Appointment(){
            this.patientRg = "";
            this.doctorRg = "";
            this.id = 0;
            this.appointmentDate = new DateAndHour();
            this.role = 3;
            this.deleted = 0;
        }
        
        public Appointment(int id, String patientRg, String doctorRg, DateAndHour appointmentDate){
            this.id = id;
            this.patientRg = patientRg;
            this.doctorRg = doctorRg;
            this.appointmentDate = appointmentDate;
            this.role = 3;
            this.deleted = 0;
        }
        
        public Appointment(int id, String patientRg, String doctorRg, DateAndHour appointmentDate,int deleted){
            this.id = id;
            this.patientRg = patientRg;
            this.doctorRg = doctorRg;
            this.appointmentDate = appointmentDate;
            this.role = 3;
            this.deleted = deleted;
        }
        
        final public void setId(int id){
            this.id = id;
        }
        
        final public int getId(){
            return this.id;
        }
        
        final public void setPatientRg(String patientRg) throws RgLengthException{
            if(patientRg.length() == 9){
		this.patientRg = patientRg;
            } else {
                    throw new RgLengthException();
            }
        }
        
        final public String getPatientRg(){
            return this.patientRg;
        }
        
        final public void setDoctorRg(String doctorRg) throws RgLengthException{
            if(doctorRg.length() == 9){
		this.doctorRg = doctorRg;
            } else {
                    throw new RgLengthException();
            }
        }
        
        final public String getDoctorRg(){
            return this.doctorRg;
        }

        final public void setRole(int role){
            this.role = role;
        }
        
        final public int getRole(){
            return this.role;
        }
        
        final public void setAppointmentDate(DateAndHour appointmentDate){
            this.appointmentDate = appointmentDate;
        }
        
        final public DateAndHour getAppointmentDate(){
            return this.appointmentDate;
        }
        
        final public int getDeleted(){
            return this.deleted;
        }
        
        final public void setDeleted(int deleted){
            this.deleted = deleted;
        }
        
}
