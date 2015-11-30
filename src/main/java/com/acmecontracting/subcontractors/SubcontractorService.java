package com.acmecontracting.subcontractors;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.acmecontracting.subcontractors.project.Project;

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
	
	public Subcontractor create(Subcontractor subcontractor) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(subcontractor);
		em.flush();
		em.getTransaction().commit();		
		return subcontractor;
	}
	
	public Subcontractor read(Integer id) {	
		EntityManager em = emf.createEntityManager();		
		Subcontractor subcontractor = em.find(Subcontractor.class, id);		
		return subcontractor;
	}

	
	public boolean delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		Subcontractor subcontractor = em.find(Subcontractor.class, id);
		if (subcontractor == null) {
			throw new IllegalArgumentException("Can't find shift with id " + id);			
		}else {
			em.getTransaction().begin();
			em.remove(subcontractor);
			em.flush();
			em.getTransaction().commit();
		}
		return true;
	}
	
}
