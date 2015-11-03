package com.acmecontracting.subcontractors.project;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

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
		TypedQuery<Project> query = em.createNamedQuery("Project.myProjects", Project.class);
		query.setParameter("id", id);
		List<Project> myProjects = query.getResultList();
		
		return myProjects;
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
