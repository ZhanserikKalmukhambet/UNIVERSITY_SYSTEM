package university;
import java.io.Serializable;

import java.util.*;
import java.util.Map.Entry;

import pairs.Pair1;
public class Transcript implements Serializable{
    private static final long serialVersionUID = 1L;
    
	private Vector<Pair1> grades = new Vector<Pair1>();

    public Transcript() {
    	
    }
    
	public Vector<Pair1> getGrades() {
		return grades;
	}
	
	public void addCourseToTranscript(Course c) {
		grades.add(new Pair1(c, new Mark()));
	}	

	public double getTotalScoreForGpa() {
		double sum = 0;
		
		for(Pair1 p : grades) {
			int credit = p.c.credit;
			double totalMark = p.m.getTotalScore();
			
			sum += totalMark * credit;
		}
		return sum/30;
	}
	
	public String toString() {
		String res = "TITLE     ECTS     SCORE     Traditional Grade\n";
		       res += "OOP        5       100             4.0";
		for(Pair1 p : grades) {
			
			String t = p.c.getTitle();
			int credit = p.c.credit;
			double totalMark = p.m.getTotalScore();
			String state;
			if(totalMark > 50)
				state = "PASS";
			else
				state = "NOT PASS";
			
			res = res + "  " + t + "        " + credit + "         " + totalMark + "            " + state + "\n";
		}
		
		return res;
	}
}
