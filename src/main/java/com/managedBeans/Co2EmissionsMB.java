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
	private static boolean publish = false ; 

	@EJB
	private ICo2Emissions metier ; 
	
	private Co2Emission co2Emission = new Co2Emission() ; 
	private String country ; 
	private List<Co2Emission> listCo2Emissions  ;
	private List<Co2Emission> listApprouvedCo2Emissions ;
	private int totalData ; 
	
	public void initDb() { 
	 if(!publish) {
		 publish=true ; 
		   Co2DataSet co2DataSet = new Co2DataSet();
		    List<Co2Emission> co2Emissions = co2DataSet.getCo2DataSet();

		    // Set an appropriate batch size based on your database and performance testing
		    int batchSize = 100;
		    for (int i = 0; i < co2Emissions.size()/2; i += batchSize) {
		        int endIndex = Math.min(i + batchSize, co2Emissions.size());
		        List<Co2Emission> batch = co2Emissions.subList(i, endIndex);
		        metier.addCo2EmissonBatch(batch);
		    }
			
	 }else {
		 System.out.println("Already initialized");
	 }
	}
	
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
		if(country != null) {
			setTotalData(metier.getCo2EmissionsByCountry(country).size());
			listCo2Emissions = filterDataByCountry();
			
		}else {
			setTotalData(metier.getCo2Emissions().size());
			listCo2Emissions = metier.getCo2Emissions() ; 
		}
		return listCo2Emissions;
	}
	
	
	public List<Co2Emission> getListApprouvedCo2Emissions() { 
		listApprouvedCo2Emissions = metier.getApprouvedData() ; 
		return listApprouvedCo2Emissions ; 
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
	
	public void approuve(Long id) {
		metier.approuveCo2Emission(id);
	}
	
	public void disApprouve(Long id) {
		metier.disApprouveCo2Emission(id) ; 
	}
}
