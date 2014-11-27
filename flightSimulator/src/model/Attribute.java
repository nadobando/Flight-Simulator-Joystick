package model;

import java.util.concurrent.ArrayBlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Attribute.
 */
public class Attribute {
	
	/** The name. */
	private String name=null;
	
	/** The value. */
	private double value;
	
	/** The n values. */
	private int nValues;
	
	/** The values. */
	private ArrayBlockingQueue<Double> values=null;
	
	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public ArrayBlockingQueue<Double> getValues() {
		return values;
	}

	/**
	 * Instantiates a new attribute.
	 *
	 * @param name the name
	 * @param value the value
	 * @param n the n
	 */
	public Attribute(String name, double value,int n) {
		
		this.name = name;
		this.value = value;
		this.nValues=n;
		values=new ArrayBlockingQueue<Double>(nValues);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(double value) {
		if(values.size()==nValues)
			if(!values.isEmpty())
				values.poll();
		values.add(value);
		this.value=value;
	}

	/**
	 * Gets the n values.
	 *
	 * @return the n values
	 */
	public int getNValues() {
		return nValues;
	}

	/**
	 * Sets the n values.
	 *
	 * @param nValues the new n values
	 */
	public void setNValues(int nValues) {
		this.nValues = nValues;
	}

	


	

	
}
