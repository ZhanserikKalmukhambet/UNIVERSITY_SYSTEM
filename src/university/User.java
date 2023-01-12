package university;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import enums.Gender;

public abstract class User implements Serializable, Cloneable, Comparable<Object>, UserInt{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String surname;
	private UserPersonalInfo personalInfo = new UserPersonalInfo();
    private String login;
    private String password;
    
    public static int count = 0;
    
    {
    	User.count += 1;
    }
    
    public User() {
    	
    }
    public User(String name, String surname) {
    	this.setName(name);
    	this.setSurname(surname);
    }
    
    public UserPersonalInfo getPersonalInfo() {
        return this.personalInfo;
    }
    public void setPersonalInfo(UserPersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }
    public void fillPersonalInfo(LocalDate birth, Gender gender, String nation, String mobPhone, String address) {
    	this.personalInfo.setBirthDate(birth);
    	this.personalInfo.setGender(gender);
    	this.personalInfo.setNationality(nation);
    	this.personalInfo.setMobPhone(mobPhone);
    	this.personalInfo.setAddress(address);
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
    public String getLogin() {
        return this.login;
    }
    public void setLogin(String login) {
    	this.login = login;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    public void changePassword(String password) {
    	this.setPassword(password);
    }
    
    public boolean leaveComment(News news, Comment c) { // i should write there NewsNotFoundException
    	if(!UniSystem.getDatabase().getNews().contains(news))
    		return false;
    	
    	news.addComment(this, c);
    	return true;
    }
    
	@SuppressWarnings("unlikely-arg-type")
	public boolean deleteComment(News news, Comment c) { // CommentNotFoundException
    	if(news.getComments().containsKey(c)) {
    		news.getComments().remove(c);
    		return true;
    	}
    	return false;
    }
	
	public int hashCode() {
		return Objects.hash(login, password, personalInfo);
	}
    
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		
		User other = (User) o;
		return Objects.equals(this.login, other.login);
	}
	
	public String toString() {
    	return "Name - " + name + ", Surname - " + surname + ", Login - " + login + ", Password - " + password; 
    }
}
