package com.managedBeans;

import java.io.Serializable;

import com.dao.IDataScientist;
import com.entities.DataScientist;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("dataScientistMB")
@SessionScoped
public class DataScientistMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private IDataScientist metier ; 
	
	DataScientist dataScientist = new DataScientist() ; 
	
	public String login() {
		if(metier.login(dataScientist.getUsername(), dataScientist.getPassword())) {
			return "co2Emissions" ; 

		}else {
			return "login" ; 
		}
		
	}
	
	public DataScientist getDataScientist() {
		return dataScientist;
	}
	


}
