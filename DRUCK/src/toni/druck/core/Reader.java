/*
 * Created on 07.10.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package toni.druck.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Category;

import toni.druck.core2.DataItem;
import toni.druck.helper.DebugAssistent;

/**
 * @author Nill
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Reader implements Runnable {
	static Category cat = Category.getInstance("Reader");

	DataFIFO queue;
	boolean isEmpty = false;
	java.io.Reader input;

	/**
	 * 
	 */
	public Reader(DataFIFO queue) {
		super();
		this.queue = queue;
		isEmpty = false;
	}

	public void run() {
		try {
			isEmpty = false;
			BufferedReader file = null;
			file = new BufferedReader(input, 1000);

			String line;

			line = file.readLine();
			while (line != null) {
				// cat.error("line " + line);
				DataItem t = new DataItem(line);
				queue.offer(t);
				
				// cat.error("line " + t);
				line = file.readLine();
				// Thread.sleep(10);
			}
			queue.offer(new DataItem(DataItem.ENDOFFILE));
			file.close();
			isEmpty = true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (Exception ex) {
			DebugAssistent.log(ex);
		}

	}

	public void read(String filename) throws IOException {
		cat.debug("open " + filename);
		read(new FileReader(filename));
	}

	public void read(java.io.Reader input) {
		this.input = input;
		Thread t = new Thread(this);
		t.start();
		// run();
	}

	public boolean isEmpty() {
		return isEmpty && queue.isEmpty();
	}
}