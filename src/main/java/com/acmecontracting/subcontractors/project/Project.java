package com.acmecontracting.subcontractors.project;

import java.io.Serializable;
import javax.persistence.*;

import com.acmecontracting.subcontractors.reports.Report;
import com.acmecontracting.subcontractors.shift.Shift;

import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQueries(
   {@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p"),
   @NamedQuery(name="Project.myProjects", query="SELECT P FROM Project P,  Assignment A WHERE A.subcontractorFk=:id")}
 )
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator( name = "PROJECT_SEQUENCER", sequenceName = "MY_SEQ", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="PROJECT_SEQUENCER")
	private Integer id;

	@Lob
	private String description;

	private String name;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="project")
	private List<Report> reports;

	//bi-directional many-to-one association to Shift
	@OneToMany(orphanRemoval=true)
	@JoinColumn(name="project_fk")
	private List<Shift> shifts;

	public Project() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setProject(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setProject(null);

		return report;
	}

	public List<Shift> getShifts() {
		return this.shifts;
	}

	public void setShifts(List<Shift> shifts) {
		this.shifts = shifts;
	}

	public Shift addShift(Shift shift) {
		getShifts().add(shift);
		shift.setProjectId(id);

		return shift;
	}


}