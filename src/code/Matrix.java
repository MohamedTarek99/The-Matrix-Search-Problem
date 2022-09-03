package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.lang.management.ManagementFactory;

public  class Matrix extends SearchProblem {
	Node initialnode;
	static boolean Visualize;
	
	public Matrix(Node initial,boolean visual) {
		initialnode=initial;
		Visualize=visual;
	}
	
	public Node getInitialnode() {
		return initialnode;
	}
	
	public static boolean isOccupied(int x,int y,String[][] grid) { // takes the x and y position and return true if they are occupied
		if(grid[x][y]==null) {
			return false;
		}
		return true;
	}
	
	
	public void visualizeGrid(Node node) { // takes the node and visualize the grid using its state
		
		String[][] state=node.translateState();
		int neoX=Integer.valueOf(state[0][0]);
		int neoY=Integer.valueOf(state[0][1]);
		int neodamage=Integer.valueOf(state[0][2]);
		int carrylimit=Integer.valueOf(state[0][3]);
		int phoneX=Integer.valueOf(state[1][0]);
		int phoneY=Integer.valueOf(state[1][1]);
		ArrayList<String> agents=new ArrayList<String>(Arrays.asList(state[2]));
		ArrayList<String> pills=new ArrayList<String>(Arrays.asList(state[3]));
		String[] startpads=state[4];
		String[] finishpads=state[5];		
		ArrayList<String> hostages=new ArrayList<String>(Arrays.asList(state[6]));
		ArrayList<String> mutatedhostages=new ArrayList<String>(Arrays.asList(state[7]));
		ArrayList<String> carriedhostages=new ArrayList<String>(Arrays.asList(state[8]));
		int kills=Integer.valueOf(state[9][0]);
		int saves=Integer.valueOf(state[10][0]);
		int numberofhostagecarried=Integer.valueOf(state[11][0]);
		int deaths=Integer.valueOf(state[12][0]);
	    int width=Integer.valueOf(state[13][0]);
	    int height=Integer.valueOf(state[13][1]);
	    String[][] grid=new String[height][width];
	    System.out.println("Action:"+node.getAction());
	    
	    ArrayList<String> carriedhostagedamage=new ArrayList<String>();
	    for (int i = 0; i < carriedhostages.size(); i=i+3) {
	    	carriedhostagedamage.add(carriedhostages.get(i+2));
			
		}
	    
	    System.out.println("CarriedHostagesDamage:"+String.join(",", carriedhostagedamage));
	    System.out.println("Deaths:"+deaths);
	    System.out.println("Kills:"+kills);
	    grid[neoX][neoY]="Neo("+neodamage+")";
	    grid[phoneX][phoneY]=(grid[phoneX][phoneY]+" TB").trim().replace("null", "");
	    for (int i = 0; i < agents.size(); i=i+2) {
      int agentX=Integer.valueOf(agents.get(i));
      int agentY=Integer.valueOf(agents.get(i+1));
      if(grid[agentX][agentY]==null) {
	    grid[agentX][agentY]="A";
      }
      
		}
	    for (int i = 0; i < mutatedhostages.size(); i=i+2) { // loop for the mutated to put them in the 2d array
	        int mutatedX=Integer.valueOf(mutatedhostages.get(i));
	        int mutatedY=Integer.valueOf(mutatedhostages.get(i+1));
	       

	  	    grid[mutatedX][mutatedY]="MH";

	        
	  		}
	    
	    for (int i = 0; i < pills.size(); i=i+2) { // puts the pills in their places on the 2d array
	        int pillX=Integer.valueOf(pills.get(i));
	        int pillY=Integer.valueOf(pills.get(i+1));
	        if(grid[pillX][pillY]==null) { // check if there is something that is in the same cell like Neo
	    	    grid[pillX][pillY]="P";
	          }else {
	        	  grid[pillX][pillY]=grid[pillX][pillY]+" P"; // if there someone justs put the pill beside him
	          }

	  		}
	    
	    for (int i = 0; i < hostages.size(); i=i+3) {
	        int hostageX=Integer.valueOf(hostages.get(i));
	        int hostageY=Integer.valueOf(hostages.get(i+1));
	        int hostagedamage=Integer.valueOf(hostages.get(i+2));
	        if(grid[hostageX][hostageY]==null) {
	        	 grid[hostageX][hostageY]="H("+hostagedamage+")";
	        }else {
		  	    grid[hostageX][hostageY]=grid[hostageX][hostageY]+" H("+hostagedamage+")";

	        }


	        
	  		}
	    for (int i = 0; i < startpads.length; i=i+2) {
	    	int padx=Integer.valueOf(startpads[i]);
	    	int pady=Integer.valueOf(startpads[i+1]);
	    	int fpadx=Integer.valueOf(finishpads[i]);
	    	int fpady=Integer.valueOf(finishpads[i+1]);
	    	if(grid[padx][pady]==null) {
		    	grid[padx][pady]="Pad("+fpadx+","+fpady+")";

	    	}else {
		    	grid[padx][pady]=grid[padx][pady]+" Pad("+fpadx+","+fpady+")";

	    	}
			
		}
	    for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
	    
	    Visualize=false;
	    
		
	}

	
	public static ArrayList<ArrayList<String>> killAgents(ArrayList<String> agents,ArrayList<String> mutatedagents,int neoX,int neoY,int kills) { //takes the agents and mutated agents and neo positions and removes the agents that are neighbours to neo from the arrays and returns them in a 2d array
		ArrayList<String> newagents=new ArrayList<String>();
		ArrayList<String> newmutatedagents=new ArrayList<String>();
		ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
		int mutatedkills=0;
		int agentkills=0;
       for (int i = 0; i < mutatedagents.size(); i=i+2) {
		int mutatedagentx=Integer.valueOf(mutatedagents.get(i));
		int mutatedagenty=Integer.valueOf(mutatedagents.get(i+1));
		if((Math.abs(neoX-mutatedagentx)<=1&&neoY==mutatedagenty) || (Math.abs(neoY-mutatedagenty)<=1 && neoX==mutatedagentx)) {
			mutatedkills++;
			continue;
		}
		newmutatedagents.add(""+mutatedagentx);
		newmutatedagents.add(""+mutatedagenty);
	}
       for (int i = 0; i < agents.size(); i=i+2) {
   		int agentx=Integer.valueOf(agents.get(i));
   		int agenty=Integer.valueOf(agents.get(i+1));
   		if((Math.abs(neoX-agentx)<=1&&neoY==agenty) || (Math.abs(neoY-agenty)<=1&&neoX==agentx)) {
   			agentkills++;
   			continue;
   		}
   		newagents.add(""+agentx);
   		newagents.add(""+agenty);
   	}
       result.add(newagents);
       result.add(newmutatedagents);
       ArrayList<String> allkills=new ArrayList<String>();
       allkills.add(""+mutatedkills);
       allkills.add(""+agentkills);
       result.add(allkills);
       return result;
		
	}
	

