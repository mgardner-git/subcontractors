package com.acmecontracting.subcontractors.report;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.acmecontracting.subcontractors.Subcontractor;
import com.acmecontracting.subcontractors.project.Project;
import com.acmecontracting.subcontractors.reports.Report;
import com.acmecontracting.subcontractors.reports.ReportService;

import junit.framework.TestCase;

public class ReportServiceTest extends TestCase{

	ReportService reportService=new ReportService();	
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
		reportService = new ReportService();
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
