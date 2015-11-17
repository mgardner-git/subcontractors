package com.acmecontracting.subcontractors.reports;

import java.io.Serializable;
import javax.persistence.*;

import com.acmecontracting.subcontractors.Subcontractor;
import com.acmecontracting.subcontractors.project.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;

import model.Image;

import java.util.List;


/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Report.findAll", query="SELECT r FROM Report r"),
	@NamedQuery(name="Report.myReports", query="SELECT R FROM Report R WHERE R.project.id=:projectId AND R.subcontractor.id=:subcontractorId")
})
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="report")
	private List<Image> images;

	@Column(name="project_fk", insertable=false, updatable=false)
	private Integer project_fk;
	
	@Column(name="subcontractor_fk",insertable=false, updatable=false)
	private Integer subcontractor_fk;
	
	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project_fk")
	@JsonIgnore
	private Project project;

	//bi-directional many-to-one association to Subcontractor
	@ManyToOne
	@JoinColumn(name="subcontractor_fk")
	@JsonIgnore
	private Subcontractor subcontractor;

	public Report() {
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

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setReport(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setReport(null);

		return image;
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

	public Integer getProject_fk() {
		return project_fk;
	}

	public void setProject_fk(Integer project_fk) {
		this.project_fk = project_fk;
	}

	public Integer getSubcontractor_fk() {
		return subcontractor_fk;
	}

	public void setSubcontractor_fk(Integer subcontractor_fk) {
		this.subcontractor_fk = subcontractor_fk;
	}

}