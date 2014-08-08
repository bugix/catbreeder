package ch.catnip.catbreeder.view;

import ch.catnip.catbreeder.model.Cat;

public interface CatView extends AppView {
	
	void renderSaveResponse();
	
	interface CatViewListener {

		void enter(Cat cat);

	}
}
