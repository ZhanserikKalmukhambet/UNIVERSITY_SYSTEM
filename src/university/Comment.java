package university;


import java.io.Serializable;
import java.time.LocalDate;

public class Comment implements Serializable{
    private static final long serialVersionUID = 1L;
    
	private String text;
    private LocalDate date;
    
    public Comment() {
    	this.date = LocalDate.now();
    }
    public Comment(String text) {
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
    
	@Override
	public String toString() {
		return "Comment [text=" + text + ", date=" + date + "]";
	}    
}
