package calcCars;

/**
 * this class represent the parking problem algorithm. the problem is that we
 * have cars in a parking and we need know how many cars are in the parking. the
 * solution is to mark the first car with a 'v' and move forward if we see
 * another 'v' we change it to 'w' and return to the start by counting the steps
 * back, if the starting point is 'v' so we didn't finish and we need to start
 * over, but if the starting point is 'w' that means we counted all the cars. *
 * 
 * @author spyro
 *
 */

public class CalcCarsLinkedList {
	LinkedListCycle cars;
	final int nLetters = 23, size = 30;// size of list and numbers of letters(24)
	final char v = 'v', w = 'w';// choose a mark

	/**
	 * random parcking generator
	 */
	// build random parking
	public CalcCarsLinkedList() {
		cars = new LinkedListCycle();
		for (int i = 0; i < size; i++) {
			char c = (char) ('a' + (int) (Math.random() * nLetters));
			cars.add(c);
		}
		System.out.println(cars.toString());
	}

	/**
	 * main function to calculate all cars in the parking
	 * 
	 * @return
	 */
// cars calculation
	public int calcCars() {
		cars.getHead().setData(v);// mark the first car as 'v'
		Node t = cars.getHead().getNext();// we move one step to fix null exception
		boolean flag = true; // continue
		int count = 1;

		while (flag) {
			if (t.getData() == v) {
				t.setData(w);// we move forward until we see 'v' and set that car to 'w'
				int i = count;// keeps the counter

				while (i > 0) {// go back to starting point by the count
					t = t.getPrev();
					i--;
				}
				if (t.getData() == w)
					flag = false;// if the head is 'w' so its the starting point and we finished
				else {// else we go step forward and start over
					count = 1;
					t = cars.getHead().getNext();
				}
			} else {
				t = t.getNext();
				count++;
			}
		}
		return count;// return numbers of steps wich is the number of cars
	}

	public static void main(String[] args) {
		CalcCarsLinkedList parking = new CalcCarsLinkedList();
		System.out.println("number of cars = " + parking.calcCars());
	}

}
