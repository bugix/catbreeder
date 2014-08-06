package ch.catnip.catbreeder.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.common.io.ByteStreams;

import ch.catnip.catbreeder.model.Breed;
import ch.catnip.catbreeder.model.Breeder;
import ch.catnip.catbreeder.model.Cat;

@Service
public class MocBreederService implements BreederService {

	private static final transient Logger logger = LoggerFactory.getLogger(MocBreederService.class);

	private final Breeder currentBreeder;

	private final List<Cat> catList;

	@Autowired
	public MocBreederService(ApplicationContext applicationContext) {
		currentBreeder = new Breeder();
		currentBreeder.setFirstName("John");
		currentBreeder.setLastName("Doe");
		currentBreeder.setLogin("john");
		currentBreeder.setPassword("123456");

		byte[] grumpyBytes = null;

		try {
			grumpyBytes = ByteStreams.toByteArray(applicationContext.getResource("classpath:grumpy.jpg").getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		catList = new LinkedList<Cat>();
		catList.add(new Cat("Anonymous", currentBreeder, Breed.LONGHAIR, LocalDate.now(), grumpyBytes));
		catList.add(new Cat("Brutus", currentBreeder, Breed.SHORTHAIR, LocalDate.now(), grumpyBytes));
		catList.add(new Cat("PussyCat", currentBreeder, Breed.SHORTHAIR, LocalDate.now(), grumpyBytes));
		catList.add(new Cat("Wotan", currentBreeder, Breed.LONGHAIR, LocalDate.now(), grumpyBytes));
		catList.add(new Cat("X-Cat", currentBreeder, Breed.NAKED, LocalDate.now(), grumpyBytes));
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

		if (!"john".equals(currentBreeder.getLogin())
				|| !"123456".equals(currentBreeder.getPassword())) {
			return false;
		}

		return true;
	}

}
