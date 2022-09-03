package code;

import java.util.ArrayList;
import java.util.Arrays;

public class Node extends SearchNode{
	private String state;
	private String action;
	private Node parent=null;
	private int pathcost=0;
	private boolean heuristic1=false;
	private int heuristic=0;
	private int depth;
	private int deaths;

	
	
	

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

	
	public Node(String State,Node Parent,String Action,int Depth,int pathcost,int deaths) {
		state=State;
		parent=Parent;
		action=Action;
		depth=Depth;
		this.pathcost=pathcost;
		this.deaths=deaths;
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


	public String[][] translateState(){
		 String[] Result;
		 String[] dimensions;
		String[] neo;
			String[] telephone;
			String[] agent;
			String[] pills;
			String[] padsAll;
			//public static String[] padsStart;
			//public static String[] padsFinish;
			String[] hostage;
			String[] mutatedHostage;
			String[] carriedHostage;
			String[] numberOfKills;
			String[] numberOfHostagesSaved;
			String[] numberOfHostagesCarried;
			String[] deaths;
			String[] strategy;
			String[] nodesNumber;
			String[] actions;
		 
		
		Result = state.split(";",2000);
		padsAll = Result[6].split(",",2000);
		String [] padsStart = new String[padsAll.length/2];
		String [] padsFinish = new String[padsAll.length/2];
		int startindex=0;
		if(padsAll.length==1) {
			padsAll=new String[] {};
		}
		for (int i = 0; i < padsAll.length; i=i+4) {
			padsStart[startindex]=padsAll[i];
			padsStart[startindex+1]=padsAll[i+1];
			padsFinish[startindex]=padsAll[i+2];
			padsFinish[startindex+1]=padsAll[i+3];
			startindex=startindex+2;
		}
//		int padStartIndex=0;
//		
//		int padFinishIndex=0;
//		for(String a :  padsAll) {
//			String x = a.toLowerCase();
//			 
//			
//			 if(x.contains("start")) {
//			
//				 padsStart[padStartIndex]=a;
//				 padStartIndex++;
//				 }
//			 if(x.contains("finishpad")) {
//				 
//				 padsFinish[padFinishIndex]=a;
//					 padFinishIndex++;
//					 }
//				
//			
//			
//			
//		}
//		
		String[] neoPositions=Result[2].split(",",2000);
		 neo=new String[] {neoPositions[0],neoPositions[1],neoPositions[2],Result[1]};
		 dimensions=Result[0].split(",",2000);
		telephone = Result[3].split(",", 2000);
		agent = Result[4].split(",", 2000);
		pills = Result[5].split(",", 2000);
		String [][] pads = {padsStart,padsStart};
		hostage = Result[7].split(",", 2000);
		mutatedHostage = Result[8].split(",", 2000);
		carriedHostage = Result[9].split(",", 2000);
		numberOfKills = Result[10].split(",", 2000);
		numberOfHostagesSaved = Result[11].split(",", 2000);
		numberOfHostagesCarried = Result[12].split(",", 2000);
		deaths = Result[13].split(",", 2000);
		if(mutatedHostage.length==1) {
			mutatedHostage=new String[] {};
		}
		if(carriedHostage.length==1) {
			carriedHostage=new String[] {};
		}
		if(agent.length==1) {
			agent=new String[] {};
		}
		
		if(hostage.length==1) {
			hostage=new String[] {};
		}
		if(pills.length==1) {
			pills=new String[] {};
		}
       

		String [][] output = {neo,telephone,agent,pills,padsStart,padsFinish,hostage,mutatedHostage,carriedHostage,numberOfKills,numberOfHostagesSaved,numberOfHostagesCarried,deaths,dimensions };
		
		
		return output;     
			   
			
		
		
		
		
			
		}
	

		
		
public int getDeaths() {
	return deaths;
}
	
@Override
public int compareTo(Node o) {
	int deaths=this.deaths;
	int nodedeaths=o.getDeaths();
	
	if(heuristic1) { // check if we are comparing the heurstic cost in the greedy search so we dont compare the real cost or the deaths
		return heuristic <  o.getHeuristic()? -1 : heuristic > o.getHeuristic()? 1 : 0;

	}

     if(deaths<nodedeaths) {
    	 return -1;
     }
     if(deaths>nodedeaths) {
    	 return 1;
     }              // if both node deaths are equal then we will calculte the cost 
    
     if(pathcost+heuristic<o.getPathcost()+o.getHeuristic()) { // heurstic will always be 0 except in greedy and a star searches
    	 return -1;
     }
     if(pathcost+heuristic>o.getPathcost()+o.getHeuristic()) {
    	 return 1;
     }

	return 0;
}

}
