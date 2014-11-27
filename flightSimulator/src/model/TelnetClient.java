package model;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
 
import java.net.Socket;
 
 

// TODO: Auto-generated Javadoc
/**
 * The Class TelnetClient.
 */
public class TelnetClient
{
	
	/** The socket. */
	private Socket socket;
	
	/** The out. */
	private PrintWriter out;
    

	/**
	 * Instantiates a new telnet client.
	 *
	 * @param host the host
	 * @param port the port
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public TelnetClient (String host, int port) throws IOException {
	    socket = new Socket(host, port);
	    out = new PrintWriter(socket.getOutputStream(), true);
	    out.println("data\r");
    }

    /**
     * Close.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public synchronized void close () throws IOException {
	    out.println("quit\r");
	    out.close();
	    socket.close();
    }

    /**
     * Sets the.
     *
     * @param name the name
     * @param value the value
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void set (String name, String value) throws IOException {
    	out.println("set " + name + ' ' + value + '\r');
    }
 
    /**
     * Sets the.
     *
     * @param name the name
     * @param value the value
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void set (String name, double value) throws IOException
    {
    	set(name, Double.toString(value));
    }
 
  
 
}
 
