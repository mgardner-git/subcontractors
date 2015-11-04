package com.acmecontracting.subcontractors.shift;

import java.io.Serializable;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.acmecontracting.subcontractors.Subcontractor;
import com.acmecontracting.subcontractors.converters.LongToDateConverter;
import com.acmecontracting.subcontractors.project.Project;


/**
 * The persistent class for the shift database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Shift.findAll", query="SELECT s FROM Shift s"),
	@NamedQuery(name="Shift.findByProjectAndSubcontractor", query="SELECT S FROM Shift S WHERE S.project=:project AND S.subcontractor=:subcontractor"),
	@NamedQuery(name="Shift.findByProjectAndSubcontractorAndRange", query="SELECT S FROM Shift S WHERE S.project=:project AND S.subcontractor=:subcontractor AND S.begin>:begin AND S.begin<:end")
})
public class Shift implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator( name = "SHIFT_SEQUENCER", sequenceName = "MY_SEQ", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="SHIFT_SEQUENCER")
	private int id;


	@Lob
	private String description;

    @Convert(converter=LongToDateConverter.class)
	private Long begin;
	
	@Convert(converter=LongToDateConverter.class)
	private Long end;

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

	public Long getBegin() {
		return this.begin;
	}

	public void setBegin(Long begin) {
		this.begin = begin;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEnd() {
		return this.end;
	}

	public void setEnd(Long end) {
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