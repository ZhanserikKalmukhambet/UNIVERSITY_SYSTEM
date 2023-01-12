package university;

import java.io.Serializable;
import java.time.LocalDate;

public class Message implements Serializable, Cloneable{
    
    private static final long serialVersionUID = 1L;
	private String text;
    private LocalDate date;
    
    public Message() {
    	this.date = LocalDate.now();
    }
    public Message(String text) {
    	this();
    	this.text = text;
    }

    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String toString() {
    	return this.text + " " + this.date;
    }
}
