
//My whatssap : +212681260136
package com.managedBeans;

import java.io.Serializable;
import java.util.List;

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
	private String country ; 
	private List<Co2Emission> listCo2Emissions ;
	private int totalData ; 
	
	
	
	public String saveCo2EmissionData() {
		metier.addCo2Emisson(co2Emission);
		return "co2Emissions" ; 
	} 
	
	public Co2Emission getCo2Emission() {
		return co2Emission;
	}
	
	public void setCo2Emission(Co2Emission co2Emission) {
		this.co2Emission = co2Emission;
	}
	
	public List<Co2Emission> getListCo2EmissionsPerScientist(DataScientist ds){
		return metier.getCo2EmissionsPerDs(ds) ; 
	}
	
	
	
	public List<Co2Emission> getListCo2Emissions() { 
//		publishDS();
		if(country != null) {
			setTotalData(metier.getCo2EmissionsByCountry(country).size());
			listCo2Emissions = filterDataByCountry();
			
		}else {
			setTotalData(metier.getCo2Emissions().size());
			listCo2Emissions = metier.getCo2Emissions() ; 
		}
//		publishDS();
		return listCo2Emissions;
	}
	
	
	public int getTotalData() {
		return totalData ;  
	}
	
	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}
	
	public List<String> getListCountries(){ 
		return metier.getAllCountries() ; 
	}

	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public List<Co2Emission> filterDataByCountry() {
		return metier.getCo2EmissionsByCountry(this.country) ;  
		
	}
}
