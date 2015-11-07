package com.acmecontracting.subcontractors.project;

import java.util.List;

import com.acmecontracting.subcontractors.Subcontractor;

import model.Assignment;

/**
 * Represents A Big package of data that is send when analyzing projects. 
 * See Also: AnalyzeProjects.jsp
 * @author monte.gardner
 *
 */
public class ProjectAnalysisDTO {

	private List<Project> projects;
	private List<Subcontractor> subcontractors;
	private List<Assignment> assignments;
	
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<Subcontractor> getSubcontractors() {
		return subcontractors;
	}
	public void setSubcontractors(List<Subcontractor> subcontractors) {
		this.subcontractors = subcontractors;
	}
	public List<Assignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
}
