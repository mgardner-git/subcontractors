package com.acmecontracting.subcontractors.shift;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.acmecontracting.subcontractors.converters.LongToDateConverter;


/**
 * The persistent class for the shift database table.
 * 
 */
@Entity
@Table(name="shift")
@NamedQueries({
	@NamedQuery(name="Shift.findAll", query="SELECT s FROM Shift s"),
	@NamedQuery(name="Shift.readByShiftAndSubcontractor", query="SELECT S FROM Shift S WHERE S.subcontractorId=:subcontractorId AND S.projectId=:projectId")
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

	@Column(name="project_fk")
	private Integer projectId;
	
	@Column(name="subcontractor_fk")
	private Integer subcontractorId;
	


	public Shift() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getBegin() {
		return this.begin;
	}

	public void setBegin(Long begin) {
		this.begin = begin;
	}
	
	public Long getEnd() {
		return this.end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getSubcontractorId() {
		return subcontractorId;
	}

	public void setSubcontractorId(Integer subcontractorId) {
		this.subcontractorId = subcontractorId;
	}

}