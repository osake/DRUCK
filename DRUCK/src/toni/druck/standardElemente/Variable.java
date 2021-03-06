package toni.druck.standardElemente;

import java.text.MessageFormat;

import toni.druck.model.DataModel;
import toni.druck.page.Element;
import toni.druck.page.Page;


/*****
 * 
 * @author Thomas Nill
 * 
 * Verbindung eines oder mehrere Namen mit den Daten, die in einer Page stehen
 * 
 * 
 */
public class Variable extends StandardElement {

	private String feldname;
	protected int[] felder = null;

	public Variable(String name, Page page) {
		super(name, page);
	}

	public Variable() {
		super();
	}

	public String getVariable() {
		return feldname;
	}

	public void setVariable(String feldname) {
		this.feldname = feldname;
		felder = getPage().getData().getMultiIndex(feldname);
	}

	protected String fuegeVariableInDenTextEin(String text, String[] s) {
		String textformat = text;
		if ((textformat == null || "".equals(textformat)) && s.length == 1) {
			return s[0];
		}
		textformat = textformat.replaceAll("'", "#;#");

		String erg = MessageFormat.format(textformat, s);

		erg = erg.replaceAll("#;#", "'");
		return erg;
	}

	public String[] getVariablen() {
		String s[] = new String[felder.length];
		DataModel m = getPage().getData();
		for (int i = 0; i < s.length; i++) {
			s[i] = m.get(felder[i]);
		}
		return s;
	}

	@Override
	public String getText() {
		String text = super.getText();
		if (felder != null) {
			String[] s = getVariablen();
			return fuegeVariableInDenTextEin(text, s);
		}
		return text;
	}

}