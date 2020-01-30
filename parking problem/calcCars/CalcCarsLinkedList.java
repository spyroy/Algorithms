package calcCars;

public class CalcCarsLinkedList {
	LinkedListCycle cars;
	final int nLetters = 23, size = 30;// size of list and numbers of letters(24)
	final char v = 'v', w = 'w';// choose a mark

	// build random parking
	public CalcCarsLinkedList() {
		cars = new LinkedListCycle();
		for (int i = 0; i < size; i++) {
			char c = (char) ('a' + (int) (Math.random() * nLetters));
			cars.add(c);
		}
		System.out.println(cars.toString());
	}

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
