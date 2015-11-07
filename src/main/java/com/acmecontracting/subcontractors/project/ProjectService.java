package com.acmecontracting.subcontractors.project;

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
public class ProjectService {
	//TODO: Validation??
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("subcontractors");
	
	public Project create(Project project) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(project);
		em.flush();
		em.getTransaction().commit();		
		return project;
	}

	public Project read(Integer id) {	
		EntityManager em = emf.createEntityManager();		
		Project project = em.find(Project.class, id);		
		return project;
	}
	
	public List<Project> readProjects(Integer id){
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Project.myProjects");
		query.setParameter("id", id);
		List<Project> myProjects = query.getResultList();
		
		return myProjects;
	}
	
	public ProjectAnalysisDTO analyze() {
		EntityManager em = emf.createEntityManager();
		ProjectAnalysisDTO analysis = new ProjectAnalysisDTO();
		TypedQuery<Project> projectQuery = em.createNamedQuery("Project.findAll", Project.class);		
		analysis.setProjects(projectQuery.getResultList());
		
		TypedQuery<Assignment> assignmentQuery = em.createNamedQuery("Assignment.findAll", Assignment.class);
		analysis.setAssignments(assignmentQuery.getResultList());
		
		TypedQuery<Subcontractor> subcontractorQuery = em.createNamedQuery("Subcontractor.findAll", Subcontractor.class);
		analysis.setSubcontractors(subcontractorQuery.getResultList());
		
		return analysis;
	}
	
	public Project update(Project project) {
		EntityManager em = emf.createEntityManager();
		Project base = em.find(Project.class, project.getId());
		if (base == null) {
			//TODO: 404
			throw new IllegalArgumentException("Can't find project with id " + project.getId());
		}else {
			
			em.getTransaction().begin();
			base.setDescription(project.getDescription());
			base.setName(project.getName());
			em.persist(base);
			em.flush();
			Project result = em.find(Project.class, project.getId()); 
			em.getTransaction().commit();
			return result;
		}
	}
	
	public boolean delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		Project project = em.find(Project.class, id);
		if (project == null) {
			throw new IllegalArgumentException("Can't find project with id " + id);			
		}else {
			em.getTransaction().begin();
			em.remove(project);
			em.flush();
			em.getTransaction().commit();
		}
		return true;
	}
}