	public static String genGrid() { // generates a random grid

		 String world="";
		 int random_width = 4;
		 int random_height =4;
		 int total_gridSize=random_width*random_height;
	     int random_hostage = (int)Math.floor(Math.random()*(10-5+1)+5);
	     int random_pills = (int)Math.floor(Math.random()*(random_hostage+1));

	     total_gridSize=total_gridSize-random_hostage-random_pills-2; // -2 is for the phonebooth and Neo
	     int random_carrySpace = (int)Math.floor(Math.random()*(4-1+1)+1);
	     world=world+random_width+","+random_height+";"+random_carrySpace+";";
	     int random_agentsNumbers=(int)Math.floor(Math.random()*(total_gridSize));
	     total_gridSize=total_gridSize-random_agentsNumbers;
	     int random_portalNumbers=(int)Math.floor(Math.random()*(total_gridSize/2)); // we divide by 2 since every portal will have another one to teleport to

	     String[][] grid=new String[random_height][random_width];
	 
	     int neoX = (int)Math.floor(Math.random()*(random_height));
	     int neoY=(int)Math.floor(Math.random()*(random_width));
	     grid[neoX][neoY]="Neo";
	     
	     int phoneX=(int)Math.floor(Math.random()*(random_height));
	     int phoneY=(int)Math.floor(Math.random()*(random_width));
	     while(isOccupied(phoneX,phoneY,grid)) {
	    	  phoneX=(int)Math.floor(Math.random()*(random_height));
	          phoneY=(int)Math.floor(Math.random()*(random_width));
	     }
	     world=world+neoX+","+neoY+";"+phoneX+","+phoneY+";";
	     grid[phoneX][phoneY]="Booth";
	     
	     for (int i = 0; i < random_agentsNumbers; i++) {
				int AgentX=(int)Math.floor(Math.random()*(random_height));
				int AgentY=(int)Math.floor(Math.random()*(random_width));
				  while(isOccupied(AgentX,AgentY,grid)) {
			    	  AgentX=(int)Math.floor(Math.random()*(random_height));
			          AgentY=(int)Math.floor(Math.random()*(random_width));
			     }
			grid[AgentX][AgentY]="A";
			world=world+AgentX+","+AgentY;
			if(i<random_agentsNumbers-1) {
				world=world+",";
			}
			
			}
	   
	     world=world+";";

	     
	     for (int i = 0; i < random_pills; i++) {
	 		int pillX=(int)Math.floor(Math.random()*(random_height));
	 		int pillY=(int)Math.floor(Math.random()*(random_width));
	 		  while(isOccupied(pillX,pillY,grid)) {
	 	    	  pillX=(int)Math.floor(Math.random()*(random_height));
	 	          pillY=(int)Math.floor(Math.random()*(random_width));
	 	     }
	 	grid[pillX][pillY]="P";
	 	world=world+pillX+","+pillY;
	 	if(i<random_pills-1) {
			world=world+",";
		}
	 	
	 	}
	    world=world+";";
	    for (int i = 0; i < random_portalNumbers; i++) {
			int portalX=(int)Math.floor(Math.random()*(random_height));
			int portalY=(int)Math.floor(Math.random()*(random_width));
			int portalX2=(int)Math.floor(Math.random()*(random_height));
			int portalY2=(int)Math.floor(Math.random()*(random_width));
			  while(isOccupied(portalX,portalY,grid)) {
		    	 portalX=(int)Math.floor(Math.random()*(random_height));
		          portalY=(int)Math.floor(Math.random()*(random_width));
		     }
		grid[portalX][portalY]="Pad";
		while(isOccupied(portalX2,portalY2,grid)) {
	   	 portalX2=(int)Math.floor(Math.random()*(random_height));
	         portalY2=(int)Math.floor(Math.random()*(random_width));
	    }
		grid[portalX][portalY]="Pad("+portalX2+","+portalY2+")";
		grid[portalX2][portalY2]="Pad("+portalX+","+portalY+")";
		world=world+portalX+","+portalY+","+portalX2+","+portalY2+",";
		world=world+portalX2+","+portalY2+","+portalX+","+portalY;
		if(i<random_portalNumbers-1) {
			world=world+",";
		}
		}
	    world=world+";";
	    
	    
	    for (int i = 0; i < random_hostage; i++) {
			int hostageX=(int)Math.floor(Math.random()*(random_height));
			int hostageY=(int)Math.floor(Math.random()*(random_width));
			int hostageDamage=(int)Math.floor(Math.random()*(99-1+1)+1);
			  while(isOccupied(hostageX,hostageY,grid)) {
		    	  hostageX=(int)Math.floor(Math.random()*(random_height));
		          hostageY=(int)Math.floor(Math.random()*(random_width));
		     }
		grid[hostageX][hostageY]="H("+hostageDamage+")";
		world=world+hostageX+","+hostageY+","+hostageDamage;
		if(i<random_hostage-1) {
			world=world+",";
		}
		
		}
	    return world;
	}
	public static Node initalizeNode(String Grid) {
		String initialstate="";
    for (int i = 0; i < Grid.length(); i++) {
		if(i==9) {
			initialstate=initialstate+",0";
		}
		initialstate=initialstate+Grid.charAt(i);
	}
	initialstate=initialstate+";;;0;0;0;0;;0;;"; // here we will initalize the grid by intalizing kills,carried hostages and more
	Node initialnode=new Node(initialstate,null,null,0,0,0);
	return initialnode;
	}
	
	
	
