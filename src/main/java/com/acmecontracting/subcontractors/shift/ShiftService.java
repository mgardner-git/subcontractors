package com.acmecontracting.subcontractors.shift;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.acmecontracting.subcontractors.project.Project;

@Service
public class ShiftService {
	//TODO: Validation??
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("subcontractors");
	Date now;
	
	
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

	public List<Shift> readByProjectAndSubcontractor(Integer projectId, Integer subcontractorId) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Shift.readByShiftAndSubcontractor");
		query.setParameter("projectId", projectId);
		query.setParameter("subcontractorId", subcontractorId);
		List<Shift> myShifts = query.getResultList();
		return myShifts;
	}
	
	public Shift update(Shift shift) {
		EntityManager em = emf.createEntityManager();
		Shift base = em.find(Shift.class, shift.getId());
		if (base == null) {
			//TODO: 404
			throw new IllegalArgumentException("Can't find shift with id " + shift.getId());
		}else {
			
			em.getTransaction().begin();
			base.setDescription(shift.getDescription());
			base.setBegin(shift.getBegin());
			base.setEnd(shift.getEnd());
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
