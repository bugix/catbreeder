package ch.catnip.catbreeder.service;

import java.util.List;

import ch.catnip.catbreeder.model.Cat;

public interface BreederService {
	
	boolean loginBreeder(String login, String password);
	
	void addCat(Cat cat);
	
	List<Cat> myCats();

}
