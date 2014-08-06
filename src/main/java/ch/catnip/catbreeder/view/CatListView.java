package ch.catnip.catbreeder.view;

import java.util.List;

import ch.catnip.catbreeder.model.Cat;

public interface CatListView extends LeView {
	
	void renderCatList(List<Cat> catList);

	interface CatListViewListener {
		void enter(String parameters);
		
		void setView(CatListView view);
	}
}
