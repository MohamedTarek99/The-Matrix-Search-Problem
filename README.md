# AI Project 1 Report

```
Team 50:
Mohamed Tarek Ahmed/43- 2156
Adham Gamal/43- 8732
Omar Ashraf/43- 3387
Mohamed Ayman Mohamed/43- 12715

## 1 - Implementation of our Node class:

We implemented two types of nodes

**- SearchNode**

The abstract searchnode consists of 7 variables which are

```
 state(String) : a variable which we keep the state of the
node in which consists of all the information about the
state that we need like deaths,kills,mutatedhostages and
more
 action(String): a variable that we store the action that
was done on this node that led it to it’s state so we can
know the path that reached the goalnode by backtracking
the parent of each node
 parent(Node): a variable which stores the parent node of
the current node
 pathcost(int) : a variable which stores the path cost of the
node
 heuristic1(Boolean) :a variable which if true then it means
that we will be comparing between nodes in the
compareTo function according to their heuristic costs not
```

```
with the pathcost we will only set it with true when we
are using the greedy search
 depth(int) : a variable that stores the depth of the node so
we can know the depth in each node in the iterative
depth search
The variables the has setters are action,heuristic,heuristic
and depth
The variable that has getters are action, heuristic, parent,
state and depth
```
**- Node**

The node class inherits the abstract searchnode class and
consists of 7 variable

```
 state(String) : a variable which we keep the state of the
node in which consists of all the information about the
state that we need like deaths,kills,mutatedhostages and
more
 action(String): a variable attribute that we keep the
action that was done on this node that led it to it’s state
so we can know the path that reached the goalnode by
backtracking the parent of each node
 parent(Node): a variable which stores the parent node of
the current node
 pathcost(int) : a variable which stores the path cost of the
node
```

```
 heuristic1(Boolean) :a variable which if true then it means
that we will be comparing between nodes in the
compareTo function according to their heuristic costs not
with the pathcost we will only set it with true when we
are using the greedy search
 depth(int) : a variable that stores the depth of the node so
we can know the depth in each node in the iterative
depth search
 death(int) : a variable that stores the number of deaths
that is in the state of this node.
```
The variables the has setters are action,heuristic,heuristic1 and
depth

The variable that has getters are action, death, heuristic,
parent, state and depth

It also has a function translatenode() which translate the state
of the node which is a string to 2d array to ease the actions that
we can do to the state.The output of this function is [

Neo,phonebooth,agents,pills,padsstart,padsfinish,hostages,mut
atedhostages,carriedhostages,numberofkills,numberofhostages
saved,numberofhostagescarried,deaths,dimensions

]


Each one of them is an array that holds information about the
name of it for example the Neo array holds the position x
,position ,damage and maximumcarrysize of neo and so on.

Also the is a compareTo(Node o) function that first check if the
Boolean variable heuristic1 is true then it will only compare the
nodes by their heuristic variable and if it is false it will compare
the death of the two nodes first and prioritize the one with the
less deaths if they are equal it will prioritize the one with less
pathcost.

So to clarify further:

**Greedy:** If the searchproblem is greedy then while creating the
node we set the heuristic1 variable to true so in the compareTo
function it will compare the nodes by their heuristic cost

**Uniform:** If the search problem is uniform cost then we will
compare first the deaths and prioritize the one with the less
deaths if they are equal then we will compare the
pathcost+heurstic(which will always be 0 in this case since we
will only calculate it and set the heuristic variable in the a star
and the greedy search) and prioritize the one with less
pathcost(since we are technically comparing only the pathcost
since the heuristic will always be 0 in the uniform case as we
said above)

**Astar:** if the search problem is Astar we will compare the deaths
first and prioritize the node with the less deaths and if they are
equal we will compare the pathcost+heuristic (now the


heuristic will not always be 0 since we will calculate the
heuristic and set the heuristic variable in the node) and
prioritize the one with the smaller result

**The rest of the searches:** We wont be using the compareTo in
them since we only use a priority queue in the astar,greedy and
uniform searches.

## 2 - Implementation of our Matrix problem

First we created the SearchProblem which is an abstract class

then we created the Matrix class which inherits the

SearchProblem class

**- SearchProblem**

The SearchProblem is an abstract class has only one variable


```
 repeatedStates(Hashmap) :all the expanded nodes states
are put in the hashmap to avoid expanding repeated
states
```
Moreover it has these **ABSTRACT** functions

```
 getInitialNode(String grid): This function takes the grid and
create the initial Node which has the initial state.
 AllowedActions(Node node): This function takes the node
and returns an array with the legal actions that can be
taken from this node
 applyaction(Node node,String action): This function takes
the node and the action that will be taken on this node
and returns a new node after the action has been taken
 goalTest(Node node): This function takes a node and
returns a boolean. True if the node reached the goal test
and false otherwise.
 getPath(Node node): This function takes the node that
reached the goal state and keeps backtracking the parent
nodes and also it keeps printing the grid in every action if
the visualize Boolean is true.(visualize is an attribute in the
matrix class that becomes true when the function solve is
called and the visualize input is true in the call)
 calculateHeurstic1(Node node): This function takes a node
and returns the first heuristic cost of the node
 calculateHeurstic2(Node node): This function takes a node
and returns the second heuristic cost of the node
```
And there is one static general search method


```
 Search(SearchProblem problem,QingFun
Queuingfunction): This function takes the problem and
the type of the queuingfunction which is an ENUM and
then returns a string a solution containing the path to the
goal or “No Solution”(if no solution was found)
```
- **Matrix**

Matrix is a class that inherits the searchproblem abstract class
and it has 2 variables

```
 Initialstate(Node): this variable contains the initial node of
