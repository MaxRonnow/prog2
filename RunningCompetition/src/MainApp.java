public class MainApp {

	public static void main(String[] args) {
		
		/*
		 * NOTE: the integer given as argument to the Random constructor
		 * is the seed for the random number generator. With a specific
		 * seed, the random number generator will always give the same
		 * sequence of outputs.
		 */
		
		Competition comp = new Competition(); // Create a competition with default settings and a random result
		//Competition comp = new Competition(new Random(3)); // Create a competition that always has the same result
		//Competition comp = new Competition(200, new Random(3)); // Create a competition of length 200 that always has the same result
		//Competition comp = new Competition(200, new Random()); // Create a competition of length 200 that has a random result
		comp.initializeCompetition();
		comp.go();
	}

}
