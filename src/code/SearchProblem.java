package code;

import java.io.ObjectInputStream.GetField;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public abstract class SearchProblem {
	private static HashMap<String, String> repeatedStates = new HashMap<String, String>();
	
	public abstract Node getInitialnode(); // gets the initial node for the problem
	
	
	
	public abstract String[] AllowedActions(Node node); // takes the state of the node and returns an array of the legal actions that can be done on this node for expansion
	
	
	
	public abstract Node applyaction(Node node,String action); // takes the action the will be applied to the node and returns the new node
	
	public abstract boolean goalTest(Node node);// takes a node and checks if it passed the goal test
	
	public abstract String getPath(Node node);//takes the node that passed the goal test and keeps backtracking to hte parent node to get all the actions that led to it
	
	public abstract int calculateHeuristic1(Node node);// calculate the first heuristic of the node
	
	public abstract int calculateHeuristic2(Node node); // calculate the second heurstic of the node
	
	public static String search(SearchProblem problem,QingFun Queningfunction) {
		Node initial=problem.getInitialnode();
		int nodesexpanded=0;
		switch (Queningfunction) {
		case ENQUEUE_AT_FRONT:
			LinkedList<Node> queue=new LinkedList<Node>();
			queue.addFirst(initial);
			while(!queue.isEmpty()) {
				Node node=queue.pollFirst();
				String state=node.getState();
				if(problem.goalTest(node)){ // check for goal state
					repeatedStates=new HashMap<String,String>(); // initlaize the repeatedstates hashmap
					return problem.getPath(node)+";"+nodesexpanded;
				}
				if(repeatedStates.get(state)!=null) { // if repeated state we will not expand
					continue;
				}
				repeatedStates.put(state, "T");
				nodesexpanded++;
				String[] AllowedAction=problem.AllowedActions(node);
				for (int i = 0; i < AllowedAction.length; i++) { // expanding the node by applying each legal action to it and putting it in the start of the queue
					String action= AllowedAction[i];
					int depth=node.getDepth();
					Node newnode=problem.applyaction(node, action); 
					queue.addFirst(newnode);
				}
			}
			
			
			break;
		case ENQUEUE_AT_ITERATIVE_END:
			for(int j=0;j==j;j++) { // infinte loop from i to infinity
				LinkedList<Node> queueIterative=new LinkedList<Node>(); // inatalize queue
				repeatedStates=new HashMap<String,String>(); // inatalize repetaed states
				queueIterative.addFirst(initial);
				while(!queueIterative.isEmpty()) {
					Node node=queueIterative.pollFirst();
					String state=node.getState();
					String[][] translatedState=node.translateState();
					if(problem.goalTest(node)){
						repeatedStates=new HashMap<String,String>();
						return problem.getPath(node)+";"+nodesexpanded;
					}
					if(repeatedStates.get(state)!=null) {
						continue;
					}
					int depth=node.getDepth();
					if(depth>j) { // if the depth of the current nodei s higher than j then we will not expand this node

						continue;
					}
					repeatedStates.put(state, "T");
					nodesexpanded++;
					String[] AllowedAction=problem.AllowedActions(node);
					for (int i = 0; i < AllowedAction.length; i++) {
						String action= AllowedAction[i];
						Node newnode=problem.applyaction(node, action);
						queueIterative.addFirst(newnode);
					}

				}
				
			}
				break;
		case ENQUEUE_AT_END:
			LinkedList<Node> queue1=new LinkedList<Node>();
			queue1.addFirst(initial);
			while(!queue1.isEmpty()) {
				Node node=queue1.pollFirst();
				String state=node.getState();
				String[][] translatedState=node.translateState();
				if(problem.goalTest(node)){
					repeatedStates=new HashMap<String,String>();
					return problem.getPath(node)+";"+nodesexpanded;
				}
				if(repeatedStates.get(state)!=null) {
					continue;
				}
				repeatedStates.put(state, "T");
				nodesexpanded++;
				String[] AllowedAction=problem.AllowedActions(node);

				
				for (int i = 0; i < AllowedAction.length; i++) {
					String action= AllowedAction[i];
					int depth=node.getDepth();
					Node newnode=problem.applyaction(node, action);
					queue1.addLast(newnode);
				}
			}
			break;
		case ENQUEUE_PATHCOST:
			PriorityQueue<Node> queue11=new PriorityQueue<Node>();
			queue11.add(initial);
			while(!queue11.isEmpty()) {
				Node node=queue11.poll();
				String state=node.getState();
				String[][] translatedState=node.translateState();
				if(problem.goalTest(node)){
					repeatedStates=new HashMap<String,String>();
					return problem.getPath(node)+";"+nodesexpanded;
				}
				if(repeatedStates.get(state)!=null) {
					continue;
				}
				repeatedStates.put(state, "T");
				nodesexpanded++;
				String[] AllowedAction=problem.AllowedActions(node);

				
				for (int i = 0; i < AllowedAction.length; i++) {
					String action= AllowedAction[i];
					int depth=node.getDepth();
					Node newnode=problem.applyaction(node, action);
					queue11.add(newnode);
				}
			}
			break;
		case ENQUEUE_GREEDY1:
			PriorityQueue<Node> queue111=new PriorityQueue<Node>();
			initial.setHeuristic(problem.calculateHeuristic1(initial));
			initial.setHeuristic1(true); // set the heurstic boolean with true so the compareTo function in the node will now compare the heurstic cost
			
			queue111.add(initial);
			while(!queue111.isEmpty()) {
				Node node=queue111.poll();
				String state=node.getState();
				String[][] translatedState=node.translateState();
				if(problem.goalTest(node)){
					repeatedStates=new HashMap<String,String>();
					return problem.getPath(node)+";"+nodesexpanded;
				}
				if(repeatedStates.get(state)!=null) {
					continue;
				}
				repeatedStates.put(state, "T");
				nodesexpanded++;
				String[] AllowedAction=problem.AllowedActions(node);

				
				for (int i = 0; i < AllowedAction.length; i++) {
					String action= AllowedAction[i];
					int depth=node.getDepth();
					Node newnode=problem.applyaction(node, action);
					newnode.setHeuristic(problem.calculateHeuristic1(newnode));
					newnode.setHeuristic1(true);

					queue111.add(newnode);
				}
			}
			break;
		case ENQUEUE_GREEDY2:
			PriorityQueue<Node> queue1111=new PriorityQueue<Node>();
			initial.setHeuristic(problem.calculateHeuristic2(initial));
			initial.setHeuristic1(true);
			
			queue1111.add(initial);
			while(!queue1111.isEmpty()) {
				Node node=queue1111.poll();
				String state=node.getState();
				String[][] translatedState=node.translateState();
				if(problem.goalTest(node)){
					repeatedStates=new HashMap<String,String>();
					return problem.getPath(node)+";"+nodesexpanded;
				}
				if(repeatedStates.get(state)!=null) {
					continue;
				}
				repeatedStates.put(state, "T");
				nodesexpanded++;
				String[] AllowedAction=problem.AllowedActions(node);

				
				for (int i = 0; i < AllowedAction.length; i++) {
					String action= AllowedAction[i];
					int depth=node.getDepth();
					Node newnode=problem.applyaction(node, action);
					newnode.setHeuristic(problem.calculateHeuristic2(newnode));
					newnode.setHeuristic1(true);
					queue1111.add(newnode);
				}
			}
			break;
			
		case ENQUEUE_ASTAR1:
			PriorityQueue<Node> queue11111=new PriorityQueue<Node>();
			initial.setHeuristic(problem.calculateHeuristic1(initial));
			initial.setHeuristic1(true);
			
			queue11111.add(initial);
			while(!queue11111.isEmpty()) {
				Node node=queue11111.poll();
				String state=node.getState();
				String[][] translatedState=node.translateState();
				if(problem.goalTest(node)){
					repeatedStates=new HashMap<String,String>();
					return problem.getPath(node)+";"+nodesexpanded;
				}
				if(repeatedStates.get(state)!=null) {
					continue;
				}
				repeatedStates.put(state, "T");
				nodesexpanded++;
				String[] AllowedAction=problem.AllowedActions(node);

				
				for (int i = 0; i < AllowedAction.length; i++) {
					String action= AllowedAction[i];
					int depth=node.getDepth();
					Node newnode=problem.applyaction(node, action);
					newnode.setHeuristic(problem.calculateHeuristic1(newnode));
					queue11111.add(newnode);
				}
			}
			break;

		case ENQUEUE_ASTAR2:
			PriorityQueue<Node> queue111111=new PriorityQueue<Node>();
			initial.setHeuristic(problem.calculateHeuristic2(initial));
			initial.setHeuristic1(true);
			
			queue111111.add(initial);
			while(!queue111111.isEmpty()) {
				Node node=queue111111.poll();
				String state=node.getState();
				String[][] translatedState=node.translateState();
				if(problem.goalTest(node)){
					repeatedStates=new HashMap<String,String>();
					return problem.getPath(node)+";"+nodesexpanded;
				}
				if(repeatedStates.get(state)!=null) {
					continue;
				}
				repeatedStates.put(state, "T");
				nodesexpanded++;
				String[] AllowedAction=problem.AllowedActions(node);

				
				for (int i = 0; i < AllowedAction.length; i++) {
					String action= AllowedAction[i];
					int depth=node.getDepth();
					Node newnode=problem.applyaction(node, action);
					newnode.setHeuristic(problem.calculateHeuristic2(node));
					queue111111.add(newnode);
				}
			}
			break;
			
			
		default:
			break;
		}
		repeatedStates=new HashMap<String,String>(); // initalize repetated states
		
		return "No Solution";
	}
}
