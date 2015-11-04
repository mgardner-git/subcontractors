package com.acmecontracting.subcontractors;

import java.io.Serializable;
import javax.persistence.*;

import model.Assignment;

import java.util.List;


/**
 * The persistent class for the subcontractor database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Subcontractor.findAll", query="SELECT S FROM Subcontractor S"),
	@NamedQuery(name="Subcontractor.login", query="SELECT S FROM Subcontractor S WHERE S.emailAddress=:emailAddress AND S.password=:password")		
})
public class Subcontractor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String emailAddress;

	private String name;

	private String password;

	//bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy="subcontractor")
	private List<Assignment> assignments;

	public Subcontractor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Assignment addAssignment(Assignment assignment) {
		getAssignments().add(assignment);
		assignment.setSubcontractor(this);

		return assignment;
	}

	public Assignment removeAssignment(Assignment assignment) {
		getAssignments().remove(assignment);
		assignment.setSubcontractor(null);

		return assignment;
	}

}