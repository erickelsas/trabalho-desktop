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
public class Person implements java.io.Serializable{
    private static final long serialVersionUID = 0;
    
    protected int id;
    protected String name;
    protected int role;
    protected String rg;
    
    public Person(){
        this.id = 0;
        this.name = "";
        this.role = 0;
        this.rg = "";
    }
    
    public Person(int id, String name, int role){
        this.id = id;
        this.name = name;
        this.role = role;
        this.rg = rg;
    }
    
    final public void setId(int id){
        this.id = id;
    }
    
    final public int getId(){
        return this.id;
    }
    
    final public void setName(String name){
        this.name = name;
    }
    
    final public String getName(){
        return this.name;
    }
    
    final public void setRole(int role){
        this.role = role;
    }
    
    final public int getRole(){
        return this.role;
    }
    
    final public String getRg(){
		return rg;
	}

    final public void setRg(String rg) throws RgLengthException{
	if(rg.length() == 9){
		this.rg = rg;
	} else {
		throw new RgLengthException();
	}
    }
    
    final public boolean buscaRg(String rg){
	if(rg.matches(getRg())){
		return true;
	} else {
		return false;
	}
    }
}
