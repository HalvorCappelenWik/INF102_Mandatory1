# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation and a description of why it has this runtime.

**If you have implemented new methods not listed you must add these as well.**

# Section 1
The runtime should be expressed using two parameters:
   * `n` - number of elements in the list
   * `k` - number of elements to select

### Task 1.1 - MyRandomSelector
* `removeRandom(List<T> list)`: O(1)
    * This method uses random.nextint(listSize)  to find a random index in our list. This is O(1). 
    * Furthermore, we swap the item in our given random index with the last item in the list. This operation is O(1). 
    * Lastly we remove and return the last item from the list.
    * Since we do the swapping this method will have O(1) runtime, in both an arraylist and linkedList, since we are only accessing the end of the list. 
* `removeRandom(List<T> list, int k)`: O(k)
    * method has O(k) because we removeRandom(List<T> list) runs k times. 

### Task 1.2 - MySmallestSelector
* `selectSmallest(List<T> list, int k, Comparator<? super T> comp)`: O(n * log(n))
    * list.sort is O(n * log(n) * c) where c is the comparator used. Comparator.NaturalOrder = O(1)
    * We select the k first items. 
    * Ignoring constants we get runtime of O(n * log(n))

# Section 2
In this section you must consider all methods being used for a strategy. This includes the strategy class itself (`RandomStrategy`, `ClosestStrategy` and `BetterStrategy`), as well as the methods found in `IStrategy` and `AbstractStrategy`.

The runtime should be expressed using three parameters
   * `m` - number of jobs in the simulation (input up to 1 000 000 can be expected)
   * `n` - number of robots in the simulation (input up to 100 can be expected)
   * `k` - number of robots required for a job (input up to 10 can be expected)

Note that not all of these parameters will be relevant to all methods. Some methods might just be dependent on one or two of the parameters.

## RandomStrategy
Give the runtime of all methods when using `RandomStrategy`.

**RandomStrategy** <br></br>
* `selectRobots(Job job, List<Robot> available)`: O(k)
    *  myRandomSelector.removeRandom(list, k) is O(k)

**IStrategy** <br></br>
* ``registerRobots(List<Robot> robots)``: O(2n) -> O(n) 
    * Because Arraylist.copy is O(n) 
* ``registerNewJob(Job job)``: O(n (k log(m) + nk))
    * Because AbstractStrategy.doJobs is O(n (k log(m) + nk))
* ``registerJobAsFulfilled(Job job)``: O(n (k log(m) + nk))
    * Because AbstractStrategy.doJobs is O(n (k log(m) + nk))

**AbstractStrategy** <br></br>
* `doJobs()`: O(?)
    * *Insert description of why the method has the given runtime*
* `selectJob()`: O(1)
    * Constant time when retrieving the head of a queue. 
* `removeJob(Job job)`: O(1)
    * *Insert description of why the method has the given runtime*
* `assignRobots(List<Robot> selected, Job job)`: O(?)
    * *Insert description of why the method has the given runtime*
* `getAvailableRobots()`: O(?)
    * *Insert description of why the method has the given runtime*

## ClosestStrategy
Give the runtime of all methods when using `ClosestStrategy`.

**ClosestStrategy** <br></br>
* `selectRobots(Job job, List<Robot> available)`: O(?)
    * *Insert description of why the method has the given runtime*

**IStrategy** <br></br>
* ``registerRobots(List<Robot> robots)``: O(?)
    * *Insert description of why the method has the given runtime*
* ``registerNewJob(Job job)``: O(?)
    * *Insert description of why the method has the given runtime*
* ``registerJobAsFulfilled(Job job)``: O(?)
    * *Insert description of why the method has the given runtime*

**AbstractStrategy** <br></br>
* `doJobs()`: O(?)
    * *Insert description of why the method has the given runtime*
* `selectJob()`: O(?)
    * *Insert description of why the method has the given runtime*
* `removeJob(Job job)`: O(?)
    * *Insert description of why the method has the given runtime*
* `assignRobots(List<Robot> selected, Job job)`: O(?)
    * *Insert description of why the method has the given runtime*
* `getAvailableRobots()`: O(?)
    * *Insert description of why the method has the given runtime*


## BetterStrategy
For `BetterStrategy` you do not need to give a runtime analysis. 
Instead, you must explain your code. What was your idea for getting a better result? What is your strategy?

*Write your answer here*
