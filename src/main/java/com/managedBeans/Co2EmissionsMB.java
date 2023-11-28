package com.managedBeans;

import java.io.Serializable;
import java.util.List;

import com.dao.ICo2Emissions;
import com.entities.Co2Emission;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("co2MB")
@SessionScoped
public class Co2EmissionsMB implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@EJB
	private ICo2Emissions metier ; 
	
	private Co2Emission co2Emission = new Co2Emission() ; 
	
	public String saveCo2EmissionData() {
		metier.addCo2Emisson(co2Emission);
		return "co2Emissions" ; 
	}
	
	public List<Co2Emission> getListCo2Emissions(){
		return metier.getCo2Emissions() ; 
	}
	
	public Co2Emission getCo2Emission() {
		return co2Emission;
	}
	
	public void setCo2Emission(Co2Emission co2Emission) {
		this.co2Emission = co2Emission;
	}
	
	public int getTotalData() {
		return metier.getCo2Emissions().size() ; 
	}

}
