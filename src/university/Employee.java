package university;

import java.time.LocalDate;
import java.util.*;

public abstract class Employee extends User {
    private static final long serialVersionUID = 1L;
    
	private double workExperience;
    private int salary;
    private LocalDate hireDate;
    private Vector<Chat> chats;
    
    public Employee() {
    	super();
    }
    public Employee(String name, String surname) {
    	super(name, surname);
    }
    public Employee(String name, String surname, double workExperience, int salary, LocalDate hireDate) {
    	this(name, surname);
    	this.workExperience = workExperience;
    	this.salary = salary;
    	this.hireDate = hireDate;
    }
    
	public double getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(double workExperience) {
		this.workExperience = workExperience;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	public Vector<Chat> getChats() {
		return chats;
	}
	public void setChats(Vector<Chat> chats) {
		this.chats = chats;
	}  
	
	public boolean sendMessage(Employee to, Message message) {
		for(Chat c : chats) {
			if(Objects.equals(c.getReceiver(), to)) {
				c.writeMessage(message);
				return true;
			}
		}
		
		Chat newChat = new Chat(to, message);
		return true;
	}
	
	public int compareTo(Object o) {
		Employee e = (Employee)o;
		if(this.getSalary() > e.getSalary())
			return 1;
		if(this.getSalary() < e.getSalary())
			return -1;
		
		return 0;
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		
		Employee e = (Employee) o;
		return this.workExperience == e.workExperience && this.salary == e.salary && Objects.equals(this.hireDate, e.hireDate);
	}
	
	public String toString() {
		return super.toString();
	}
}
