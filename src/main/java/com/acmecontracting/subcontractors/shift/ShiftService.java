package com.acmecontracting.subcontractors.shift;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import com.acmecontracting.subcontractors.Subcontractor;
import com.acmecontracting.subcontractors.project.Project;

@Service
public class ShiftService {
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("subcontractors");
	
	public Shift create(Shift shift) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(shift);
		em.flush();
		em.getTransaction().commit();		
		return shift;
	}

	public Shift read(Integer id) {	
		EntityManager em = emf.createEntityManager();		
		Shift shift = em.find(Shift.class, id);		
		return shift;
	}
	/*
	public List<Shift> readByProjectAndSubcontractor(Project project, Subcontractor subcontractor) {
		
	}
	
	public List<Shift> readByProjectAndSubContractorAndTime(Project project, Subcontractor subcontractor, Long start, Long end) {
		
	}*/

	public Shift update(Shift shift) {
		EntityManager em = emf.createEntityManager();
		Shift base = em.find(Shift.class, shift.getId());
		if (base == null) {
			//TODO: 404
			throw new IllegalArgumentException("Can't find shift with id " + shift.getId());
		}else {			
			em.getTransaction().begin();
			base.setBegin(shift.getBegin());
			base.setEnd(shift.getEnd());
			base.setDescription(shift.getDescription());
			em.persist(base);
			em.flush();
			Shift result = em.find(Shift.class, shift.getId()); 
			em.getTransaction().commit();
			return result;
		}
	}
	
	public boolean delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		Shift shift = em.find(Shift.class, id);
		if (shift == null) {
			throw new IllegalArgumentException("Can't find shift with id " + id);			
		}else {
			em.getTransaction().begin();
			em.remove(shift);
			em.flush();
			em.getTransaction().commit();
		}
		return true;
	}
}
