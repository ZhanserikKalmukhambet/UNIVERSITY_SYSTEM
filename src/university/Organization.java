package university;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import enums.OrganizationType;

public class Organization implements Serializable, Cloneable{
    private static final long serialVersionUID = 1L;
    
	private OrganizationType title;
    private Student head;
    private LocalDate openDate;
    private Vector<Student> members;
    
    public Organization(){
   
    }
    public Organization(OrganizationType title, Student head) {
    	this.title = title;
    	this.head = head;
    }
    public Organization(OrganizationType title, Student head, LocalDate openDate) {
    	this(title, head);
    	this.openDate = openDate;
    }
    public Organization(OrganizationType title, Student head, LocalDate openDate, Vector<Student> members) {
    	this(title, head, openDate);
    	this.members = members;
    }
    
    public OrganizationType getTitle() {
        return this.title;
    }
    public void setTitle(OrganizationType title) {
        this.title = title;
    }
    public Student getHead() {
        return this.head;
    }
    public void setHead(Student head) {
        this.head = head;
    } 
    public LocalDate getOpenDate() {
        return this.openDate;
    }
    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }
    public Vector<Student> getMembers() {
        return this.members;
    }
    public void setMembers(Vector<Student> members) {
        this.members = members;
    }
    
    public boolean addMember(Student s) {
    	if(members.contains(s))
    		return false;
    	
    	members.add(s);
    	return true;
    }
      
	@Override
	public int hashCode() {
		return Objects.hash(head, members, openDate, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organization other = (Organization) obj;
		return this.title == other.title;
		
	}
	@Override
	public String toString() {
		return "Organization [title=" + title + ", head=" + head + ", openDate=" + openDate + ", members=" + members
				+ "]";
	}
}
