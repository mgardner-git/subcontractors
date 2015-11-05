package com.acmecontracting.subcontractors.shift;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.acmecontracting.subcontractors.Subcontractor;
import com.acmecontracting.subcontractors.SubcontractorService;
import com.acmecontracting.subcontractors.project.Project;
import com.acmecontracting.subcontractors.project.ProjectService;


public class ShiftServiceTest{

	ShiftService shiftService;
	ProjectService projectService;
	SubcontractorService subcontractorService;	
	Project project;
	Subcontractor subcontractor;
	Integer deleteMeId;
	Date now;

	
	@After    
    public void after()
    {
        if (deleteMeId != null) {
            shiftService.delete(deleteMeId);//make sure our test cases are marked deleted
        }
		subcontractorService.delete(subcontractor.getId());
		projectService.delete(project.getId());

    }
	@Before
    public void before()
    {
		shiftService = new ShiftService();
		now = new Date();
		//the database won't allow a shift without a project to attach it to
		projectService = new ProjectService();
		project = new Project();
		project.setDescription("test");
		project.setName("test");
		project = projectService.create(project);
		
		subcontractorService = new SubcontractorService();
		subcontractor = new Subcontractor();
		subcontractor.setEmailAddress("test@test.com");
		subcontractor.setName("test");
		subcontractor.setPassword("T3ST");
		subcontractor = subcontractorService.create(subcontractor);
		
		deleteMeId = null;
    }
    
	@Test
	public void testCreate() {
		Shift shift = new Shift();
		shift.setDescription("Test1");
		shift.setBegin(now.getTime()-1000);
		shift.setEnd(now.getTime());	
		shift.setProject(project);
		shift.setSubcontractor(subcontractor);
		Shift result = shiftService.create(shift);
		assertNotNull(result);
		assertNotNull(result.getId());
		deleteMeId = result.getId();
	}
	
	@Test
	public void testRead() {
		Shift shift = new Shift();
		shift.setDescription("Test1");
		shift.setBegin(now.getTime()-1000);
		shift.setEnd(now.getTime());	
		shift.setProject(project);
		shift.setSubcontractor(subcontractor);
		Shift result = shiftService.create(shift);
		deleteMeId = result.getId();

		Shift result2 = shiftService.read(result.getId());
		assertNotNull(result2);
		assertNotNull(result2.getId());		
	}
	
	@Test
	public void testUpdate() {
		Shift shift = new Shift();
		shift.setDescription("Test1");
		shift.setBegin(now.getTime()-1000);
		shift.setEnd(now.getTime());	
		shift.setProject(project);
		shift.setSubcontractor(subcontractor);

		Shift result = shiftService.create(shift);
		assertNotNull(result.getId());
		deleteMeId = result.getId();		
		result.setDescription("Test2");
		Shift updateResult = shiftService.update(result);
		assertNotNull(updateResult);
		assertEquals(updateResult.getId(), result.getId());
		assertEquals("Test2", updateResult.getDescription());		
		
	}

	@Test	
	public void testDelete() {
		Shift shift = new Shift();
		shift.setDescription("Test1");
		shift.setBegin(now.getTime()-1000);
		shift.setEnd(now.getTime());	
		shift.setProject(project);
		shift.setSubcontractor(subcontractor);

		Shift shift2 = shiftService.create(shift);
		
		boolean result = shiftService.delete(shift2.getId());
		assertTrue(result);
		
		Shift result2 = shiftService.read(shift2.getId());
		assertNull(result2);
	}
}
