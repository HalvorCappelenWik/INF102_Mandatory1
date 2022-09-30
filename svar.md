# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation and a description of why it has this runtime.

**If you have implemented new methods not listed you must add these as well.**

# Section 1
The runtime should be expressed using two parameters:
   * `n` - number of elements in the list
   * `k` - number of elements to select

### Task 1.1 - MyRandomSelector
* `removeRandom(List<T> list)`: O(n)
    * Because list.Remove is worst case O(n) in both arraylist and linkedList. We have to iterate tru the array to find the element to remove.  
* `removeRandom(List<T> list, int k)`: O(nk)
    * method has O(nk) because we run removeRandom(List<T> list), which is O(n), k times. 


### Task 1.2 - MySmallestSelector
* `selectSmallest(List<T> list, int k, Comparator<? super T> comp)`: O(n * log(n))
    * We create a new copy of the list. This is O(n) since we iterate through the list and add each element to the new list.
      kSmallest.sort(comp); is O(n * log(n) * c) where c is the comparator used. Comparator.NaturalOrder = O(1)
      We select the k first items, this gives us O(k) and total runtime is O(n * log(n) + k)
      Ignoring constants we get runtime of O(n * log(n))
  

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
* `selectRobots(Job job, List<Robot> available)`: O(nk)
    *  since myRandomSelector.removeRandom(list, k) is O(nk)

**IStrategy** <br></br>
* ``registerRobots(List<Robot> robots)``: O(2n) -> O(n) 
    * Because Arraylist.copy is O(n), we ignore constants. 
* ``registerNewJob(Job job)``: O(mnk)
    * Because AbstractStrategy.doJobs is O(mnk) 
* ``registerJobAsFulfilled(Job job)``: O(mnk)
  * Because AbstractStrategy.doJobs is O(mnk)

**AbstractStrategy** <br></br>
* `doJobs()`: O(mnk)
    * Because we have a while loop going tru the backlog of jobs which is O(m), then for each job we run selectRobots which is 
    O(nk). See comments in method for more details.  
    We have O(m) * (O(kn) + O(kn) + O(n)) ->  O(mkn). 
* `selectJob()`: O(1) 
    * Constant time when retrieving the head of a queue. 
* `removeJob(Job job)`: O(n)
    * Worst case is that job is not first in the queue, this is O(n)
* `assignRobots(List<Robot> selected, Job job)`: O(nk)
    * In this method when assigning selected robots to a job, we first iterate tru the selected robots which is 
    O(k). Then we move the robots to the job location, which is O(log m). Then we remove the selected robot from the list of available robots which is O(n).
    See comments in method for more details. 
    We have O(k) * O(k*n) + (O(k) * O(log m)) -> O(k * n)
* `getAvailableRobots()`: O(1)
    * Only returns the list available, does not copy. 

## ClosestStrategy
Give the runtime of all methods when using `ClosestStrategy`.

**ClosestStrategy** <br></br>
* `selectRobots(Job job, List<Robot> available)`: O(n * log(n))
    * Because mySmallestSelector.selectSmallest(list, k) is O(n * log(n))

**IStrategy** <br></br>
* ``registerRobots(List<Robot> robots)``: O(2n) -> O(n)
  * Because Arraylist.copy is O(n), we ignore constants.
* ``registerNewJob(Job job)``: O(mnk)
  * Because AbstractStrategy.doJobs is O(mnk)
* ``registerJobAsFulfilled(Job job)``: O(mnk)
  * Because AbstractStrategy.doJobs is O(mnk)

**AbstractStrategy** <br></br>
* `doJobs()`: O(m) * (O(n * log(n)) + O(k*n) + O(n)) -> O(mkn)
  * Because we have a while loop going tru the backlog of jobs which is O(m), then for each job we run selectRobots which is
    O(n * log(n)), but this does not affect the runtime. See comments in method for more details.
    Assuming that mn*log n is bigger smaller than mkn. 
* `selectJob()`: O(1)
    * Constant time when retrieving the head of a queue.
* `removeJob(Job job)`: O(n)
    * Worst case is that job is not first in the queue, this is O(n)
* `assignRobots(List<Robot> selected, Job job)`: O(k) * (O(log(m) + O(n)) + (O(k) * O(log m)) -> O(k * n)
  * In this method  when assigning selected robots to a job, we first iterate tru the selected robots which is
    O(k). Then we move the robots to the job location, which is O(log m). Then we remove the selected robot from the list of available robots which is O(n).
    See comments in method for more details.
* `getAvailableRobots()`: O(1)
    * Only returns the list available, does not copy.


## BetterStrategy
For `BetterStrategy` you do not need to give a runtime analysis. 
Instead, you must explain your code. What was your idea for getting a better result? What is your strategy?

1. Select the closest available robots for a job. I.e. same as in closesStrategy. 
2. Then I arrange the backlog such that the job with the lowest distance to the available robots is first in the queue.
3. In MoveAvailableRobots i first implemented so that the available robots would move to the average location of executed jobs. 
   but this did not improve performance. Then i implemented that robots should move to a random location of the executed jobs. 
   in some cases this does improve performance and i beat 5/6 inputs, but not regularly. 
4. I think the better approach would be to implement the geometric median and then move the robots according to this. 
   But did not have time to implement this. Or move the robots according to k-means clustering. 

