# Pickers problem

## 🤔 Chapter 1 - Understanding the problem
We have two k pickers and n orders to spread between all of them.
In the first task the goal is to assign the maximum possible number of orders.
The orders have a constraint: they have a parameter ```completeBy``` which marks the latest time they can be processed.
This is classic decision problem.
The set of possible solutions grows exponentially fast with the growth of its scale.
There are algorithms that can provide us with good solutions that run in polynomial time.

## 📚 Chapter 2 - Solution

All the solutions use three classes: ```Order``` representing single order,
```Store``` representing meta-data of task, and ```Picker``` representing and employee,
with assigned Orders. The output of the solver is a list of pickers.

### Solution I - Greedy
First solution uses a greedy algorithm for both tasks. First, orders are places into a
priority queue, based on simple metrics. Task 1 solution uses 2-step decision criteria,
prioritizing earlier ```completeBy``` value, then shorter picking time. Task 2 uses metric based on
a quotient of price by duration.

### Solution II - DP
There is also another approach to this problem.
Using dynamic programming technique can be very useful.
This solution is implemented using assignment table with recurrent relationship.
As my solution is not performing very well, it is yet to be debugged.

Unit test cover Solution I classes.

## 🏭 Chapter 3 - Tech stack
I used a couple of libraries in the solution:
* Jackson - to parse ```.json``` files into POJOs.
* Lombok - for simple getter generation
* Junit 5 - for unit tests
