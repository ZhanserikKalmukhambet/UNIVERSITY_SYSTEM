package university;

import java.io.Serializable;
import java.util.*;

import enums.TypeFile;
public class File implements Serializable, Cloneable{
    private static final long serialVersionUID = 1L;
    
    private String name;
    private TypeFile type;
    private double size;
    
    public File() {
    	
    }
    public File(String name, TypeFile type, double size) {
    	this.name = name;
    	this.type = type;
    	this.size = size;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TypeFile getType() {
		return type;
	}
	public void setType(TypeFile type) {
		this.type = type;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
}
