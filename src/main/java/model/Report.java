package model;

import java.io.Serializable;
import javax.persistence.*;

import com.acmecontracting.subcontractors.project.Project;

import java.util.List;


/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@NamedQuery(name="Report.findAll", query="SELECT r FROM Report r")
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="report")
	private List<Image> images;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project_fk")
	private Project project;

	//bi-directional many-to-one association to Subcontractor
	@ManyToOne
	@JoinColumn(name="subcontractor_fk")
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

}