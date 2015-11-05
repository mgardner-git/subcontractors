package com.acmecontracting.subcontractors.shift;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.springframework.stereotype.Service;

import com.acmecontracting.subcontractors.project.Project;

@Service
public class ShiftService {
	//TODO: Validation??
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("subcontractors");
	Date now;
	
	public Shift read(Integer id) {	
		EntityManager em = emf.createEntityManager();		
		Shift shift = em.find(Shift.class, id);		
		return shift;
	}
	
	public Shift create(Shift shift) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(shift);
		em.flush();
		em.getTransaction().commit();		
		return shift;
	}

	
}
