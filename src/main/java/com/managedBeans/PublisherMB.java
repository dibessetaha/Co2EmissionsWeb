package com.managedBeans;

import java.io.Serializable;

import com.dao.IPublisher;
import com.entities.PublisherApproval;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("publisherMB")
@SessionScoped
public class PublisherMB implements Serializable {

	
 
		private static final long serialVersionUID = 1L;
		@EJB
		private IPublisher metier ; 
		
		PublisherApproval publisher = new PublisherApproval() ; 
		
		public String login() {
			if(metier.login(publisher.getUsername(), publisher.getPassword())) {
				return "approve" ; 

			}else {
				return "login" ; 
			}
			
		}
		
		public PublisherApproval getPublisher() {
			return publisher;
		}
		


	

}
