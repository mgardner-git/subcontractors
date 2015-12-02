package com.acmecontracting.subcontractors.report;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acmecontracting.subcontractors.Subcontractor;
import com.acmecontracting.subcontractors.project.Project;
import com.acmecontracting.subcontractors.reports.Report;
import com.acmecontracting.subcontractors.reports.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath:root-context.xml"})
public class ReportServiceTest extends TestCase{

	@Autowired
	ReportService reportService;	
	Integer deleteMeId=null;

	@After    
    public void tearDown()
    {
        if (deleteMeId != null) {
            reportService.delete(deleteMeId);//make sure our test cases are marked deleted
        }
    }
	@Before
    public  void setup()
    {		
        deleteMeId = null;
    }
    
	@Test
	public void testCreate() {
		Report report = new Report();
		report.setDescription("Test1");
		Project project = new Project();
		project.setId(1);
		report.setProject(project);		
		Subcontractor subcontractor = new Subcontractor();
		subcontractor.setId(1);
		report.setSubcontractor(subcontractor);
		
		
		Report result = reportService.create(report);
		assertNotNull(result);
		assertNotNull(result.getId());
		deleteMeId = result.getId();
	}
	
	@Test
	public void testRead() {
		Report result = reportService.read(1);
		assertNotNull(result);
		assertNotNull(result.getId());		
	}
	
	@Test
	public void testUpdate() {
		Report report = new Report();
		report.setDescription("Test1");
		Project project = new Project();
		project.setId(1);
		report.setProject(project);
		Subcontractor subcontractor = new Subcontractor();
		subcontractor.setId(1);
		report.setSubcontractor(subcontractor);
		
		Report result = reportService.create(report);
		assertNotNull(result.getId());
		deleteMeId = result.getId();
		
		result.setDescription("Test2");
		Report updateResult = reportService.update(result);
		assertNotNull(updateResult);
		assertEquals(updateResult.getId(), result.getId());
		assertEquals("Test2", updateResult.getDescription());		
		
	}

	@Test	
	public void testDelete() {
		Report report = new Report();
		report.setDescription("Test1");
		Project project = new Project();
		project.setId(1);
		report.setProject(project);
		Subcontractor subcontractor = new Subcontractor();
		subcontractor.setId(1);
		report.setSubcontractor(subcontractor);

		Report report2 = reportService.create(report);
		
		boolean result = reportService.delete(report2.getId());
		assertTrue(result);
		
		Report result2 = reportService.read(report2.getId());
		assertNull(result2);
	}
	
}
