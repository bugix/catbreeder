package ch.catnip.catbreeder.view;

import ch.catnip.catbreeder.model.Cat;

public interface CatView extends AppView {
	
	interface CatViewListener {
		void enter(String parameters);
		
		void saveCat(Cat cat);
	}
}
