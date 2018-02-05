package controller;

import model.SimulatorModel;

import java.awt.event.ActionListener;

/**
 * In this application one controller is modeled. The controller knows the model.
 * Currently the controller doesn't change any part of the view. Therefore
 * the controller is not aware of the view.
 *
 * @author The Button Bashers
 * @version 1.2
 *
 */
public abstract class AbstractController implements ActionListener{
	protected SimulatorModel model;

	/**
	 * Constructor of the class
	 * Register the model to the controller.
	 *
	 * @param model the model
	 */
	AbstractController(SimulatorModel model) {
		this.model=model;
	}
}
