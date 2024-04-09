/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho_desktop.model;

import trabalho_desktop.model.Crm;

/**
 *
 * @author erick
 */
public class Doctor extends Person implements java.io.Serializable{
        private static final long serialVersionUID = 2;
    
    	private Crm crm;
	private String espec;
        
        public Doctor(){
            super.id = 0;
            super.name = "";
            super.role = 2;
            super.rg = "";
            this.crm = new Crm();
            this.espec = "";
        }
        
        public Doctor(Person p){
            super.id = p.id;
            super.name = p.name;
            super.role = 1;
            super.rg = p.rg;
            this.espec = "";
            this.crm = new Crm();
    }
        
        public Doctor(int id, String name, String rg, Crm crm, String espec){
            super.id = id;
            super.name = name;
            super.role = 2;
            super.rg = rg;
            this.crm = crm;
            this.espec = espec;
        }
        
       final public Crm getCrm(){
		return crm;
	}
	
	final public void setCrm(Crm crm){
		this.crm = crm;
	}

	final public String getEspec(){
		return espec;
	}

	final public void setEspec(String espec){
		this.espec = espec;
	}
}
