package university;

import java.util.Vector;

public class ResearchDecorator extends UserDecorator{
	
	private static final long serialVersionUID = 1L;
	protected Vector<ResearchPaper> researches;
	private int hIndex;
	
	public ResearchDecorator(UserInt u) {
		super(u);
		this.researches = new Vector<ResearchPaper>();
	}
	public boolean citeResearch(ResearchPaper researchToCite, ResearchPaper researchMine) {
		if (this.researches.contains(researchToCite) && !researchToCite.authors.equals(researchMine.authors)) return this.citeResearch(researchMine, researchToCite);
		return researchMine.citations.add(researchToCite);
	}
	
	public boolean addResearch(ResearchPaper research) {
		if (this.researches.contains(research)) return false;
		return this.researches.add(research);
	}
	
	public Vector<ResearchPaper> getResearches() {
		return researches;
	}

	public void setResearches(Vector<ResearchPaper> researches) {
		this.researches = researches;
	}
	

}
