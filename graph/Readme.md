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