	public static String solve(String grid,String strategy,boolean visualize) { 
		Node initial=initalizeNode(grid); // initalize a new node which si the root
		Visualize=visualize; // set the visualize variable 
	String result="";
	SearchProblem problem=new Matrix(initial,visualize);
	switch(strategy) {	
	case "BF":
		result=search(problem,QingFun.ENQUEUE_AT_END);
		break;
	case "DF":
		result=search(problem,QingFun.ENQUEUE_AT_FRONT);
		break;
	case "UC":
		result=search(problem, QingFun.ENQUEUE_PATHCOST);
		break;
	case "ID":
		result=search(problem, QingFun.ENQUEUE_AT_ITERATIVE_END);
		break;
	case "GR1":
		result=search(problem,QingFun.ENQUEUE_GREEDY1);
		break;
	case "GR2":
		result=search(problem,QingFun.ENQUEUE_GREEDY2);
		break;
	case "AS1":
		result=search(problem,QingFun.ENQUEUE_ASTAR1);
		break;
	case "AS2":
		result=search(problem,QingFun.ENQUEUE_ASTAR2);
		break;
		

		 
	default:
	    break;
	 
				
		
	}
	return result;
	}

	public int calculateHeuristic1(Node node) {
		String[][] state=node.translateState();
		int neoX=Integer.valueOf(state[0][0]);
		int neoY=Integer.valueOf(state[0][1]);
		int neodamage=Integer.valueOf(state[0][2]);
		int carrylimit=Integer.valueOf(state[0][3]);
		int phoneX=Integer.parseInt(state[1][0]);
		int phoneY=Integer.parseInt(state[1][1]);
		ArrayList<String> agents=new ArrayList<String>(Arrays.asList(state[2]));
		ArrayList<String> pills=new ArrayList<String>(Arrays.asList(state[3]));
		String[] startpads=state[4];
		String[] finishpads=state[5];		
		ArrayList<String> hostages=new ArrayList<String>(Arrays.asList(state[6]));
		ArrayList<String> mutatedhostages=new ArrayList<String>(Arrays.asList(state[7]));
		ArrayList<String> carriedhostages=new ArrayList<String>(Arrays.asList(state[8]));
		int kills=Integer.valueOf(state[9][0]);
		int saves=Integer.valueOf(state[10][0]);
		int numberofhostagecarried=Integer.valueOf(state[11][0]);
		int deaths=Integer.valueOf(state[12][0]);
	    int width=Integer.valueOf(state[13][0]);
	    int height=Integer.valueOf(state[13][1]);
	    int distancetophonebooth=Math.abs(neoX-Integer.valueOf(phoneX)+neoY-Integer.valueOf(phoneY));
	    int mindistance=Integer.MAX_VALUE;
	   
	    if(hostages.size()==0) {
	    	return 0;
	    }
	    int maximumdamageforhostage=0;
	    int totaldistancetohostage=0;
	    
	    for (int i = 0; i < hostages.size(); i=i+3) {
	    	int hostagex=Integer.valueOf(hostages.get(i));
	    	int hostagey=Integer.valueOf(hostages.get(i+1));
	    	int hostagedamage=Integer.valueOf(hostages.get(i+2));
	    	int distancetohostage=Math.abs(neoX-hostagex)+Math.abs(neoY-hostagey); // here we calculate the distance to the hostage
	  
	    	for (int j = 0; j < startpads.length; j=j+2) { // here we check for pads to see if it is nearer to use one or just go directly to the hostage
	    		int xpad=Integer.parseInt(startpads[j]);
	    		int ypad=Integer.parseInt(startpads[j+1]);
	    	    int distancetopad=Math.abs(neoX-xpad)+Math.abs(neoY-ypad)+1; //here we calculate the distance to the pad and  +1 is for the action of flying
	    	    if(distancetopad<distancetohostage) {
	    	    	int xpadfinish=Integer.parseInt(finishpads[j]);
	    	    	int ypadfinish=Integer.parseInt(finishpads[j+1]);
	    	    	int distancetohostagefrompad=Math.abs(hostagex-xpadfinish)+Math.abs(hostagey-ypadfinish);
	    	    	int totaldistancefrompad=distancetopad+distancetohostagefrompad; // this is the total distance if we went with the pad to the hostage
	    	    	distancetohostage=Math.min(totaldistancefrompad, distancetohostage); // if the distance to the hostage directly is less than the distance to the pad then weill take the one with less distance 

	    	    }
	    	  
				
			}
	    	
	    
	    
	    	 int hostagetophonebooth=Math.abs(hostagex-phoneX)+Math.abs(hostagey-phoneY); // distance from the hostage to phonebooth directly
	   	    for (int j = 0; j < startpads.length; j=j+2) {
	       		int xpad=Integer.parseInt(startpads[j]);
	       		int ypad=Integer.parseInt(startpads[j+1]);
	       	    int distancetopad=Math.abs(hostagex-xpad)+Math.abs(hostagey-ypad)+1; // distance from the hostage to the pad and the +1 is for the jump action
	       	    if(distancetopad<hostagetophonebooth) { // check if the distance to the phonebooth directly is less than the distance to the pad so if this is true we will disregard this pad 
	       	    	int xpadfinish=Integer.parseInt(finishpads[j]);
	       	    	int ypadfinish=Integer.parseInt(finishpads[j+1]);
	       	    	int distancetoboothfrompad=Math.abs(phoneX-xpadfinish)+Math.abs(phoneY-ypadfinish); // check the total distance to the pad + from the pad to the phonebooth
	       	    	int totaldistancefrompad=distancetopad+distancetoboothfrompad;
	       	         hostagetophonebooth=Math.min(hostagetophonebooth, totaldistancefrompad); // choose the minimum distance

	       	    }
	   	    }
		   	    int totaldistance=distancetohostage+hostagetophonebooth; // total distance is the minimum distance needed to go from neo to the hostage and from the hostage to the phonebooth

	       	    
	       	    if(hostagedamage>=maximumdamageforhostage ) {// check if the current hostage damage is higher than the damages of the hostages beofre
	       	    	int enflicteddamage=totaldistance*2; // damage enfliced to the hostage on the way
	       	    	if(hostagedamage+enflicteddamage<100) { // check if the hostage will live
	       	    		maximumdamageforhostage=hostagedamage; // this hostage will be our target since now we can reach it and save it and it has higher damage
	       	    	    totaldistancetohostage=totaldistance; // this will be our current distance
	       	    	}
	       	    	
	       	    }
	       	  
	   			
	   		}
	   	    
	   	    
	   	    

	   		
	   	
   	    return totaldistancetohostage*20; // x20 since every action have a cost of 20 so every movement will cost 20

	 
	
	    }

		
	
	
	
