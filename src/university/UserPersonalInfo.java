package university;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import enums.Gender;

public class UserPersonalInfo implements Cloneable, Serializable{
    private static final long serialVersionUID = 1L;
   
    private LocalDate birthDate;
    private Gender gender;
    private String nationality;
    private String mobPhone;
    private String address;
    
    public UserPersonalInfo() {
    	
    }
    public UserPersonalInfo(LocalDate birth, Gender gender, String nation, String mobPhone, String address) {
    	this.birthDate = birth;
    	this.gender = gender;
    	this.nationality = nation;
    	this.mobPhone = mobPhone;
    	this.address = address;
    }

    public String getMobPhone() {
        return this.mobPhone;
    }
    public void setMobPhone(String mobPhone) {
    	this.mobPhone = mobPhone;
    }
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    public void setBirthDate(LocalDate birth) {
    	this.birthDate = birth;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
    	this.address = address;
    }
    public String getNationality() {
        return this.nationality;
    }
    public void setNationality(String nation) {
    	this.nationality = nation;
    }
    public Gender getGender() {
        return this.gender;
    }
    public void setGender(Gender gender) {
    	this.gender = gender;
    }
    	
	@Override
	public int hashCode() {
		return Objects.hash(address, birthDate, gender, mobPhone, nationality);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPersonalInfo other = (UserPersonalInfo) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
				&& gender == other.gender && Objects.equals(mobPhone, other.mobPhone)
				&& Objects.equals(nationality, other.nationality);
	}
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
    
    public String viewPersonalInfo() {
    	return "Birthdate - " + birthDate + "\nGender - " + gender +  
    			"\nNationality - " + nationality + "\nPhone - " + mobPhone + "\nAddress - " + address;
    }
}
