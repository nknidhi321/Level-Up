Revised 4th march

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

MST : 
---
Graph should be connected, Should have N-1 edges, Cost should be minimum, No cycle

MST Algorithms :-     1) Prims      2) Kruskal
Jab saare vtx ko touch krna ho with minimum cost then prims ya kruskals lga do

PRIMS :
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
