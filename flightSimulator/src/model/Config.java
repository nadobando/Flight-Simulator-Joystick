package model;


import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Config.
 */
public class Config implements Serializable{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The file name. */
	private String fileName=null;
	
	/** The delimiter. */
	private String delimiter=null;
	
	/** The attributes. */
	private String attributes=null;
	
	/** The ip. */
	private String ip=null;
	
	/** The joystick port. */
	private int nValues,modelPort,joystickPort;
	
	/** The flight control path. */
	private String flightControlPath=null;
	
	/** The flight engines path. */
	private String flightEnginesPath=null;
	
	/** The view attributes. */
	private String viewAttributes=null;
	
	/**
	 * Gets the flight engines path.
	 *
	 * @return the flight engines path
	 */
	public String getFlightEnginesPath() {
		return flightEnginesPath;
	}
	
	/**
	 * Sets the flight engines path.
	 *
	 * @param flightEnginesPath the new flight engines path
	 */
	public void setFlightEnginesPath(String flightEnginesPath) {
		this.flightEnginesPath = flightEnginesPath;
	}
	
	/**
	 * Gets the flight control path.
	 *
	 * @return the flight control path
	 */
	public String getFlightControlPath() {
		return flightControlPath;
	}
	
	/**
	 * Sets the flight control path.
	 *
	 * @param flightControlPath the new flight control path
	 */
	public void setFlightControlPath(String flightControlPath) {
		this.flightControlPath = flightControlPath;
	}
	
	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * Sets the ip.
	 *
	 * @param ip the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * Gets the joystick port.
	 *
	 * @return the joystick port
	 */
	public int getJoystickPort() {
		return joystickPort;
	}
	
	/**
	 * Sets the joystick port.
	 *
	 * @param joystickPort the new joystick port
	 */
	public void setJoystickPort(int joystickPort) {
		this.joystickPort = joystickPort;
	}
	
	/**
	 * Sets the model port.
	 *
	 * @param modelPort the new model port
	 */
	public void setModelPort(int modelPort) {
		this.modelPort = modelPort;
	}
	
	/**
	 * Gets the view attributes.
	 *
	 * @return the view attributes
	 */
	public String getViewAttributes() {
		return viewAttributes;
	}
	
	/**
	 * Sets the view attributes.
	 *
	 * @param viewAttributes the new view attributes
	 */
	public void setViewAttributes(String viewAttributes) {
		this.viewAttributes = viewAttributes;
	}
	
	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Gets the delimiter.
	 *
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return delimiter;
	}
	
	/**
	 * Sets the delimiter.
	 *
	 * @param delimiter the new delimiter
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public String getAttributes() {
		return attributes;
	}
	
	/**
	 * Sets the attributes.
	 *
	 * @param attributes the new attributes
	 */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
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
	
	/**
	 * Gets the model port.
	 *
	 * @return the model port
	 */
	public int getModelPort() {
		return modelPort;
	}

}