	public int calculateHeuristic2(Node node) {
		String[][] state=node.translateState();
		int neoX=Integer.valueOf(state[0][0]);
		int neoY=Integer.valueOf(state[0][1]);
		int neodamage=Integer.valueOf(state[0][2]);
		int carrylimit=Integer.valueOf(state[0][3]);
		int phoneX=Integer.parseInt(state[1][0]);
		int phoneY=Integer.parseInt(state[1][1]);
		ArrayList<String> agents=new ArrayList<String>(Arrays.asList(state[2]));
		ArrayList<String> pills=new ArrayList<String>(Arrays.asList(state[3]));
		String[] startpads=state[4];
		String[] finishpads=state[5];		
		ArrayList<String> hostages=new ArrayList<String>(Arrays.asList(state[6]));
	    int mindistance=Integer.MAX_VALUE; 
	    int nearesthostagex=0;
	    int nearesthostagey=0;
	    for (int i = 0; i < hostages.size(); i=i+3) {
	    	int distancetohostage=Math.abs(neoX-Integer.valueOf(hostages.get(i)))+Math.abs(neoY-Integer.valueOf(hostages.get(i+1))); // calculate the distance to the hostage
	  
	    	for (int j = 0; j < startpads.length; j=j+2) {
	    		int xpad=Integer.parseInt(startpads[j]);
	    		int ypad=Integer.parseInt(startpads[j+1]);
	    	    int distancetopad=Math.abs(neoX-xpad)+Math.abs(neoY-ypad); // calculates the distane to the pad from neo position
	    	    if(distancetopad<distancetohostage) { // if the distance to the pad is higher than the distance to the hostage directly then we will disregard this pad
	    	    	int xpadfinish=Integer.parseInt(finishpads[j]);
	    	    	int ypadfinish=Integer.parseInt(finishpads[j+1]);
	    	    	int distancetohostagefrompad=Math.abs(Integer.valueOf(hostages.get(i))-xpadfinish)+Math.abs(Integer.valueOf(hostages.get(i+1))-ypadfinish);// we will calculate the distance from the finishpad to the hostage
	    	    	int totaldistancefrompad=distancetopad+distancetohostagefrompad; // total distance if neo went to the hostage using pads
	    	    	distancetohostage=Math.min(totaldistancefrompad, distancetohostage); // check if the neo going directly will have less distance than taking any of the pads

	    	    }
	    	  
				
			}
	    	 if(mindistance>distancetohostage) { // check if this is the nearest hostage
	    		   mindistance=distancetohostage;
	    		   nearesthostagex=Integer.valueOf(hostages.get(i));
	    		   nearesthostagey=Integer.valueOf(hostages.get(i+1));
	    		   
	    	   }
	    	 }
	    
	    
	    
	    if(hostages.size()==0) { // if there aren o hostages then the heurstic cost will be 0
	    	return 0;
	    }
	    
	    int hostagetophonebooth=Math.abs(nearesthostagex-phoneX)+Math.abs(nearesthostagey-phoneY); // distance to phonebooth directly
	    for (int j = 0; j < startpads.length; j=j+2) {
    		int xpad=Integer.parseInt(startpads[j]);
    		int ypad=Integer.parseInt(startpads[j+1]);
    	    int distancetopad=Math.abs(nearesthostagex-xpad)+Math.abs(nearesthostagex-ypad); //added +1 for the fly action
    	    if(distancetopad<hostagetophonebooth) {
    	    	int xpadfinish=Integer.parseInt(finishpads[j]);
    	    	int ypadfinish=Integer.parseInt(finishpads[j+1]);
    	    	int distancetoboothfrompad=Math.abs(phoneX-xpadfinish)+Math.abs(phoneY-ypadfinish);
    	    	int totaldistancefrompad=distancetopad+distancetoboothfrompad;
    	         hostagetophonebooth=Math.min(hostagetophonebooth, totaldistancefrompad);

    	    }
    	  
			
		}
	    
	    int totaldistance=mindistance+hostagetophonebooth;
	    
	    
	    return (totaldistance)*20;// x20 since every action have a cost of 20 so every movement will cost 20


		
	}
	

	public boolean goalTest(Node node) { // returns true if the node passed the goal test
		String[][] state=node.translateState();
		Integer neoX=Integer.parseInt(state[0][0]);
		Integer neoY=Integer.parseInt(state[0][1]);
		Integer neoDamage=Integer.parseInt(state[0][2]);
		Integer phoneX=Integer.parseInt(state[1][0]);
		Integer phoneY=Integer.parseInt(state[1][1]);
		String[] hostages=state[6];
		String[] MutatedHostages=state[7];
		String[] CarriedHostages=state[8];
		if(neoX.equals(phoneX) && neoY.equals(phoneY) && neoDamage!=100 && MutatedHostages.length==0 && CarriedHostages.length==0 && hostages.length==0 ){ // the goal is that there is no more hostages or mutated hostaged and neo is alive and at the phonebooth
			return true;
		}else {
			return false;
		}		
	}
	
