package com.acmecontracting.subcontractors;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@Service
public class SubcontractorService {
	//TODO: Validation??
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("subcontractors");
	
		
	
	public Subcontractor login(String emailAddress, String password) {
		EntityManager em = emf.createEntityManager();
		
		//TODO: Get a TypedQuery
		Query query = em.createNamedQuery("Subcontractor.login");
		query.setParameter("emailAddress", emailAddress);
		query.setParameter("password",password);
		List<Subcontractor> results = query.getResultList();
		if (results != null && results.size() == 1){
			Subcontractor entity = results.get(0);
			return entity;
		}else{
			return null;
		}
	}
	
}
