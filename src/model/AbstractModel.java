package model;

import view.*;
import java.util.*;

public abstract class AbstractModel {
	private List<AbstractDisplayPane> views;

	public AbstractModel() {
		views=new ArrayList<>();
	}

	public void addView(AbstractDisplayPane view) {
		views.add(view);
	}

	public void notifyViews() {
		for(AbstractDisplayPane v: views) v.updateView();
	}
}
