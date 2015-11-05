package com.acmecontracting.subcontractors.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import com.acmecontracting.subcontractors.project.Project;
import com.acmecontracting.subcontractors.project.ProjectService;

import junit.framework.TestCase;


public class ProjectServiceTest extends TestCase{

	ProjectService projectService;
	
	Integer deleteMeId;

	public void setUp() {
		projectService = new ProjectService();
	}
	
	@After    
    public void after()
    {
        if (deleteMeId != null) {
            projectService.delete(deleteMeId);//make sure our test cases are marked deleted
        }
    }
	@Before
    public void before()
    {
        deleteMeId = null;
    }
    
	@Test
	public void testCreate() {
		Project project = new Project();
		project.setDescription("Test1");
		project.setName("some name");
		Project result = projectService.create(project);
		assertNotNull(result);
		assertNotNull(result.getId());
		deleteMeId = result.getId();
	}
	
	@Test
	public void testRead() {
		Project result = projectService.read(1);
		assertNotNull(result);
		assertNotNull(result.getId());		
	}
	
	@Test
	public void testUpdate() {
		Project project = new Project();
		project.setDescription("Test1");
		project.setName("some name");
		Project result = projectService.create(project);
		assertNotNull(result.getId());
		deleteMeId = result.getId();
		
		result.setDescription("Test2");
		Project updateResult = projectService.update(result);
		assertNotNull(updateResult);
		assertEquals(updateResult.getId(), result.getId());
		assertEquals("Test2", updateResult.getDescription());		
		
	}

	@Test	
	public void testDelete() {
		Project project = new Project();
		project.setDescription("Test1");
		project.setName("some name");
		Project project2 = projectService.create(project);
		
		boolean result = projectService.delete(project2.getId());
		assertTrue(result);
		
		Project result2 = projectService.read(project2.getId());
		assertNull(result2);
	}
}
