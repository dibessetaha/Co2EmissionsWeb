package com.managedBeans;

import java.io.Serializable;
import java.util.List;

import com.beans.Co2DataFromExcel;
import com.dao.ICo2Emissions;
import com.entities.Co2Emission;
import com.entities.DataScientist;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("co2MB")
@SessionScoped
public class Co2EmissionsMB implements Serializable {
	

	private static final long serialVersionUID = 1L;
	public static boolean publish = false ; 

	@EJB
	private ICo2Emissions metier ; 
	
	private Co2Emission co2Emission = new Co2Emission() ; 
	
	public String saveCo2EmissionData() {
		metier.addCo2Emisson(co2Emission);
		return "co2Emissions" ; 
	} 
	
	public List<Co2Emission> getListCo2Emissions(){
//		publishDS();
		return metier.getCo2Emissions() ; 
	}
	
	public List<Co2Emission> getListCo2EmissionsPerScientist(DataScientist ds){
		return metier.getCo2EmissionsPerDs(ds) ; 
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
	
	private void publishDS() {
		Co2DataFromExcel ds = new Co2DataFromExcel() ; 
		ds.test();  
	}

}
