package com.acmecontracting.subcontractors.reports;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.acmecontracting.subcontractors.Subcontractor;

import model.Assignment;

@Service
public class ReportService {
	//TODO: Validation??
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("subcontractors");
	
	public Report create(Report report) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(report);
		em.flush();
		em.getTransaction().commit();		
		return report;
	}

	public Report read(Integer id) {	
		EntityManager em = emf.createEntityManager();		
		Report report = em.find(Report.class, id);		
		return report;
	}
	
	public List<Report> readReports(Integer subcontractorId, Integer projectId){
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Report.myReports");
		query.setParameter("projectId", projectId);
		query.setParameter("subcontractorId", subcontractorId);
		List<Report> myReports = query.getResultList();		
		return myReports;
	}
	
	
	public Report update(Report report) {
		EntityManager em = emf.createEntityManager();
		Report base = em.find(Report.class, report.getId());
		if (base == null) {
			//TODO: 404
			throw new IllegalArgumentException("Can't find report with id " + report.getId());
		}else {
			
			em.getTransaction().begin();
			base.setDescription(report.getDescription());
			base.setProject(report.getProject());
			em.persist(base);
			em.flush();
			Report result = em.find(Report.class, report.getId()); 
			em.getTransaction().commit();
			return result;
		}
	}
	
	public boolean delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		Report report = em.find(Report.class, id);
		if (report == null) {
			throw new IllegalArgumentException("Can't find report with id " + id);			
		}else {
			em.getTransaction().begin();
			em.remove(report);
			em.flush();
			em.getTransaction().commit();
		}
		return true;
	}
}
