package university;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Vector;

public class ResearchPaper implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Vector<ResearchDecorator> authors;
	String title;
	String description;
	Date publicationDate;
	Vector<ResearchPaper> citations;
	Vector<ResearchPaper> references;
	Vector<String> keywords;
	
	public ResearchPaper() {
		
	}
	
	public ResearchPaper(String title) {
		this.title = title;
	}
	
	public ResearchPaper(String title, String description) {
		this(title);
		this.description = description;
	}
	
	public ResearchPaper(String title, String description, Date publicationDate) {
		this(title, description);
		this.publicationDate = publicationDate;
	}
	
	public ResearchPaper(String title, String description, Date publicationDate, Vector<String> keywords) {
		this(title, description, publicationDate);
		this.keywords = keywords;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Vector<ResearchPaper> getCitations() {
		return citations;
	}

	public void setCitations(Vector<ResearchPaper> citations) {
		this.citations = citations;
	}

	public Vector<ResearchPaper> getReferences() {
		return references;
	}

	public void setReferences(Vector<ResearchPaper> references) {
		this.references = references;
	}

	public Vector<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Vector<String> keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "ResearchPaper [title=" + title + ", description=" + description + ", publicationDate=" + publicationDate
				+ ", citations=" + citations + ", references=" + references + ", keywords=" + keywords + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(citations, description, keywords, publicationDate, references, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResearchPaper other = (ResearchPaper) obj;
		return Objects.equals(citations, other.citations) && Objects.equals(description, other.description)
				&& Objects.equals(keywords, other.keywords) && Objects.equals(publicationDate, other.publicationDate)
				&& Objects.equals(references, other.references) && Objects.equals(title, other.title);
	}
	
	
}
