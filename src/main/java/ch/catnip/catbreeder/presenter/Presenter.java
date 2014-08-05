package ch.catnip.catbreeder.presenter;

import java.io.Serializable;

import ch.catnip.catbreeder.view.LeView;

public interface Presenter <T extends LeView> extends Serializable {

	void setView(T view);

}
