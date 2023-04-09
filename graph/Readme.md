In worst case a graph can have :   E    =    (V * (V - 1)) / 2  edges     =>     V ^ 2  edges

----------------------------------------------------------------------------------------------

All nodes won't participate :-
1)  Shortest path in terms of edge => BFS
2)  Shortest path in terms of weight => Dijkstra

All nodes will participate :- 
1)  Prims, Kruskals (MST)
2)  Ordering matters => Topo Sort


----------------------------------------------------------------------------------------------------------------------------

Vertices/Nodes < 10,000 then only form adjacency matrix

----------------------------------------------------------------------------------------------------------------------------

hasPath ? Mere nbr se agar mere tak path hai => Mere se v path hai

multiSovler ? Take care whether recursive call is backtracking after visiting the distination node or it is never visited

hamiltonian ? Edge count = V - 1
hamiltonian Path ? Starts with source, visits all the nodes without visiting any node twice
hamiltonian Cycle ? Above condition + source node has a direct edge with the last visited node

bipartite ? Look into 1st class notes

----------------------------------------------------------------------------------------------------------------------------

MST : [Only for Undirected Graph] [+ve, -ve, 0] all edge weights are allowed
---
Graph should be connected, Should have N-1 edges, Cost should be minimum, No cycle

MST Algorithms :-     1) Prims      2) Kruskal
Jab saare vtx ko touch krna ho with minimum cost then prims ya kruskals lga do

PRIMS : [Greedy Algo]  TC : ElogE == ElogV(GFG)  Why ? In worst case E can be V^2 => Elog(V^2) => 2ElogV => ElogV 
1) You need graph to use Prim's Algo
2) When graph is dense use Prims, because if you use Kruskals then you will have to sort huge number of edges  [Edge is more compared to vtx]
3) TC is E log V
4) Prefer Kruskals since easy to code


KRUSKALS :
1) You don't need graph to use Kruskal's Algo, you job will be done just by tavelling on edges  [Edge is less compared to vtx]
2) When graph is sparse use Kruskal's => Less edges => Less time for sorting the edge 
3) TC is E log E, since you will sort all edges so it's E log E, 
4) Easy to code


# NOTE : Why do we use Simple array edge sorting in Kruskal's && PriorityQueue Sorting in Prim's ??
Kuki Kruskal's khula chalta hai, jo v minimum hai poore edge me usko pick kar lega whereas
Prim's haath pakar k chalta hai, src node se nbr node tak jo v mimimum hai usko pick karta hai(for this you need graph)
nbrs ko daalna hai so you need queue like BFS, and minimum nikalna hai isliye PriorityQueue use krna parega

----------------------------------------------------------------------------------------------------------------------------

BFS aati hai mtlb Dijkastra && Prims v likhna v aati hai

BFS kisi v 2 vtx/node ka minimum cost nikal k deti hai (Source && destination dependent) 
Jab weight constant ho tab BFS lgti hai, deciding factor is length
Queue is used

Dijkastar kisi v 2 vtx/node ka minimum cost nikal k deti hai (Source && destination dependent) 
Jab weight +ve && variable ho tab Dijkastra lgti hai, deciding factor is weightSoFar      [NOTE : Dijksta does not work for -ve weight]   
PriorityQueue is used.

Jab weight variable ho && saare vtx ko touch krna ho with minimum cost then prims ya kruskals lga do [MST], deciding factor in prims is weight
PriorityQueue is used.

NOTE : Dijksta and Prims is ditto same just relplace wsf of Dijksta with w in Prims
-----------------------------------------------------------------------------------------------------------------------------------------------

Union Find aati hai toh Kruskal v aati hai

Bs Edges ko weight k basis pe sort kar do, now sbse minimum weight ko ek ek kar k nikalo and start forming your graph incase you want to see a graph,
else just keep adding the cost also note to skip the edges which forms cycle same as Union Find.

NOTE : Union Find and Kruskal is ditto same in extra just sort edges on weight in Kruskals 
-------------------------------------------------------------------------------------------------------------------------------------------------------

# Shortest Path ALgo : ["-ve wt. cycle" k liye shortest path hota he nahi hai]  1) Dijkstra 2) BellManFord 3) Floyd

# Dijkstra : [Directed, Undirected] [Ek v -ve edge wt. nahi hona chahiye]

Dijkstra me koi kam weight path wala bnda usi node ko touch krne wapas aa sakta hai, with "minimum path cost", so tumko sabko chance dena hai, islye queue se nikalte waqt visited mark karo isme, 
PQ me jitne v nodes hai, unme se jis node ki sbse kam wsf hai, hum us node ka answer finalize kar saktey hai
=> jo sbse pehli baar niklega PQ me se, us node tak pahuchne k liye wahi sbse "minimum path cost" hai.
kuki dijkstra sirf +ve wt. k liye hota hai, and agar aapke (x-1) wsf bnde bade hai, x wsf bnde se, toh ghoom k cycle k through aapke (x-1) bnde,
jab v x tak pahuchenge kuch wt. add on kar he aaenge, kuki jab pehle he haar rahe the toh ghoom k aaoge tab v haaroge he.

=> Jo sbse pehli baar nikalta hai dijkstra me wahi us tak ka mimimum path cost hai, provided aap queue se nikalte waqt visisted mark karoge.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------
# NOTE : BFS me queue me daalte he visited mark kar dete hai and Prim's && Dijksra me nikalte waqt Why ??

Kuki BFS me agar cycle wali chiz nahi chahiye toh, daalte he mark kr dete hai, yaha hamesha level/radially baat hoti hai, with wt. 1
islye queue me daalte he visied mark kar do kuki sbka wt. 1 hota hai, so cycle lekar aaoge toh wt. aur badh he jaaega, jo sbse pehle kisi xth node ko touch karta hai wahi mimimum hai us node tak radially pahuchne k liye.

Par Prims me "p"queue me daalte he visited nahi mark saktey kuki aapko wo cycle wali chiz chahiye kuki yaha weight k terms me baat hoti hai,
and "overall mimimum cost" kisi dusre nodes se ghoom k aap tak "overall minimum cost" bna sakti hai, islye yaha cycle wali BFS use krna.

For Dijkstra read above

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

# What is CC && SCC(Necesarrily this will be cycle) ??
=> Kisi v node se kisi v node pe jaa saktey ho, if that is possible it is CC or SCC 

# Undirected graph me CC (Connected Component) hoti hai
# Directed graph me SCC (Strongly Connected Component) hoti hai

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

# KosaRaju, The idea is jiske taraf aap point kar rahe ho, usko kisi v tarah pehle visited mark kar do to obtain individual SCC

Step 1) DFS (Random Order)
Step 2) Reverse Edges
Step 3) DFS (Stack Top Order)

Look into .md file for explanation

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

NOTE : Kosaraju is for SCC, and TopoSortDFS is JUST EXACTLY SAME to first step of Kosaraju

----------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Topological Sort :-
=> Only for DAG)  
=> u comes before v

1) TopDFS(recursive)       
2) 2) Kahn's Algo(Iterative), kind of BFS

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
# DSU can detect cycle only in "undirected" graph