	public String getPath(Node node) { // returns the actions that led to this input node in a string format
		String[][] translatedstate=node.translateState();
		String deaths=translatedstate[12][0]; // total deaths in this node
		String kills=translatedstate[9][0]; // total kills in this node
		ArrayList<String> actions=new ArrayList<String>();
		actions.add(0, node.getAction()); // since we are backtracking so the path we are getting is reversed so by putting it infront of the array it will be corrected
		Node parent=node.getParent();
		ArrayList<Node> allnodes=new ArrayList<Node>();
		allnodes.add(node);
		while(parent!=null && parent.getAction()!=null) { // this is the root node so we will stop there
			allnodes.add(0, parent);
			actions.add(0,parent.getAction());
			parent=parent.getParent();
		}
		if(Visualize) {
			for (int i = 0; i < allnodes.size(); i++) {
				visualizeGrid(allnodes.get(i));

			}
		}
		String path= String.join(",", actions);
		path=path+";"+deaths+";"+kills;
		return path;
		
		
		
		
	}
	public static ArrayList<ArrayList<String>> healhostages(ArrayList<String> hostages,ArrayList<String> carriedhostages,ArrayList<String> pills, int neoX,int neoY){     // this function decreases the damage of the hostaged and carriedhostages and returns the new hostages damage in 2d array
	    ArrayList<ArrayList<String>> results=new ArrayList<ArrayList<String>>();
	    for (int i = 0; i < pills.size(); i=i+2) {
			int pillx=Integer.parseInt(pills.get(i));
			int pilly=Integer.parseInt(pills.get(i+1));
			if(pillx==neoX && pilly==neoY) { // if this is the pill that neo got then we will remove it 
				pills.remove(i);
				pills.remove(i);			
			}
		}
	    
	    for (int i = 0; i < hostages.size(); i=i+3) { // heal all hostages 
			int damage=Integer.valueOf(hostages.get(i+2));
			if(damage<=20) {
				damage=0;
			
			}else {
				damage=damage-20;
			}
			
			hostages.set(i+2, ""+damage);
		}
	    for (int i = 0; i < carriedhostages.size(); i=i+3) { // heal all carried hostages except the dead ones
	  		int damage=Integer.valueOf(carriedhostages.get(i+2));
	  		if(damage==100) {
	  			continue;
	  		}
	  		if(damage<=20) {
				damage=0;
			
			}else {
				damage=damage-20;
			}
	  		carriedhostages.set(i+2, ""+damage);
	  	}
	    
	    results.add(pills);
	    results.add(hostages);
	    results.add(carriedhostages);
	    return results;      // return the new hostages,carriedhostages and pills
			
			
		}

	
	public  Node applyaction(Node node,String action) { // this function takes the actio and applies it to the node
		String[][] state=node.translateState();
		int neoX=Integer.valueOf(state[0][0]);
		int neoY=Integer.valueOf(state[0][1]);
		int neodamage=Integer.valueOf(state[0][2]);
		int carrylimit=Integer.valueOf(state[0][3]);
		String phoneX=state[1][0];
		String phoneY=state[1][1];
		ArrayList<String> agents=new ArrayList<String>(Arrays.asList(state[2]));
		ArrayList<String> pills=new ArrayList<String>(Arrays.asList(state[3]));
		String[] startpads=state[4];
		String[] finishpads=state[5];		
		ArrayList<String> hostages=new ArrayList<String>(Arrays.asList(state[6]));
		ArrayList<String> mutatedhostages=new ArrayList<String>(Arrays.asList(state[7]));
		ArrayList<String> carriedhostages=new ArrayList<String>(Arrays.asList(state[8]));
		int kills=Integer.valueOf(state[9][0]);
		int saves=Integer.valueOf(state[10][0]);
		int numberofhostagecarried=Integer.valueOf(state[11][0]);
		int deaths=Integer.valueOf(state[12][0]);
	    int width=Integer.valueOf(state[13][0]);
	    int height=Integer.valueOf(state[13][1]);
	    int pathcost=0;
		String[] newhostages;
		ArrayList<ArrayList<String>> updatedhostages=new ArrayList<ArrayList<String>>();
		switch(action) {
		case "left":
			pathcost=pathcost+20;
			neoY--;
			updatedhostages=damageHostages(hostages, mutatedhostages, carriedhostages, deaths); // increase hostages damage
			deaths=Integer.valueOf(updatedhostages.get(3).get(0)); // check the number of deaths after hostage damage
			hostages=updatedhostages.get(0); // new hostages
			mutatedhostages=updatedhostages.get(1); // new mutated hostages
			carriedhostages=updatedhostages.get(2); // new carried hostages 
			break;
		case "right":
			pathcost=pathcost+20;
			neoY++;
			updatedhostages=damageHostages(hostages, mutatedhostages, carriedhostages, deaths); // increase hostage damage
			deaths=Integer.valueOf(updatedhostages.get(3).get(0)); // check the nummber of deaths after hostage damagee
			hostages=updatedhostages.get(0); // new hostages
			mutatedhostages=updatedhostages.get(1); // new mutated hostages
			carriedhostages=updatedhostages.get(2); // new carried hostages 
			break;
		case "up":
			pathcost=pathcost+20;
			neoX--;
			updatedhostages=damageHostages(hostages, mutatedhostages, carriedhostages, deaths);
			deaths=Integer.valueOf(updatedhostages.get(3).get(0));
			hostages=updatedhostages.get(0); // new hostages
			mutatedhostages=updatedhostages.get(1); // new mutated hostages
			carriedhostages=updatedhostages.get(2); // new carried hostages 		
			break;
		case "down":
			pathcost=pathcost+20;
			neoX++;
			updatedhostages=damageHostages(hostages, mutatedhostages, carriedhostages, deaths);
			deaths=Integer.valueOf(updatedhostages.get(3).get(0));
			hostages=updatedhostages.get(0); // new hostages
			mutatedhostages=updatedhostages.get(1); // new mutated hostages
			carriedhostages=updatedhostages.get(2); // new carried hostages 
			break;
			
		case "kill":
			if(neodamage>=80) {
				neodamage=100;
			}else {
			neodamage=neodamage+20;
			}
			ArrayList<ArrayList<String>> killresult=killAgents(agents, mutatedhostages, neoX, neoY, kills);
			 agents=killresult.get(0);
			 mutatedhostages=killresult.get(1);
			 int mutatedkills=Integer.parseInt(killresult.get(2).get(0));
			 int agentkills=Integer.parseInt(killresult.get(2).get(1));
			 kills=kills+agentkills+mutatedkills;
			 pathcost=pathcost+(mutatedkills*20000)+(agentkills*200000); // killing a mutated agent will have a cost of 20,000 while killing an agent will have a cost of 200,000
			 updatedhostages=damageHostages(hostages, mutatedhostages, carriedhostages, deaths);
			 deaths=Integer.valueOf(updatedhostages.get(3).get(0));
			 hostages=updatedhostages.get(0); // new hostages
			 mutatedhostages=updatedhostages.get(1); // new mutated hostages
			 carriedhostages=updatedhostages.get(2); // new carried hostages 
			   break;
		case "fly":
			pathcost=pathcost+20;
			updatedhostages=damageHostages(hostages, mutatedhostages, carriedhostages, deaths);
			deaths=Integer.valueOf(updatedhostages.get(3).get(0));
			hostages=updatedhostages.get(0); // new hostages
			mutatedhostages=updatedhostages.get(1); // new mutated hostages
			carriedhostages=updatedhostages.get(2); // new carried hostages 
			for (int i = 0; i < startpads.length; i=i+2) {
				int padx=Integer.valueOf(startpads[i]);
				int pady=Integer.valueOf(startpads[i+1]);
				if(neoX==padx&& neoY==pady) {
					neoX=Integer.valueOf(finishpads[i]);
					neoY=Integer.valueOf(finishpads[i+1]);
					break;

				}
			}
			break;
			
		case "takePill":
			pathcost=pathcost+20;
			if(neodamage<=20) {
				neodamage=0;
			}else {
				neodamage=neodamage-20;
			}
			ArrayList<ArrayList<String>> pillresults=healhostages(hostages, carriedhostages, pills, neoX, neoY);//heal the hostages
			pills=pillresults.get(0);
			hostages=pillresults.get(1); // new hostages
			carriedhostages=pillresults.get(2); // new carried hostages
			
			break;
		case "carry":
			pathcost=pathcost+20;
			numberofhostagecarried++;
			for (int i = 0; i < hostages.size(); i=i+3) {
				int hostagex=Integer.valueOf(hostages.get(i));
				int hostagey=Integer.valueOf(hostages.get(i+1));
				int hostagedamage=Integer.valueOf(hostages.get(i+2));
				if(neoX==hostagex && neoY==hostagey) { // carry the hostage that is in the same cell as neo
					hostages.remove(i);
					hostages.remove(i);
					hostages.remove(i); // remove the hostage from the hostages array
					i=i-3;
					carriedhostages.add(""+hostagex);
					carriedhostages.add(""+hostagey);
					carriedhostages.add(""+hostagedamage); // add the hostage to the carriedhostages array
					break;
				}
			}
			
			updatedhostages=damageHostages(hostages, mutatedhostages, carriedhostages, deaths); // increase the hostages damage
			deaths=Integer.valueOf(updatedhostages.get(3).get(0)); //check the number of deaths after the hostages damage
			hostages=updatedhostages.get(0); // new hostages
			mutatedhostages=updatedhostages.get(1); // new mutated hostages
			carriedhostages=updatedhostages.get(2); // new carried hostages 
			
			break;
		case "drop":
			pathcost=pathcost+20;
			for (int i = 0; i < carriedhostages.size(); i=i+3) { // drop all carried hostages
				int hostagedamage=Integer.parseInt(carriedhostages.get(i+2));
				if(hostagedamage!=100) { // if the hostage is dead then we will not consider him saved
					saves++;
				}
			}
			carriedhostages=new ArrayList<String>();
			updatedhostages=damageHostages(hostages, mutatedhostages, carriedhostages, deaths);
			deaths=Integer.valueOf(updatedhostages.get(3).get(0));
			hostages=updatedhostages.get(0); // new hostages
			mutatedhostages=updatedhostages.get(1); // new mutated hostages
			carriedhostages=updatedhostages.get(2); // new carried hostages 		
			numberofhostagecarried=0; // initalize the hostage carried number
			break;

			
			
			
		}
		String allpads="";
		for (int i = 0; i < startpads.length; i=i+2) { // here we put again the pads to return it as a state
			if(allpads=="") { // check if we start with a comma or not so if this is the start of the string then we will not put a comma
				allpads=startpads[i]+","+startpads[i+1]+","+finishpads[i]+","+finishpads[i+1];
			}else {
			allpads=allpads+","+startpads[i]+","+startpads[i+1]+","+finishpads[i]+","+finishpads[i+1];

			}			
		}
		
		pathcost=node.getPathcost()+pathcost; // parent path cost + current node cost

		String result=""+width+","+height+";"+carrylimit+";";
		result=result+neoX+","+neoY+","+neodamage+";"+phoneX+","+phoneY+";"+String.join(",", agents)+";"+String.join(",", pills)+";"+allpads;
		result=result+";"+String.join(",", hostages)+";"+String.join(",", mutatedhostages)+";"+String.join(",", carriedhostages)+";"+kills+";"+saves+";"+numberofhostagecarried+";"+deaths;
	
		int depth=node.getDepth();
		Node newnode=new Node(result,node,action,depth+1,pathcost,deaths); // initalize the new node

		return newnode;	
	}

