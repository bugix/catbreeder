package ch.catnip.catbreeder.view;

import ch.catnip.catbreeder.model.Cat;

public interface CatView extends AppView {

	public static final String PLACE = "cat";
	
	void renderSaveResponse();
	
	interface CatViewListener {

		void enter(Cat cat);

		//void saveCat(Cat cat);
	}
}
