package model;

import java.io.Serializable;
import javax.persistence.*;

import com.acmecontracting.subcontractors.Subcontractor;


/**
 * The persistent class for the assignment database table.
 * 
 */
@Entity
@Table(name="assignment")
@NamedQuery(name="Assignment.findAll", query="SELECT a FROM Assignment a")
public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="project_fk")
	private Integer projectFk;

	@Column(name="subcontractor_fk")
	private Integer subcontractorFk;
	
	public Assignment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getProjectFk() {
		return projectFk;
	}

	public void setProjectFk(Integer projectFk) {
		this.projectFk = projectFk;
	}

	public Integer getSubcontractorFk() {
		return subcontractorFk;
	}

	public void setSubcontractorFk(Integer subcontractorFk) {
		this.subcontractorFk = subcontractorFk;
	}

	
}