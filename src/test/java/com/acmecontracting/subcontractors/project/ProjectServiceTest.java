package com.acmecontracting.subcontractors.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath:root-context.xml"})
public class ProjectServiceTest extends TestCase{

	@Autowired
	ProjectService projectService;	
	Integer deleteMeId=null;

	@After    
    public void tearDown()
    {
        if (deleteMeId != null) {
            projectService.delete(deleteMeId);//make sure our test cases are marked deleted
        }
    }
	@Before
    public  void setup()
    {
		projectService = new ProjectService();
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
	
	@Test
	//this method is meant to be run just after the sql.txt script.
	public void testAnalyze() {
		ProjectAnalysisDTO analysis = projectService.analyze();
		assertNotNull(analysis);
		assertNotNull(analysis.getAssignments());
		assertEquals(3, analysis.getAssignments().size());
		assertNotNull(analysis.getProjects());
		assertEquals(3, analysis.getProjects().size());
		assertNotNull(analysis.getSubcontractors());
		assertEquals(3, analysis.getSubcontractors().size());
	}
}
