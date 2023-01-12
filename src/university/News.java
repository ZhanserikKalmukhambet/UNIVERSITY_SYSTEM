package university;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import enums.Language;

public class News implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	
	private Language language;
    private String title;
    private String info;
    private LocalDate date;
    private HashMap<User, Comment> comments;
    
    public News() {
    	
    }
    public News(String title) {
    	this.title = title;
    }
    public News(String title, Language language) {
		this(title);
		this.language = language;
		this.setDate(LocalDate.now());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	public HashMap<User, Comment> getComments() {
		return comments;
	}
	
	public void addComment(User user, Comment c) {
		comments.put(user, c);
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "News [title=" + title + ", date=" + date + ", comments=" + comments + "]";
	}	
}
