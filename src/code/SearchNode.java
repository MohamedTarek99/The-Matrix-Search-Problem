package code;

public abstract class SearchNode implements Comparable<Node> {
	private String state;
	private String action;
	private Node parent=null;
	private int pathcost=0;
	private boolean heuristic1=false;
	private int heuristic=0;
	private int depth;
	
	
	
	
	

	public void setHeuristic1(boolean heuristic1) {
		this.heuristic1 = heuristic1;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}

	
	
	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getState() {
		return state;
	}


	public Node getParent() {
		return parent;
	}


	public int getPathcost() {
		return pathcost;
	}


	public int getHeuristic() {
		return heuristic;
	}


	



public abstract String[][] translateState(); // this method translates the state into a 2d array so we can apply our other functions on it like applyaction




	
	
}