	public static ArrayList<ArrayList<String>> damageHostages(ArrayList<String> hostages,ArrayList<String> mutatedhostages,ArrayList<String> carriedhostages,int currentdeaths) {
		ArrayList<String> updatedhostages=new ArrayList<String>();
		ArrayList<String> updatedcarriedhostages=new ArrayList<String>();
		int deaths=0;
		ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
		for (int i = 0; i < hostages.size(); i=i+3) {
			String hostagex=hostages.get(i);
			String hostagey=hostages.get(i+1);
			int hostagedamage=Integer.parseInt(hostages.get(i+2));
			if(hostagedamage>=98) {
				deaths++;
				mutatedhostages.add(hostagex);
				mutatedhostages.add(hostagey);
			}else {
				updatedhostages.add(hostagex);
				updatedhostages.add(hostagey);
				updatedhostages.add(""+(hostagedamage+2));
			}
		}
		for (int i = 0; i < carriedhostages.size(); i=i+3) {
			String hostagex=carriedhostages.get(i);
			String hostagey=carriedhostages.get(i+1);
			int hostagedamage=Integer.parseInt(carriedhostages.get(i+2));
			if(hostagedamage>=98 ) {
				carriedhostages.set(i+2, ""+100);
				if(hostagedamage!=100)
				deaths++;
				
			}else {
				carriedhostages.set(i+2, ""+(hostagedamage+2));
			}
		}
		result.add(updatedhostages);
		result.add(mutatedhostages);
		result.add(carriedhostages);
		ArrayList<String> alldeaths=new ArrayList<String>();
		alldeaths.add(""+(deaths+currentdeaths));
		result.add(alldeaths);
		return result;  // this will return the new hostages,mutated hostages,carriedhostages and deaths in arraylists respectavily 
		
		}
	
	
	
