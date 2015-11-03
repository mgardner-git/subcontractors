package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subcontractor database table.
 * 
 */
@Entity
@NamedQuery(name="Subcontractor.findAll", query="SELECT s FROM Subcontractor s")
public class Subcontractor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="subcontractor")
	private List<Report> reports;

	//bi-directional many-to-one association to Shift
	@OneToMany(mappedBy="subcontractor")
	private List<Shift> shifts;

	public Subcontractor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
		report.setSubcontractor(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setSubcontractor(null);

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
		shift.setSubcontractor(this);

		return shift;
	}

	public Shift removeShift(Shift shift) {
		getShifts().remove(shift);
		shift.setSubcontractor(null);

		return shift;
	}

}