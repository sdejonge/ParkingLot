package model;

import view.*;
import java.util.*;


/**
 * The Abstract model controls all other models
 *
 * @author The Button Bashers
 * @version 1.2
 *
 */

public abstract class AbstractModel {
	private List<AbstractDisplayPane> views;

	/**
	 * Constructor of the class
	 * Make an ArrayList of the views
	 */

	public AbstractModel() {
		views=new ArrayList<>();
	}


	/**
	 * Adds the views to the ArrayList
	 *
	 * @param view the Abstract view
	 */

	public void addView(AbstractDisplayPane view) {
		views.add(view);
	}

	/**
	 * Iterates through all the views and calls for the updateView() method
	 */

	public void notifyViews() {
		for(AbstractDisplayPane v: views) v.updateView();
	}
}
