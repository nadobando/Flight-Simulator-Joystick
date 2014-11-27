package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import controller.Controller;
import controller.MyController.UpdateDataCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class SimulatorReader.
 */
public class SimulatorReader {
	
	/** The delimeter. */
	private String delimeter=null;
	
	/** The buff. */
	private BufferedReader buff=null;
	
	/** The list. */
	private ConcurrentHashMap<String, Attribute> list=null;
	
	/** The names. */
	private String[] names=null; 
	
	/** The control. */
	private Controller control=null;
	
	
	/**
	 * Instantiates a new simulator reader.
	 *
	 * @param delimeter the delimeter
	 * @param buff the buff
	 * @param list2 the list2
	 * @param names the names
	 * @param control the control
	 */
	public SimulatorReader(String delimeter, BufferedReader buff,
			ConcurrentHashMap<String, Attribute> list2, String[] names,Controller control)
	{
		
		this.delimeter = delimeter;
		this.buff = buff;
		this.list = list2;
		this.names = names;
		this.control=control;
	}

	
	
	/**
	 * Start to read.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public  void startToRead() throws IOException
	{
		String line;
		
		
		String[] data=null;
		ArrayList<Double> dataD = new ArrayList<Double>();
		
		try{
			
		
		//buff.readLine();
		int j=0;
		while((line = buff.readLine())!=null)
		{
			
			System.out.println(buff);
			UpdateDataCommand up= (UpdateDataCommand)control.getCommands().get("UpdateData").create();
			data=line.split(delimeter);
			for(int i=0;i<names.length;i++)
			{
				
				dataD.add(Double.parseDouble(data[i]));
				list.get(names[i]).setValue(dataD.get(i));
				
			}
			
			up.setData(dataD);
			up.execute();

			dataD.clear();
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
		}
		catch(IOException io)
		{
			return;
		}
		
		
	}


}



























