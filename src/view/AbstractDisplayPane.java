package view;

import javax.swing.*;
import model.*;

/**
 * The Abstract view controls all other views
 *
 * @author The Button Bashers
 * @version 1.2
 *
 */

public abstract class AbstractDisplayPane extends JPanel {
	private static final long serialVersionUID = 6437976554496769048L;
	protected SimulatorModel model;

	/**
	 * Constructor of the class
	 * Sends view to add to the ArrayList of views
	 * @param model
	 */

	public AbstractDisplayPane(SimulatorModel model) {
		this.model=model;
		model.addView(this);
	}


	/**
	 * Returns the model name
	 * @return model the model
	 */

	public SimulatorModel getModel() {
		return model;
	}

	/**
	 * Updates the view
	 */

	public void updateView() {
		repaint();
	}
}
