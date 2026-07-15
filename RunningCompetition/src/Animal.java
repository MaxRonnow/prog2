import java.util.Random;


public abstract class Animal {

	/*
	 * 
	 * NOTE! The reference variable rng MUST refer to the 
	 * same random number generator that is used in the 
	 * class Competition
	 * 
	 */
	private Random rng; 
	
	
	/**The constructor for the Animal. Do not modify.
	 * @param random the random number generator which MUST
	 * be the same as the one used in the class Competition
	 */
	public Animal(Random random) {
		rng = random;
	}
	
	
	/**This method shall move the animal forward with a
	 * distance and random variation that is specific to 
	 * each type of animal. 
	 * 
	 * NOTE: You MUST use the method nextDouble in the class
	 * Random for getting the random numbers
	 */
	public abstract void run(); 
	
	/**This method shall randomly return true or false, where
	 * the probabilities of true and false are animal specific.
	 * If the method returns true, it means that the animal should
	 * move forward. If it returns false, it means that the animal 
	 * should not move forward (because it stops and eats)
	 * 
	 * NOTE: You MUST use the method nextDouble in the class
	 * Random for getting the random numbers
	 * 
	 * @return true if animal should move forward, otherwise false
	 */
	public abstract boolean willRun();
	
	/**This method shall return the distance that the animal
	 * has run so far
	 * 
	 * @return a double representing the distance that the animal
	 * has run so far
	 */
	public abstract double getDistanceTravelled();
	
}
