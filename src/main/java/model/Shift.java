package model;

import java.io.Serializable;
import javax.persistence.*;

import com.acmecontracting.subcontractors.project.Project;

import java.util.Date;


/**
 * The persistent class for the shift database table.
 * 
 */
@Entity
@NamedQuery(name="Shift.findAll", query="SELECT s FROM Shift s")
public class Shift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date begin;

	@Lob
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date end;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project_fk")
	private Project project;

	//bi-directional many-to-one association to Subcontractor
	@ManyToOne
	@JoinColumn(name="subcontractor_fk")
	private Subcontractor subcontractor;

	public Shift() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBegin() {
		return this.begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEnd() {
		return this.end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Subcontractor getSubcontractor() {
		return this.subcontractor;
	}

	public void setSubcontractor(Subcontractor subcontractor) {
		this.subcontractor = subcontractor;
	}

}