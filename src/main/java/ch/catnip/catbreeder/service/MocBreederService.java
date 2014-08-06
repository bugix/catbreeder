package ch.catnip.catbreeder.service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.catnip.catbreeder.model.Breed;
import ch.catnip.catbreeder.model.Breeder;
import ch.catnip.catbreeder.model.Cat;

@Service
public class MocBreederService implements BreederService {
	
	@Autowired
	ServletContext context;
	
	private static Breeder currentBreeder;
	
	private static List<Cat> catList;
	
	public MocBreederService() {
		currentBreeder = new Breeder();
		currentBreeder.setFirstName("John");
		currentBreeder.setLastName("Doe");
		currentBreeder.setLogin("john");
		currentBreeder.setPassword("123456");
		
		
		
		catList = new LinkedList<Cat>();
		catList.add(new Cat("Anonymous", currentBreeder, Breed.LONGHAIR, LocalDate.now(), null));
		catList.add(new Cat("Brutus", currentBreeder, Breed.SHORTHAIR, LocalDate.now(), null));
		catList.add(new Cat("PussyCat", currentBreeder, Breed.SHORTHAIR, LocalDate.now(), null));
		catList.add(new Cat("Wotan", currentBreeder, Breed.LONGHAIR, LocalDate.now(), null));
		catList.add(new Cat("X-Cat", currentBreeder, Breed.NAKED, LocalDate.now(), null));
	}

	@Override
	public void addCat(Cat cat) {
		cat.setBreeder(currentBreeder);
		catList.add(cat);
	}

	@Override
	public List<Cat> myCats() {
		return catList;
	}

	@Override
	public boolean loginBreeder(String login, String password) {
		
		if (!"john".equals(currentBreeder.getLogin()) || !"123456".equals(currentBreeder.getPassword()))
		{
			return false;
		}

		return true;
	}

}
