/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_desktop.model;

import trabalho_desktop.exceptions.BloodTypeInvalidException;

/**
 *
 * @author erick
 */
public class Patient extends Person implements java.io.Serializable{
    private static final long serialVersionUID = 1;
    
    private int sus;
    private String bloodType;
    
    public Patient(){
        super.id = 0;
        super.name = "";
        super.role = 1;
        super.rg = "";
        this.sus = 0;
        this.bloodType = "";
    }
    
    public Patient(Person p){
        super.id = p.id;
        super.name = p.name;
        super.role = 1;
        super.rg = p.rg;
        this.sus = 0;
        this.bloodType = "";
    }
    
    public Patient(int id, String name, String rg, int sus, String bloodType){
        super.id = id;
        super.name = name;
        super.role = 1;
        super.rg = rg;
        this.sus = sus;
        this.bloodType = bloodType;
    }
    
    final public void setSus(int sus){
        this.sus = sus;
    }
    
    final public int getSus(){
        return this.sus;
    }
    
    final public void setBloodType(String bloodType) throws BloodTypeInvalidException{
        bloodType = bloodType.toUpperCase();
      	if(bloodType.length() == 2){
			if(Character.compare(bloodType.charAt(0), 'A') == 0 || Character.compare(bloodType.charAt(0), 'B')  == 0 || Character.compare(bloodType.charAt(0), 'O') == 0){
				if(Character.compare(bloodType.charAt(1), '+') == 0 || Character.compare(bloodType.charAt(1), '-') == 0){
					this.bloodType = bloodType;
				}
			}
		    else{
		        throw new BloodTypeInvalidException();
		    }
		} else if(bloodType.length() == 3){
			if(Character.compare(bloodType.charAt(0), 'A') == 0 || Character.compare(bloodType.charAt(0), 'B') == 0 || Character.compare(bloodType.charAt(0), 'O') == 0){
				if(Character.compare(bloodType.charAt(1), 'B') == 0){
					if(Character.compare(bloodType.charAt(2), '+') == 0 || Character.compare(bloodType.charAt(2), '-') == 0){
						this.bloodType = bloodType;
				    }
			    }
		    } else{
		        throw new BloodTypeInvalidException();
		    }
		}else {
			throw new BloodTypeInvalidException();
		}
    }
    
    final public String getBloodType(){
        return bloodType;
    }
}
