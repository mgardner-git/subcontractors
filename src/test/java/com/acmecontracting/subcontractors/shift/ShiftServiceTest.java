package com.acmecontracting.subcontractors.shift;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acmecontracting.subcontractors.Subcontractor;
import com.acmecontracting.subcontractors.SubcontractorService;
import com.acmecontracting.subcontractors.project.Project;
import com.acmecontracting.subcontractors.project.ProjectService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath:root-context.xml"})
public class ShiftServiceTest{

	@Autowired
	ShiftService shiftService;
	@Autowired
	ProjectService projectService;
	@Autowired
	SubcontractorService subcontractorService;	
	Project project;
	Subcontractor subcontractor;
	Integer deleteMeId;
	Date now;

	
	@After    
    public void tearDown()
    {
        if (deleteMeId != null) {
            shiftService.delete(deleteMeId);//make sure our test cases are marked deleted
        }
		subcontractorService.delete(subcontractor.getId());
		projectService.delete(project.getId());

    }
	@Before
    public void setup()
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
		shift.setProjectId(project.getId());
		shift.setSubcontractorId(subcontractor.getId());
		Shift result = shiftService.create(shift);
		assertNotNull(result);
		assertNotNull(result.getId());
		deleteMeId = result.getId();
	}
	
	@Test
	//this test is designed to be run directly after the sql.txt file 
	public void testReadBySubcontractorAndProject() {
		List<Shift> results = shiftService.readByProjectAndSubcontractor(1, 1);
		assertNotNull(results);
		assertEquals(1,results.size());
		Shift result = results.get(0);
		assertEquals("raked leaves", result.getDescription());		
	}
	
	@Test
	public void testRead() {
		Shift shift = new Shift();
		shift.setDescription("Test1");
		shift.setBegin(now.getTime()-1000);
		shift.setEnd(now.getTime());	
		shift.setProjectId(project.getId());
		shift.setSubcontractorId(subcontractor.getId());
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
		shift.setProjectId(project.getId());
		shift.setSubcontractorId(subcontractor.getId());

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
		shift.setProjectId(project.getId());
		shift.setSubcontractorId(subcontractor.getId());

		Shift shift2 = shiftService.create(shift);
		
		boolean result = shiftService.delete(shift2.getId());
		assertTrue(result);
		
		Shift result2 = shiftService.read(shift2.getId());
		assertNull(result2);
	}
}
