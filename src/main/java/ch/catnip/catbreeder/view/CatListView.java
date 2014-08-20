package ch.catnip.catbreeder.view;

import java.util.List;

import ch.catnip.catbreeder.model.Cat;

public interface CatListView extends AppView {
	
	public static final String PLACE = "";
	
	void renderCatList(List<Cat> catList);

	interface CatListViewListener {
		void enter(String parameters);
	}
}
