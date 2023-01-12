package university;

import java.io.Serializable;

public class Mark implements Serializable, Cloneable, Comparable<Object>{
    private static final long serialVersionUID = 1L;
    
	private double att1Score;
	private double att2Score;
	private double finalScore;
	private double bonus;

    public Mark() {
    	
    }
    
    public double getAtt1Score() {
		return att1Score;
	} 
    
    public void setAtt1Score(double att1Score) {
		this.att1Score = att1Score;
	}

	public double getAtt2Score() {
		return att2Score;
	}

	public void setAtt2Score(double att2Sore) {
		this.att2Score = att2Sore;
	}

	public double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
	}
	
	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	} 
	
	public double getTotalScore() {
		if(this.att1Score + this.att2Score + this.finalScore + this.bonus > 100)
			return this.att1Score + this.att2Score + this.finalScore;
		return this.att1Score + this.att2Score + this.finalScore + this.bonus;
	} 

	@Override
	public String toString() {
		return "Mark [att1Score=" + att1Score + ", att2Sore=" + att2Score + ", finalScore=" + finalScore + "]";
	}

	@Override
	public int compareTo(Object o) {
		Mark m = (Mark)o;
		if(this.getTotalScore() > m.getTotalScore())
			return 1;
		if(this.getTotalScore() < m.getTotalScore())
			return -1;
		return 0;
	} 
}
