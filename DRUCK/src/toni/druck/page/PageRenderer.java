package toni.druck.page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;


/*****
 * 
 * @author Thomas Nill
 * 
 * Schnittstelle das die eigentliche Druckausgabe abgekapselt 
 * 
 *s 
 */
public interface PageRenderer {

	int REAL_PRINT = 0;
	int ACCUMULATION = 0;

	int getStatus();

	void setOutput(OutputStream out);

	void setOutput(String filename) throws FileNotFoundException;

	void print(Element elem);

	void printDefs(Element elem);

	void startDocument(Page page);

	void endDocument();

	void newPage(int pagenr, Page page);

	void addExtension(Extension qrCode);

	void include(String filename) throws IOException;
}
