package view;

import javax.swing.*;
import model.*;

public abstract class AbstractDisplayPane extends JPanel {
	private static final long serialVersionUID = 6437976554496769048L;
	protected SimulatorModel model;

	public AbstractDisplayPane(SimulatorModel model) {
		this.model=model;
		model.addView(this);
	}

	public SimulatorModel getModel() {
		return model;
	}

	public void updateView() {
		repaint();
	}
}
