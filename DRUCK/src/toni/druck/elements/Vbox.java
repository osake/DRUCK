package toni.druck.elements;

import toni.druck.core.VHElement;
import toni.druck.core2.Element;
import toni.druck.core2.Page;

public class Vbox extends VHElement {

	public Vbox(String name, Page page) {
		super(name, page);
	}

	public Vbox() {
		super();
	}

	public void berechneGroesse() {
		int h = 0;
		int w = 0;
		if (childs != null) {
			for (Element e : childs) {
				e.berechneGroesse();
				w = Math.max(w, e.getWidth());
				h += e.getHeight();
			}
		}
		setzeWidthAndHeight(h, w);
	}

	public void setzePositionen() {

		int h = 0;
		if (childs != null) {
			for (Element e : childs) {
				e.setzePositionen();
				e.setRelX(0);
				e.setRelY(h);
				h += e.getHeight();
			}
		}
	}

}