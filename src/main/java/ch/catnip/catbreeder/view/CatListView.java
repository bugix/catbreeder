package ch.catnip.catbreeder.view;

import java.util.List;

import ch.catnip.catbreeder.model.Cat;

public interface CatListView extends LeView {
	
	void setCatList(List<Cat> catList);
	
}