	public String[] AllowedActions(Node node) { // returns an array of the legal actions that can be taken from this node
		String lastAction=node.getAction();
		if(lastAction==null) {
			lastAction="";
		}
		String[][] state=node.translateState();
		String neoX=state[0][0];
		String neoY=state[0][1];
		int neodamage=Integer.valueOf(state[0][2]);
		int carrylimit=Integer.valueOf(state[0][3]);
		String phoneX=state[1][0];
		String phoneY=state[1][1];
		String[] agents=state[2];
		String[] pills=state[3];
		String[] pads=state[4];
		String[] hostages=state[6];
		String[] mutatedhostages=state[7];
		String[] carriedhostages=state[8];
		int numberofhostagecarried=Integer.valueOf(state[11][0]);
		String actions="";
	    String width=state[13][0];
	    String height=state[13][1];

		boolean onposition=false; // a boolean if something was in the same place as neo we wont need to keep tracking the rest of the possiblities
		int onHostage=-1;
		if(neodamage==100) {
			return new String[] {};   // return an empty array if neo is dead so no actions can be taken
		}
		if(!lastAction.equals("right")) { // check if the last action was the opposite of this action so we can prevent repeated states
		 if(leftIsValid(hostages, agents, mutatedhostages, neoX, neoY)) { //check if we can go left
			 actions="left";
		 }
		}
		 if(!lastAction.equals("up")) {
			 if(downIsValid(hostages, agents, mutatedhostages, neoX, neoY,width,height)) {
				 if(actions!="") {
					 actions=actions+","; //put a comma to seperate actions
				 }
				 actions=actions+"down";
			 }
		 }
		 if(neoX.equals(phoneX) && neoY.equals(phoneY)) {
			 onposition=true;
			 if(carriedhostages.length!=0) {
				 if(actions!="") {
					 actions=actions+","; //put a comma to seperate actions
				 }
				 actions=actions+"drop";
			 } 
			 }
		 
		 
		 if(!lastAction.equals("fly") && !onposition) {
			 if(onPad(pads, neoX, neoY)) {
				 onposition=true; // this means we found something in the same place as neo so there wont be anything else in the same place so we wont check for pills or hostages
				 if(actions!="") {
					 actions=actions+","; //put a comma to seperate actions
				 }
				 actions=actions+"fly";
			 }
		 }
		 if(!onposition) { // check if we already found something else in this position before we waste time searching for hostages
		 onHostage=onHostage(hostages, neoX, neoY);
		 }
		 if(onHostage!=-1) {
			 onposition=true;
		 }
		 if(carrylimit>numberofhostagecarried && onHostage!=-1) {
			 if(actions!="") {
				 actions=actions+","; //put a comma to seperate actions
			 }
			 actions=actions+"carry";
		 }
		 
		 if(onHostage<=97 && canKill(agents, mutatedhostages, neoX, neoY)) {
			 if(actions!="") {
				 actions=actions+","; //put a comma to seperate actions
			 }
			 actions=actions+"kill";
		 }
		 
		 if(!onposition && onPill(pills, neoX, neoY)) {
			 if(actions!="") {
				 actions=actions+","; //put a comma to seperate actions
			 }
			 actions=actions+"takePill";
		 }
		 if(!lastAction.equals("left")&&rightIsValid(hostages, agents, mutatedhostages, neoX, neoY,width,height)) {
			 if(actions!="") {
				 actions=actions+","; //put a comma to seperate actions
			 }
			 actions=actions+"right"; 
		 }
		 if(!lastAction.equals("down") && upIsValid(hostages, agents, mutatedhostages, neoX, neoY,width,height)) {
			 if(actions!="") {
				 actions=actions+","; //put a comma to seperate actions
			 }
			 actions=actions+"up"; 
		 }
		 
		 String[] result=actions.split(",");
		 return result;
		 
		 
		 
		 
		 
		}
  	