the matrix problem
 Visualize(Boolean): this variable is true when the solve
function is called and the Boolean of the visualize is true
```
This class has many helpers functions we will cover them here
and the main functions will be covered in the next section

##  getInitialNode(String grid): This function takes the grid and

## create the initial Node which has the initial state.

##  IsOccupied(int x,int y , String[][] grid): This functions takes

```
the position x,position y and the grid and check if this
position is occupied by returning true if the position is null
in the grid and false otherwise. This function is used while
```
## generating the grid to prevent overlapping

##  visualizeGrid(Node node): This function takes the node

```
and then prints a 2d array representing the grid as
```

```
visualization it also print the number of deaths ,kills and
```
## the action taken in this node

##  killAgents(ArrayList<String> agents,ArrayList<String>

```
mutatedhostages,int neoX,int neoY,int kills): This function
takes the agents .mutatedhostages and removes the
agents and mutatedhostages that neo will kill and increase
the an 2darray the contains the new agents and new
mutatedagents after they were killed and also return the
agentkills and mutatedkills in this form
[[agents],[mutatedagents],[numberofagentkills],[numbero
```
## fmutatedhostageskills]]

 **genGrid():** Generates a random grid. The dimensions of the
grid, the starting position of Neo and the telephone booth,
as well as the number and locations of the hostages,
agents, pills, and pads randomly generated
 **initalizeNode(String grid):** This function takes the grid and
generates the initial state of the initial node and returns
the initial node
 **Solve(String grid,String strategy,boolean visualize):** This
function takes the grid and the strategy and the visualize
Boolean and returns a solution as a string that was
reached by the given strategy
 **CalculateHeurstic1(Node node):** This function takes the
node and returns an integer that was estimated by this
function which is the cost that is needed to carry the
nearest hostage and drop him in the Tb


 **CalculateHeurstic2(Node node):** This function takes the
node and returns an integer that was estimated by this
function which is the cost that is needed to carry the most
damaged hostage that I can save and drop him in the Tb
 **goalTest(Node node):** This function takes the node and
returns boolean true if it reached the goal test and
false otherwise
 **getPath(Node node):** This function takes the node and
returns an integer that was estimated by this function
which is the cost that is needed to carry the nearest
hostage and drop him in the Tb
 **healhostages(ArrayList<String> hostages,
ArrayList<String> carriedhostages, ArrayList<String>
pills,int neox,int neoy):** This function heals hostages and
carried hostages and this function is called when neo takes
a pill. It also removes the pill that neo is standing by and it
returns a 2d array in this format
[[newpills],[hostagesafterheal],[carriedhostagesafterheal]]
 **applyaction(Node node,String action):** This function
takes a node and returns a new node after this
action has been taken
 **damageHostages(ArrayList<String> hostages,
ArrayList<String> mutatedhostages, ArrayList<String>
carriedhostages,int currentdeaths):** This function takes
the hostages and carried hostages and increase the
damage by 2 and increases the deaths if some hostages


died and if the hostage that died is not carried it will put
this hostage in the mutatedhostages array. This function is
called in every action except takepills.it returns a 2darray
in this format [[hostagesafterdamage],[mutatedhostages],[
arriedhostagesafterdamage],[newdeaths]]
 **AllowedActions(Node node):** This function takes the node
and returns an array with the legal actions that can be
taken from this node
 **CanKill(String[] agents,String[]
mutatedagents,String neox,String neoy):** This
function takes the agents and mutatedagents and
check if any of them is near neo for neo to kill and
returns boolean true if there are
agents/mutatedagents near neo and false otherwise.
 **onPill(ArrayList<String> pills,String neox,String
neoy):** This function takes the pills and checks if neo
is standing on one and return Boolean true and false
otherwise

 **onHostage(ArrayList<String> hostages,String
neox,String neoy):** This function takes the hostages
and checks if neo is standing on one and return
Boolean true and false otherwise
 **onPad(ArrayList<String> pads,String neox,String
neoy):** This function takes the pads and checks if neo


```
is standing on one and return Boolean true and false
otherwise
```
 **upIsValid(String[] hostages,String[] agents,String[]
mutatedhostages,String neoX,String neoY):** This
function checks if the up action is valid and return
Boolean true and false otherwise
 **leftIsValid(String[] hostages,String[] agents,String[]
mutatedhostages,String neoX,String neoY):** This
function checks if the left action is valid and return
Boolean true and false otherwise

 **rightIsValid(String[] hostages,String[]
agents,String[] mutatedhostages,String neoX,String
neoY):** This function checks if the right action is valid
and return Boolean true and false otherwise
 **downIsValid(String[] hostages,String[]
agents,String[] mutatedhostages,String neoX,String
neoY):** This function checks if the down action is
valid and return Boolean true and false otherwise


## 3 - Main Functions

```
 getInitialNode(String grid): This function takes the grid and
create the initial Node which has the initial state.
 AllowedActions(Node node): This function takes the node
and checks for the actions that can be taken from the
node by calling the helper functions like
leftIsValid,upIsValid and so on. This function returns an
array with the legal actions that can be taken from this
node
 applyaction(Node node,String action): This function takes
the node and the action that will be taken on this node
and returns a new node after the action has been taken.
 goalTest(Node node): This function takes a node and
returns a boolean. True if the node reached the goal test
and false otherwise.
 getPath(Node node): This function takes the node that
reached the goal state and keeps backtracking the parent
```

nodes and also it keeps printing the grid in every action if
the visualize Boolean is true.(visualize is an attribute in the
matrix class that becomes true when the function solve is
called and the visualize input is true in the call)
 **CalculateHeurstic1(Node node):** This function takes the
node and returns an integer that was estimated by this
function which is the cost that is needed to carry the
nearest hostage and drop him in the Tb
 **CalculateHeurstic2(Node node):** This function takes the
node and returns an integer that was estimated by this
function which is the cost that is needed to carry the most
damaged hostage that I can save and drop him in the Tb
 **Search(SearchProblem problem,Qingfun
queingfunction):** This is a static function that is in
the searchproblem class which takes the problem
and an enum Qingfun and based on the enum it
returns a solution as a string .First we will get the
initialnode and insert it in the queue and then we
will make a loop which will end only if the queue is
empty or a node passed the goaltest , then we will
poll the first node in the queue we will first check if
the node reached the goaltest or not and if it didn’t
we will also check if this node is a repeated state by
checking if the state of this node is in the
repeatedStates hashmap if not then we will call the


allowedactions function which will return an array of
the legal action that can be taken then we will loop
on each action and call the applyaction function with
the action and it will return a new node and we will
insert it in the queue in a way that satisifies the
qinfun input. The qingfun enums works as the
following.
**ENQUEUE_AT_END:** for this enum we will insert the
new nodes at the end of the queue.
**ENQUEUE_AT_FRONT:** for this enum we will insert
the new nodes at the beginning of the queue.
**ENQUEUE_PATHCOST:** for this enum we will use a
priority queue and insert the node depending on its
pathcost.
**ENQUEUE_GREEDY1:** for this enum we will use a
priority queue and calculate the first heuristic cost of
the node and set the heuristic with the cost and also
set the heurstic1 boolean of the node as true so the
compareTo function will only check on the heuristic
variable of the node.
**ENQUEUE_GREEDY2:** for this enum we will use a
priority queue and calculate the second heuristic
cost of the node and set the heuristic with the cost
and also set the heurstic1 boolean of the node as


true so the compareTo function will only check on
the heuristic variable of the node.
**ENQUEUE_AT_ITERATIVE_END:** for this enum we
will loop from i to infinity and we will only expand
the nodes that that has depth <= i and insert them in
the start of the queue and if the queue is empty we
will increment i and keep repeating until we find a
goal.
**ENQUEUE_ASTAR:** for this enum we will calculate
the first heuristiccost and put the nodes in the
queue depending on their heursticost+pathcost
**ENQUEUE_ASTAR2** : for this enum we will calculate
the second heuristiccost and put the nodes in the
queue depending on their heursticost+pathcost**.**
 **solve(String grid,String strategy,boolean visualize):**
This function is a static function in the matrix class
which calls the static search function that in the
searchproblem class and returns a string of the
solution. The enum that gets passed to the search
function depends on the strategy.
BF=>ENQUEUE_AT_END,
DF=>ENQUEUE_AT_FRONT,UC=>ENQUEUE_PATHCO
ST,ID=>ENQUEUE_AT_ITERATIVE_END,GR1=>ENQUE


### UE_GREEDY1,GR2=>ENQUEUE_GREEDY2,AS1=>ENQ

```
UEUE_ASTAR1 and AS2=>ENQUEUE_ASTAR
```
## 3 - Search algorithms

##  ENQUEUE_AT_END(BFS): First we will get the

```
initialnode and insert it in the queue and then we
will make a loop which will end only if the queue is
empty or a node passed the goaltest , then we will
poll the first node in the queue we will first check if
the node reached the goaltest or not and if it didn’t
we will also check if this node is a repeated state by
checking if the state of this node is in the
repeatedStates hashmap if not then we will call the
allowedactions function which will return an array of
the legal action that can be taken then we will loop
on each action and call the applyaction function with
the action and it will return a new node and we will
```
## insert it in the front of the queue

##  ENQUEUE_AT_FRONT(DFS): First we will get the

```
initialnode and insert it in the queue and then we
will make a loop which will end only if the queue is
```

```
empty or a node passed the goaltest , then we will
poll the first node in the queue we will first check if
the node reached the goaltest or not and if it didn’t
we will also check if this node is a repeated state by
checking if the state of this node is in the
repeatedStates hashmap if not then we will call the
allowedactions function which will return an array of
the legal action that can be taken then we will loop
on each action and call the applyaction function with
the action and it will return a new node and we will
```
## insert it at the front of the queue.

##  ENQUEUE_PATHCOST(Uniform cost search): First

```
we will get the initialnode and insert it in the priority
queue and then we will make a loop which will end
only if the queue is empty or a node passed the
goaltest , then we will poll the first node in the
queue we will first check if the node reached the
goaltest or not and if it didn’t we will also check if
this node is a repeated state by checking if the state
of this node is in the repeatedStates hashmap if not
then we will call the allowedactions function which
will return an array of the legal action that can be
taken then we will loop on each action and call the
applyaction function with the action and it will
```

```
return a new node and we will insert it in the priority
queue depending on it’s cost , the lower the cost the
more priority it has. The cost of all actions are 20
except the killing a mutatedhostage is 20,000 and
killing an agent is 200,000, also we compare the
deaths before the comparing the pathcost in the
compareTo function in the node since the priority of
our optimal solution is having minimum deaths so
we first compare the deaths of each node and
prioritize the node less deaths but if the number of
deaths of each node with is equal we will then
```
## priotize the node with the lower pathcost.

 **ENQUEUE_GREEDY1(Greedy search 1):** First we will
get the initialnode and set the heuristic1 boolean of
the node to true so the compareto function will
compare the heuristic costs only and the heuristic int
variable of the node with the calculateheurstic
function which calculates the number of actions
that are needed to go and carry the most damaged
hostage that I will be able to save and returning him
to the phonebooth considering the pads then
multiply this number by 20 since all actions cost 20
except the kill action and insert it in the priority
queue and then we will make a loop which will end


only if the queue is empty or a node passed the
goaltest , then we will poll the first node in the
queue we will first check if the node reached the
goaltest or not and if it didn’t we will also check if
this node is a repeated state by checking if the state
of this node is in the repeatedStates hashmap if not
then we will call the allowedactions function which
will return an array of the legal action that can be
taken then we will loop on each action and call the
applyaction function with the action and it will
return a new node and we will insert it in the priority
queue depending on it’s heuristic cost
 **ENQUEUE_GREEDY2(Greedy search 2):** First we will
get the initialnode and set the heuristic1 boolean of
the node to true so the compareto function will
compare the heuristic costs only and the heuristic int
variable of the node with the calculateheurstic
function which calculates the manhattan distance to
the hostage and carry the nearest hostage and
returning him to the phonebooth considering the
pads then multiply this number by 20 since all
actions cost 20 except the kill action and insert it in
the priority queue and then we will make a loop
which will end only if the queue is empty or a node


passed the goaltest , then we will poll the first node
in the queue we will first check if the node reached
the goaltest or not and if it didn’t we will also check
if this node is a repeated state by checking if the
state of this node is in the repeatedStates hashmap
if not then we will call the allowedactions function
which will return an array of the legal action that can
be taken then we will loop on each action and call
the applyaction function with the action and it will
return a new node and we will insert it in the priority
queue depending on it’s heuristic cost
 **ENQUEUE_ASTAR1:** First we will get the initialnode
and set the heuristic int variable of the node with
the calculateheurstic1 function which calculates the
manhattan distance needed for neo to cover to go
and carry the most damaged hostage that I will be
able to save and returning him to the phonebooth
considering the pads then multiply this number by
20 since all actions cost 20 except the kill action and
insert it in the priority queue and then we will make
a loop which will end only if the queue is empty or a
node passed the goaltest , then we will poll the first
node in the queue we will first check if the node
reached the goaltest or not and if it didn’t we will


also check if this node is a repeated state by
checking if the state of this node is in the
repeatedStates hashmap if not then we will call the
allowedactions function which will return an array of
the legal action that can be taken then we will loop
on each action and call the applyaction function with
the action and it will return a new node and we will
insert it in the priority queue depending on it’s
heuristic cost+ pathcost
 **ENQUEUE_ASTAR2:** First we will get the initialnode
and set the heuristic int variable of the node with
the calculateheurstic2 function which calculates the
number of actions that are needed to go and carry
the nearest hostage and returning him to the
phonebooth considering the pads then multiply this
number by 20 since all actions cost 20 except the kill
action and insert it in the priority queue and then we
will make a loop which will end only if the queue is
empty or a node passed the goaltest , then we will
poll the first node in the queue we will first check if
the node reached the goaltest or not and if it didn’t
we will also check if this node is a repeated state by
checking if the state of this node is in the
repeatedStates hashmap if not then we will call the


allowedactions function which will return an array of
the legal action that can be taken then we will loop
on each action and call the applyaction function with
the action and it will return a new node and we will
insert it in the priority queue depending on it’s
heuristic cost+ pathcost
 **ENQUEUE_AT_ITERATIVE_END:** First we will get the
create a infinite loop that keeps incrementing the int
value k and then we create the initialnode and insert
it in the queue and then we will make a loop which
will end only if the queue is empty or a node passed
the goaltest , then we will poll the first node in the
queue we will first check if the node reached the
goaltest or not and if it didn’t we will also check if
this node is a repeated state by checking if the state
of this node is in the repeatedStates hashmap if not
we will check if the depth of the node is more than j
if not then we will call the allowedactions function
which will return an array of the legal action that can
be taken then we will loop on each action and call
the applyaction function with the action and it will
return a new node and we will insert it at the front
of the queue and if the current node has a depth
that is higher or equal than j we will not expand it


```
and we will skip it and if the queue is empty we will
reset the queue and the repeatedstates hashmap
and start again but with the j incremented and keep
repeating until we find a goal.
```
## 3 - Heurstic functions

For us to prove that our heuristic functions are
admissible we first have to know our pathcosts. All
actions cost 20 except killing a mutated hostage costs
20 , 00 0 and killing an agent costs 20 0 , 000.

**Heurstic Function 1:** Our first heuristic function is the
distance needs to be taken to reach the most damaged
hostage that I can save and returning him to the
phonebooth disregarding any agents and multiplying this
number by 20 since every action has a cost of 20 except
the kill action so this function should be admissible
because we disregarded the agents so there could be an
agent in the way which I have to kill to reach the hostage
which will increase the cost drastically and also we
consider the pads so if neo distance will be closer to the
hostage if he took some pads we will calculate this


distance instead the direct distance from neo to the
hostage and also we will calculate the distance from the
hostage to the phonebooth the same way while
considering the pads and even if the hostage died before
reaching neo could reach him neo will need to kill him
anyways because he is a mutated hostage so this will also
increase the actual cost so the heuristic cost will be
always equal or less than the actual cost.

**Heuristic function 2:** Our second heuristic function is the
distance that needs to be taken to reach the nearest
hostage and returning him to the phonebooth
disregarding any agents and multiplying this number by
20 since every action has a cost of 20 except the kill
action so this function should be admissible because we
disregarded the agents so there could be an agent in the
way which I have to kill to reach the hostage which will
increase the cost drastically and also we consider the
pads so if neo distance will be closer to the hostage if he
took some pads we will calculate this distance instead
the direct distance from neo to the hostage and also we
will calculate the distance from the hostage to the
phonebooth the same way while considering the pads
and even if the hostage died before reaching neo could
reach him neo will need to kill him anyways because he is


a mutated hostage so this will also increase the actual
cost so the heuristic cost will be always equal or less than
the actual cost.

## 3 - Examples

**This is an example for the UC search just to show you
how visualize works.The action that was done is first
printed then the carried hostages damage then the
deaths then the kills the finally the grid in a form of a 2d
array**

**Grid:** 5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;
0,0,30,3,0,80,4,4,80 (This is the example that is the project description)

**Solution:**
down,down,takePill,left,left,down,down,left,left,up,carry
,up,up,takePill,down,down,down,right,right,right,right,ca
rry,left,fly,down,right,drop,up,left,fly,left,left,left,up,up,u
p,up,carry,down,down,down,down,right,right,right,fly,rig
ht,down,drop;0;0;3197

Action:down


CarriedHostagesDamage:Deaths:0

Kills:0[H(32), A, null, Pad(4,3), null] (^)
[P, A, null, null, Neo(0) TB][null, A, null, null, P]
[H(82), A, null, A, A][null, null, null, Pad(0,3), H(82)] (^)
(^) Action:down
CarriedHostagesDamage:Deaths:0
Kills:0[H(34), A, null, Pad(4,3), null] (^)
[P, A, null, null, TB][null, A, null, null, Neo(0) P] (^)
[H(84), A, null, A, A][null, null, null, Pad(0,3), H(84)] (^)
Action:takePillCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(14), A, null, Pad(4,3), null][P, A, null, null, TB]
[null, A, null, null, Neo(0)][H(64), A, null, A, A]
[null, null, null, Pad(0,3), H(64)]Action:left
CarriedHostagesDamage:Deaths:0
Kills:0[H(16), A, null, Pad(4,3), null] (^)
[P, A, null, null, TB][null, A, null, Neo(0), null] (^)
[H(66), A, null, A, A][null, null, null, Pad(0,3), H(66)] (^)
Action:leftCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(18), A, null, Pad(4,3), null][P, A, null, null, TB]
[null, A, Neo(0), null, null][H(68), A, null, A, A]
[null, null, null, Pad(0,3), H(68)]Action:down
CarriedHostagesDamage:Deaths:0
Kills:0[H(20), A, null, Pad(4,3), null] (^)
[P, A, null, null, TB][null, A, null, null, null] (^)
[H(70), A, Neo(0), A, A][null, null, null, Pad(0,3), H(70)] (^)
Action:down


CarriedHostagesDamage:Deaths:0

Kills:0[H(22), A, null, Pad(4,3), null] (^)
[P, A, null, null, TB][null, A, null, null, null] (^)
[H(72), A, null, A, A][null, null, Neo(0), Pad(0,3), H(72)] (^)
Action:leftCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(24), A, null, Pad(4,3), null][P, A, null, null, TB]
[null, A, null, null, null][H(74), A, null, A, A]
[null, Neo(0), null, Pad(0,3), H(74)]Action:left
CarriedHostagesDamage:Deaths:0
Kills:0[H(26), A, null, P (^) ad(4,3), null]
[P, A, null, null, TB][null, A, null, null, null] (^)
[H(76), A, null, A, A][Neo(0), null, null, Pad(0,3), H(76)] (^)
Action:upCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(28), A, null, Pad(4,3), null][P, A, null, null, TB]
[null, A, null, null, null][Neo(0) H(78), A, null, A, A] (^)
[null, null, null, Pad(0,3), H(78)]Action:carry
CarriedHostagesDamage:80Deaths:0
Kills:0[H(30), A, null, Pad(4,3), null] (^)
[P, A, null, null, TB][null, A, null, null, null] (^)
[Neo(0), A, null, A, A[null, null, null, Pad(0,3), H(80)]] (^)
Action:upCarriedHostagesDamage:82 (^)
Deaths:0Kills:0
[H(32), A, null, Pad(4,3), null][P, A, null, null, TB]
[Neo(0), A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), H(82)]Action:up
CarriedHostagesDamage:84


Deaths:0Kills:0
[H(34), A, null, Pad(4,3), null][Neo(0) P, A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), H(84)]Action:takePill
CarriedHostagesDamage:64Deaths:0

Kills:0[H(14), A, null, Pad(4,3), null] (^)
[Neo(0), A, null, null, TB][null, A, null, null, null]
[null, A, null, A, A][null, null, null, Pad(0,3), H(64)] (^)
Action:downCarriedHostagesDamage:66 (^)
Deaths:0Kills:0
[H(16), A, null, Pad(4,3), null][null, A, null, null, TB]
[Neo(0), A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), H(66)]Action:down
CarriedHostagesDamage:68Deaths:0
Kills:0[H(18), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[Neo(0), A, null, A, A][null, null, null, Pad(0,3), H(68)] (^)
Action:downCarriedHostagesDamage:70 (^)
Deaths:0Kills:0
[H(20), A, null, Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[Neo(0), null, null, Pad(0,3), HAction:right (70)]
CarriedHostagesDamage:72Deaths:0
Kills:0[H(22), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][null, Neo(0), null, Pad(0,3), H(72)] (^)
Action:rightCarriedHostagesDamage:74 (^)
Deaths:0


Kills:0[H(24), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][null, null, Neo(0), Pad(0,3), H(74)] (^)
Action:rightCarriedHostagesDamage:76 (^)
Deaths:0Kills:0
[H(26), A, null, Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Neo(0) Pad(0,3), H(76)]Action:right
CarriedHostagesDamage:78Deaths:0
Kills:0[H(28), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][null, null, null, Pad(0,3), Neo(0) H(78)] (^)
Action:carryCarriedHostagesDamage:80,80 (^)
Deaths:0Kills:0
[H(30), A, null, Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), Neo(0)]Action:left
CarriedHostagesDamage:82,82Deaths:0
Kills:0[H(32), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][null, null, null, Neo(0) Pad(0,3), null] (^)
Action:flyCarriedHostagesDamage:84,84 (^)
Deaths:0Kills:0
[H(34), A, null, Neo(0) Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), null]Action:down
CarriedHostagesDamage:86,86Deaths:0
Kills:0


[H(36), A, null, Pad(4,3), null][null, A, null, Neo(0), TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), null]Action:right
CarriedHostagesDamage:88,88Deaths:0

Kills:0[H(38), A, null, Pad(4,3), null] (^)
[null, A, null, null, Neo(0) TB][null, A, null, null, null]
[null, A, null, A, A][null, null, null, Pad(0,3), null] (^)
Action:dropCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(40), A, null, Pad(4,3), null][null, A, null, null, Neo(0) TB] (^)
[null, A, null, null[null, A, null, A, A], null]
[null, null, null, Pad(0,3), null]Action:up
CarriedHostagesDamage:Deaths:0
Kills:0[H(42), A, null, Pad(4,3), Neo(0)] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][null, null, null, Pad(0,3), (^) null]
Action:leftCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(44), A, null, Neo(0) Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), null]Action:fly
CarriedHostagesDamage:Deaths:0
Kills:0[H(46), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][null, null, null, Neo(0) Pad(0,3), null] (^)
Action:leftCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(48), A, null, Pad(4,3), null]


[null, A, n[null, A, null, null, null]ull, null, TB] (^)
[null, A, null, A, A][null, null, Neo(0), Pad(0,3), null] (^)
Action:leftCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(50), A, null, Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, Neo(0), null, Pad(0,3), null]Action:left
CarriedHostagesDamage:Deaths:0
Kills:0[H(52), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][Neo(0), null, null, Pad(0,3), null (^) ]
Action:upCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(54), A, null, Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][Neo(0), A, null, A, A]
[null, null, null, Pad(0,3), null]Action:up
CarriedHostagesDamage:Deaths:0
Kills:0[H(56), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][Neo(0), A, null, null, null] (^)
[null, A, null, A, A][null, null, null, Pad(0,3), null] (^)
Action:upCarriedHostagesDamage: (^)
Deaths:0Kills:0
[H(58), A, null, Pad(4,3), null][Neo(0), A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), null]Action:up
CarriedHostagesDamage:Deaths:0
Kills:0[Neo(0) H(60), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB]


[null, A, null, null, null][null, A, null, A, A]
[Action:carrynull, null, null, Pad(0,3), null]
CarriedHostagesDamage:62Deaths:0

Kills:0[Neo(0), A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][null, null, null, Pad(0,3), null] (^)
Action:downCarriedHostagesDamage:64 (^)
Deaths:0Kills:0
[null, A, null, Pad(4,3), null][Neo(0), A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), null]Action:down
CarriedHostagesDamage:66Deaths:0
Kills:0[null, A, null (^) , Pad(4,3), null]
[null, A, null, null, TB][Neo(0), A, null, null, null] (^)
[null, A, null, A, A][null, null, null, Pad(0,3), null] (^)
Action:downCarriedHostagesDamage:68 (^)
Deaths:0Kills:0
[null, A, null, Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][Neo(0), A, null, A, A]
[null, null, null, Pad(0,3), null]Action:down
CarriedHostagesDamage:70Deaths:0
Kills:0[null, A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][Neo(0), null, null, Pad(0,3), null] (^)
Action:rightCarriedHostagesDamage:72 (^)
Deaths:0Kills:0
[null, A, null, Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null]


[null, A, null, A, A][null, Neo(0), null, Pad(0,3), null] (^)
Action:rightCarriedHostagesDamage:74 (^)
Deaths:0Kills:0
[null, A, null, Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, Neo(0), Pad(0,3), null]Action:right
CarriedHostagesDamage:76Deaths:0
Kills:0[null, A, null, Pad(4,3), null] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][null, null, null, Neo(0) Pad(0,3), null] (^)
Action:flyCarriedHostagesDamage:78 (^)
Deaths:0Kills:0
[null, A, null, Neo(0) Pad(4,3), null][null, A, null, null, TB]
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), null]Action:right
CarriedHostagesDamage:80Deaths:0
Kills:0[null, A, null, Pad(4,3), Neo(0)] (^)
[null, A, null, null, TB][null, A, null, null, null] (^)
[null, A, null, A, A][null, null, null, Pad(0,3), null] (^)
Action:downCarriedHostagesDamage:82 (^)
Deaths:0Kills:0
[null, A, null, Pad(4,3), null][null, A, null, null, Neo(0) TB] (^)
[null, A, null, null, null][null, A, null, A, A]
[null, null, null, Pad(0,3), null]Action:drop
CarriedHostagesDamage:Deaths:0
Kills:0[null, A, null, Pad(4,3), null] (^)
[null, A, null, null, Neo(0) TB][null, A, null, null, null]
[null, A, null, A, A]


[null, null, null, Pad(0,3), null]

**Grid 1:**
5,5;3;1,3;4,0;0,1,3,2,4,3,2,4,0,4;3,4,3,0,4,2;1,4,1,2,1,2,1,4
,0,3,1,0,1,0,0,3;4,4,45,3,3,12,0,2,88.

**Solutions for grid 1**

**Breadth First search:**
left,up,carry,down,down,right,down,carry,kill,down,ri
ght,carry,left,left,left,left,drop;1;2;205779.

**Depth First Search:**
up,kill,right,down,kill,up,left,kill,right,down,fly,up,right,
right,down,fly,up,right,right,down,fly,up,right,right,do
wn,fly,up,right,right,down,fly,up,right,right,down,fly,u
p,right,right,down,fly,up,right,right,down,fly,up,right,fl
y,up,kill,right,right,right,right,down,fly,right,down,right
,down,takePill,up,up,up,left,fly,up,right,right,down,rig
ht,down,kill,up,up,right,down,fly,up,left,down,down,d
own,down,right,takePill,kill,up,up,up,up,right,fly,up,ri
ght,down,down,down,down,left,up,takePill,up,up,up,r
ight,right,right,right,down,fly,right,down,right,down,kil
l,up,up,up,left,fly,up,right,right,down,right,down,down
,down,left,up,up,left,down,down,left;3;8;153


**Iterative deepening search:**

up,left,carry,right,down,down,down,kill,carry,right,do
wn,carry,left,left,left,left,drop;1;2;316373

**Uniform Cost search:**
down,down,right,takePill,down,carry,up,left,carry,up,left
,left,left,down,takePill,up,up,fly,left,carry,right,fly,down,
down,down,drop;0;0;14961

**Greedy search 1:**
down,down,right,down,kill,up,takePill,down,left,up,carry
,down,right,carry,left,left,takePill,kill,right,right,up,left,up
,kill,left,up,right,up,kill,right,down,fly,up,right,right,down
,fly,up,right,right,down,fly,up,right,right,down,fly,up,righ
t,right,down,fly,up,right,right,down,fly,up,right,right,dow
n,fly,up,right,right,down,fly,up,right,down,down,down,d
own,left,left,up,left,takePill,up,up,up,kill,right,right,right,
right,down,down,down,down,left,left,left,up,up,left,dow
n,down,drop;3;6;527

**Greedy search 2:**
down,down,right,down,kill,up,left,down,right,up,left,kill,
right,kill,down,left,up,right,down,left,up,right,down,left,
up,right,down,left,up,carry,right,kill,takePill,down,left,up


,up,up,up,fly,down,down,down,drop,right,right,takePill,l
eft,up,up,up,right,right,up,fly,up,kill,right,kill,right,right,d
own,right,down,left,left,down,left,left,down;2;6;1157

**Astar1:**

down,down,carry,right,takePill,down,carry,up,left,up,left
,left,left,down,takePill,up,up,fly,left,carry,right,fly,down,
down,down,drop;0;0;2085

**Astar2:**
down,down,carry,right,takePill,down,carry,up,left,up,left
,left,left,down,takePill,up,up,fly,left,carry,right,fly,down,
down,down,drop;0;0;2775

### OPTIMALITY COMPARISON

BF: Breath first search is not an optimal search since it
traverse the tree level by level and stops when it find its
first goal


DF: Depth first search is not an optimal search since it
expands all the depth of each node and stops when it
finds its first goal

ID:Iterative depth search is not an optimal search in our
case because it our pathcost will not increase uniformally
in the depth since a death or a kill will increase the cost
drastically so it is not optimal in our problem

UC:Uniform cost is an optimal search since it expands the
nodes which has the less cost first so the first goal it will
find will be the node that passes the goal test and has
the lowest cost of all the other goal nodes.

Greedy 1 and 2: greedy search is not an optimal search
since it expands the node that has less heuristic cost first
so the first goal node but that doesn’t mean it will be the
optimal one

A star 1 and 2 : a star search is an optimal search since it
expands the nodes that has lower path cost+ heuristic
cost and since our heuristic functions are admissible it
will always insure optimal solutions

### COMPLETENESS COMPARISON


BF: Breath first search is a complete search since it
traverses the nodes level by level so if there is a goal it
will find it.

DF: Depth first search is not a complete search since it
expands all the depth of each node but this can lead it to
get stuck in an infinite loop but since we prevent
repeated states it will find a solution if there is any.

ID:Iterative depth search is a complete search since it is
similar to depth first but it stops at a certain depth and
start again with a higher depth limit so if there is a
solution it will find it.

UC:Uniform cost is an complete search since it expands
the nodes which has the less cost first so eventually it will
reach the node that passes the goal test unless if the
costs are with negative which may be stuck in an infinite
loop but since we don’t have negative costs and also we
handle repeated states this will never happen.

Greedy 1 and 2: greedy search is a complete search.

A star 1 and 2 : a star search is an complete search

### EXPANDED NODES COMPARISON


BF: Breath first expanded 205779 nodes in the first
grid which is expected since breath first search
traverse nodes level by level.

DF: Depth first search expanded 153 nodes in grid 1
which is low as we expected since most likely as
long as the nodes are far from the root it is most
likely getting closer to the goal.

ID:Iterative depth search expanded 316373 nodes
which is higher than the BFS as we expected because it
iterates the same nodes multiple of times until it reaches
the goal

UC:Uniform cost expanded 14961 nodes which is
reasonable since it expands the nodes with lower
uniform costs and deaths until it finds a goal.

Greedy 1: greedy search 1 expanded 527 which is low but
we expected this since greedy search is an informed
search and have an estimation where the goal is

Greedy 2 :greedy search 2 expanded 1157 nodes which is
low but still higher than greedy1 which is because greedy
2 function may underestimate the goal more than greedy
1


A star 1: Astar search 1 expanded 2085 nodes and got
the optimal solution which also had a solution that has
the same cost as the UC search but with less nodes
expansion that is because it considers both the heuristic
and pathcost

A star 2 : a star search expanded 2775 nodes and got a
solution as a star1 which is optimal and had the same
path cost as the UC.

**Memory and CPU comparison**

```
Strategi
es
```
### BF DF ID UC GR1 GR

### 2

### A1 A2

### CPU

```
utlizatio
n
```
### 5.6

### %

### 11.3

### %

### 11 .8

### %

### 5.4

### %

### 10.8

### %

### 5.6

### %

### 15.6

### %

### 20.3

### %

```
Ram
Usage
```
### 14

### 59

```
mb
```
### 23

```
mb
```
### 10.2

```
mb
```
### 10.

### 2

```
mb
```
### 61

```
mb
```
### 98.

```
6m
b
```
### 129

```
mb
```
### 98

```
mb
```

The highest ram usage was the BF which is expected
because it expands nodes in ever expanding way which
takes a lot of ram and the lowest ram usage was from the
ID and the UC which the UC makes more sense for it to
be the lowest ram since it only expands node with lower
costs so it makes sense that it expands a lower amount of
nodes than the others while we think that the ID memory
usage is based that it is close to the depth but with a limit
which keep expanding the same nodes over and over
again which explains why it has a high cpu usage.

The highest cpu utilization from the Astar 1 that is
because it needs a lot of calculation to calculate the
heuristic and to sum it with the path cost and the lowest
cpu utilization was from the bf

**Grid 2 :**
5,5;2;0,4;3,4;3,1,1,1;2,3;3,0,0,1,0,1,3,0;4,2,54,4,0,85,1,0,
43


**Solutions for grid 2**

BFS:left,left,left,left,down,carry,down,down,kill,down,rig
ht,right,carry,right,right,up,drop;1;2;10358

DFS:down,down,down,down,left,up,up,up,up,right,down
,down,down,down,left,up,up,up,up,right,down,down,do
wn,down,left,up,up,up,up,right,down,down,down,down,
left,up,up,up,left,up,left,kill,right,right,right,down,down,
down,down,left,up,up,up,left,down,down,kill,up,up,up,ri
ght,right,down,down,down,down,left,up,up,takePill,up,u
p,right,down,down,down,down,left,up,left,up,up,up,left,
fly,up,right,up,kill,up,right,right,right,down,down,down,d
own,left,up,up,up,left,down,down,down,left,up,up,left,d
own,kill,up,up,up,right,right,right,right,down,down,down
;3;5;124
IDS:left,left,left,left,down,carry,down,down,kill,right,righ
t,down,carry,up,right,right,drop;1;2;30699
UCS:left,down,down,takePill,left,left,left,up,carry,down,d
own,down,carry,right,right,right,right,up,drop,down,left,l
eft,carry,right,up,right,drop;0;0;1628

GR1:left,left,down,down,down,down,left,kill,right,right,u
p,left,down,left,kill,right,right,up,up,left,down,left,down,
kill,right,right,up,up,up,up,right,down,down,left,takePill,


up,right,down,down,down,left,left,left,up,left,up,kill,righ
t,right,up,up,right,down,down,down,right;3;4;235
GR2:left,left,left,fly,down,carry,right,kill,right,right,up,lef
t,up,right,takePill,left,down,down,left,up,left,up,up,kill,ri
ght,up,left,down,down,right,up,left,down,right,up,left,ca
rry,down,down,down,right,kill,right,right,right,up,drop;3;
3;2948
AS1:left,down,down,takePill,left,left,left,up,carry,down,d
own,down,carry,right,right,right,up,right,drop,left,left,do
wn,carry,right,up,right,drop;0;0;1078
AS2:down,down,left,takePill,left,left,left,up,carry,down,d
own,down,carry,right,right,right,right,up,drop,down,left,l
eft,carry,right,right,up,drop;0;0;1170

The discussion of the optimality and completeness will
be the same as the grid above because they are the same
strategies.

### EXPANDED NODES COMPARISON

They are the same differences that are in the first grid.


### MEMORY AND CPU COMPARISON

```
Strategi
es
```
### BF DF ID UC GR1 GR

### 2

### A1 A2

### CPU

```
utlizatio
n
```
### 18

### %

### 5.6% 10.8

### %

### 5.4

### 2 %

### 10.8

### 6 %

### 30.

### 0 %

### 5.65

### %

### 11.3

### 2 %

```
Ram
Usage
```
### 14

### 4.6

```
mb
```
### 23.8

```
mb
```
### 10.2

```
mb
```
### 8.5

```
mb
```
### 42.5

```
mb
```
### 142

```
.9m
b
```
### 95.2

```
mb
```
### 79

```
mb
```
The highest strategy that takes ram is the BF as we said
before it expands in an ever expanding way which why it
makes sense that it has the highest memory and the
smallest memory is the uniform cost because it only
expands the nodes that has the smallest cost so it makes
sense that it has a small memory usage.

The highest strategy that used the cpu is the greedy 2
function which is because it calculates the heuristic
function which may use a lot of cpu. while the lowest
one is the DF which may be because there is little
calculations needed in the df and memory.



