package model;

import java.io.Serializable;
import javax.persistence.*;

import com.acmecontracting.subcontractors.Subcontractor;


/**
 * The persistent class for the assignment database table.
 * 
 */
@Entity
@NamedQuery(name="Assignment.findAll", query="SELECT a FROM Assignment a")
public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="project_fk")
	private int projectFk;

	//bi-directional many-to-one association to Subcontractor
	@ManyToOne
	@JoinColumn(name="subcontractor_fk")
	private Subcontractor subcontractor;

	public Assignment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectFk() {
		return this.projectFk;
	}

	public void setProjectFk(int projectFk) {
		this.projectFk = projectFk;
	}

	public Subcontractor getSubcontractor() {
		return this.subcontractor;
	}

	public void setSubcontractor(Subcontractor subcontractor) {
		this.subcontractor = subcontractor;
	}

}