	public static boolean canKill(String[] agents,String[] mutatedhostages,String neoX,String neoY) { // check for near agents that neo could kill if there is then this function will return true
		int neox=Integer.valueOf(neoX);
		int neoy=Integer.valueOf(neoY);
		for (int i = 0; i < agents.length; i=i+2) {
			int agentX=Integer.valueOf(agents[i]);
			int agentY=Integer.valueOf(agents[i+1]);
			if((Math.abs(neox-agentX)<=1 && agentY==neoy)|| (Math.abs(neoy-agentY)<=1 && agentX==neox)) {
				return true;
			}
		}
		for (int i = 0; i < mutatedhostages.length; i=i+2) {
			int mutatedhostageX=Integer.valueOf(mutatedhostages[i]);
			int mutatedhostageY=Integer.valueOf(mutatedhostages[i+1]);
			if((Math.abs(neox-mutatedhostageX)<=1 && mutatedhostageY==neoy)|| (Math.abs(neoy-mutatedhostageY)<=1 && mutatedhostageX==neox)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean onPill(String [] pills,String neoX,String neoY) {
		
		for (int i = 0; i < pills.length; i=i+2) {
		
			
			if(pills[i].equals(neoX) && pills[i+1].equals(neoY)) {
				return true; 
			}
		}
	return false;	
	}
	
	public static int onHostage(String [] hostages,String neoX,String neoY) { // if there is no hostage it will return an -1 if there is one it will return its damage
		int result=-1;
		for (int i = 0; i < hostages.length; i=i+3) {
			if(hostages[i].equals(neoX) && hostages[i+1].equals(neoY)) {
				result=Integer.valueOf(hostages[i+2]);                        // if there is a hostage we will return its damage to know if it will turn to an agent in the next cycle so we can deny the action kill in this case
			    return result;
			}
		}
	return result;	
	}
	
	public static boolean onPad(String [] pads,String neoX,String neoY) { // check if neo is on a pad and reutrn true if he is
		
		for (int i = 0; i < pads.length; i=i+2) {
			if(pads[i].equals(neoX) && pads[i+1].equals(neoY)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean upIsValid(String[] hostages,String[] agents,String[] mutatedhostages,String neoX,String neoY,String width,String height) { // check if neo can move up
		if(0==Integer.valueOf(neoX))
			return false;
		for (int i = 0; i < mutatedhostages.length; i=i+2) { // check for mutatedhostages
			if(Integer.valueOf(mutatedhostages[i])+1==Integer.valueOf(neoX) && mutatedhostages[i+1].equals(neoY)) {
				return false;
			}
		}
		for (int i = 0; i < agents.length; i=i+2) {// check for agents
			if(Integer.valueOf(agents[i])+1==Integer.valueOf(neoX) && agents[i+1].equals(neoY)) {
				return false;
			}
		}
		
		for (int i = 0; i < hostages.length; i=i+3) { 
			if(Integer.valueOf(hostages[i])+1==Integer.valueOf(neoX) && hostages[i+1].equals(neoY) && Integer.valueOf(hostages[i+2])>97) { //checks if there is a hostage above that has damage more than 97 so it wont turn into an agent the next cycle
				return false;
			}
		}
		return true;
		
	}
	
	public static boolean downIsValid(String[] hostages,String[] agents,String[] mutatedhostages,String neoX,String neoY,String width,String height) {
		if(Integer.valueOf(width)-1==Integer.valueOf(neoX))
			return false;
		for (int i = 0; i < mutatedhostages.length; i=i+2) {
			if(Integer.valueOf(mutatedhostages[i])-1==Integer.valueOf(neoX) && mutatedhostages[i+1].equals(neoY)) {
				return false;
			}
		}
		for (int i = 0; i < agents.length; i=i+2) {
			if(Integer.valueOf(agents[i])-1==Integer.valueOf(neoX) && agents[i+1].equals(neoY)) {
				return false;
			}
		}
		for (int i = 0; i < hostages.length; i=i+3) {
			
			if(Integer.valueOf(hostages[i])-1==Integer.valueOf(neoX) && hostages[i+1].equals(neoY) && Integer.valueOf(hostages[i+2])>97) { //checks if there is a hostage above that has damage more than 97 so it wont turn into an agent the next cycle
				return false;
			}
		}
		return true;
		
	}
	
	public static boolean leftIsValid(String[] hostages,String[] agents,String[] mutatedhostages,String neoX,String neoY) {
		if(0==Integer.valueOf(neoY)) {
          return false;
		}
		for (int i = 0; i < mutatedhostages.length; i=i+2) {
			if(mutatedhostages[i].equals(neoX) && Integer.valueOf(mutatedhostages[i+1])==Integer.valueOf(neoY)-1) {
				return false;
			}
		}
		for (int i = 0; i < agents.length; i=i+2) {
			if(agents[i].equals(neoX) && Integer.valueOf(agents[i+1])==Integer.valueOf(neoY)-1) {
				return false;
			}
		}
		
		for (int i = 0; i < hostages.length; i=i+3) {
			if(hostages[i].equals(neoX) && Integer.valueOf(hostages[i+1])==Integer.valueOf(neoY)-1 && Integer.valueOf(hostages[i+2])>97) {
				return false;
			}
		}
		return true;
		
	}
	
	
	public static boolean rightIsValid(String[] hostages,String[] agents,String[] mutatedhostages,String neoX,String neoY,String width,String height) {
		if(Integer.valueOf(width)-1==Integer.valueOf(neoY)) {
			return false;
		}
		for (int i = 0; i < mutatedhostages.length; i=i+2) {
			if(mutatedhostages[i].equals(neoX) && Integer.valueOf(mutatedhostages[i+1])==Integer.valueOf(neoY)+1) {
				return false;
			}
		}
		for (int i = 0; i < agents.length; i=i+2) {
			if(agents[i].equals(neoX) && Integer.valueOf(agents[i+1])==Integer.valueOf(neoY)+1) {
				return false;
			}
		}
		
		for (int i = 0; i < hostages.length; i=i+3) {
			if(hostages[i].equals(neoX) && Integer.valueOf(hostages[i+1])==Integer.valueOf(neoY)+1 && Integer.valueOf(hostages[i+2])>97) {
				return false;
			}
		}
		return true;
		
	}
	
	
	public static String[][] translateState(String state){
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


		String [][] output = {neo,telephone,agent,pills,padsStart,padsFinish,hostage,mutatedHostage,carriedHostage,numberOfKills,numberOfHostagesSaved,numberOfHostagesCarried,deaths,dimensions };
		
		
		return output;     
			   
			
		
		
		
		
			
		}

	
	
			
	
public static void main(String[] args) {
	String grid7="5,5;3;1,3;4,0;0,1,3,2,4,3,2,4,0,4;3,4,3,0,4,2;1,4,1,2,1,2,1,4,0,3,1,0,1,0,0,3;4,4,45,3,3,12,0,2,88";
	String projexample="5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";
	String grid0="5,5;2;3,4;1,2;0,3,1,4;2,3;4,4,0,2,0,2,4,4;2,2,91,2,4,62";
	String grid3="5,5;1;0,4;4,4;0,3,1,4,2,1,3,0,4,1;4,0;2,4,3,4,3,4,2,4;0,2,98,1,2,98,2,2,98,3,2,98,4,2,98,2,0,1";
	String grid10="5,5;4;1,1;4,1;2,4,0,4,3,2,3,0,4,2,0,1,1,3,2,1;4,0,4,4,1,0;2,0,0,2,0,2,2,0,2;0,0,62,4,3,45,3,3,39,2,3,40";
	String grid5="5,5;2;0,4;3,4;3,1,1,1;2,3;3,0,0,1,0,1,3,0;4,2,54,4,0,85,1,0,43";

	String gridtest=Matrix.genGrid();
	System.out.println(gridtest);
	System.out.println(Arrays.deepToString(initalizeNode(gridtest).translateState()));




	

}


}
