package TurtleAndRabbit_arm;


/**
 * this class represent a solution to the problem that there is a linked list
 * with arm and cycle we need to know what is the length of the arm and what is
 * the length of the cycle.
 * solution: 
 * the rabbit will move two steps every time
 * and the turtle one step, we move them until they meet (they must meet
 * eventually because there is a cycle we prove that in the Turtle and rabbit
 * problem) after they meet we will put the rabbit at the starting point(the
 * head of the arm) and the turtle will stay at the meeting point. now each of
 * them is moving one step every time (they both have the same amount of steps
 * to the cycle head) so they meet again in the cycle head and the number of
 * steps they did is the length of the arm. 
 * from the cycle head the rabbit will
 * stay in his place and the turtle will move along the cycle, when he meets the
 * rabbit the number of steps he did is the length of the cycle.
 * 
 * length of list is = arm length + cycle length
 * 
 * proof that they will meet:
 * mark:
 * n = length of cycle
 * m = length of arm
 * k = distance between cycle head to the meeting point
 * p = number of rounds the turtle did
 * q = number of rounds the rabbit did
 * i = number of steps the turtle did
 * i*2 = number of steps the rabbit did
 * -> i = m + np +k, 2i = m + nq + k
 * -> 2m + 2np + 2k = m + nq + k -> k = n(q-2p) - m
 * -> k = n - m -> meaning that the meeting point is (n-m) steps from the cycle head.
 * [assuming all cars are marked differently]!
 * @author spyro
 *
 */

public class CycleWithArm {
	final static int nLetters = 23, size = 26;// size of list and numbers of letters(24)
	
	public static int ArmLength(LinkedListCycle cars) {
		int ans = -1;// length of arm
		boolean ToRun = true;
		boolean flag = true;
		
		Node turtle = cars.getHead();
		Node rabbit = cars.getHead();
		
		while(flag) {
			
			if(turtle.getNext() == null || rabbit.getNext() == null || rabbit.getNext().getNext() == null) {
				System.out.println("there is no cycle");
				flag = false;
				ToRun = false;
			}
			
			else {
				turtle = turtle.getNext();
				rabbit = rabbit.getNext().getNext();
				
				if(turtle.getData() == rabbit.getData()) {
					flag = false;
					ToRun = true;
				}
			}
		}
		
		rabbit = cars.getHead();// move the rabbit to the starting point(arm head)
		while(ToRun) {
			ans++;
			if(turtle.getData() == rabbit.getData())
				ToRun = false;
			
			else {
				turtle = turtle.getNext();
				rabbit = rabbit.getNext();
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		LinkedListCycle cars = new LinkedListCycle();
		cars.add('#');
		for (int i = 0; i < size; i++) {
			char c = (char) ('a' + i);
			cars.add(c);
		}
		System.out.println(cars.toString());
		System.out.println("arm length = " + ArmLength(cars)); // the answer will always be 0 here because thats how we built it
	}

}
