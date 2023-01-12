package pairs;

import enums.Presence;
import university.*;

public class Pair2 {
	  public Student s;
	  public Presence p;
	  
	  public Pair2() {};
	  
	  public Pair2(Student s, Presence p) {
	    this.s =  s;
	    this.p = p;
	  }
	  
	  public void setStudent(Student s) {
	    this.s = s;
	  }
	  
	  public void setPresence(Presence p) {
	    this.p = p;
	  }
	  public Student getStudent() {
	    return s;
	  }
	  
	}