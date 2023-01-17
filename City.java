/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	
 *	@since	
 */
public class City implements Comparable<City> {
	
	// fields
	private String name;
	private String designation;
	public String state;
	private int population;
	
	// constructor
	public City(String nm, String st, String tp, int pop){
		name = nm;
		//System.out.println(name);
		state = st;
		designation = tp;
		population = pop;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other){
		if(population!=other.population){
			return this.population-other.population;
		}
		if(!(state.equals(other.state))){
			compareState(other);
		}
		else{
			compareOther(other);
		}
		return 1;
	}
	
	/**
	 * Compare two cities names
	 * @param other		the other city to compare
	 * @return		this.name-other.name
	 */
	public int compareOther(City other){
		int num = this.name.compareTo(other.name);
		return num;
	}
	
	/**
	 * Compare two cities states
	 * @param other		the other city to compare
	 * @return 			this.state-other.state
	 */
	public int compareState(City other){
		int num = this.state.compareTo(other.state);
		return num;
	}
	
	/**	Equal city name, state name, and population
	 *	@param other		the other City to compare
	 *	@return				true if city name, state name, and population are equal; false otherwise
	 */
	 public boolean isEqual(City other){
		 if(this.state.equals(other.state)){
			 if(this.name.equals(other.name)){
				if(this.population==other.population){
					return true;
				}
			}
		}
		return false;
	}
	
	/**	Accessor methods */
	
	/**
	 * Returns the population
	 * @return		the population
	 */
	public int getPopulation(){
		return population;
	}
	/**
	 * Returns the state to which the city belongs to
	 * @return		the state
	 */
	public String getState(){
		return state;
	}
	/**
	 * Returns the name of the city
	 * @return		the city name
	 */
	public String getName(){
		return name;
	}
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
