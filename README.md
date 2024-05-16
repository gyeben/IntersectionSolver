# IntersectionSolver

This command line application can be used to solve intersection problems that involve an intersection of equivalent roads.

# Research

As the traffic rules were not provided in the exercise description, I consulted the Hungarian traffic code ([KRESZ](https://net.jogtar.hu/jogszabaly?docid=97500001.kpm)) first. The relevant traffic rules in this context are the following:

- Priority must be given at the intersection to all ambulances.
- A car must give right of way to other cars coming from its right.
- A left-turning car must give right of way to a car coming from the opposite direction if the other car is going straight or turning right.

The combination of the second and the third rule means that a car turning right can cross at any time (if there is no ambulance coming).
