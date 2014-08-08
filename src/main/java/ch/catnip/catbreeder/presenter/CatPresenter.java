package ch.catnip.catbreeder.presenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.catnip.catbreeder.model.Cat;
import ch.catnip.catbreeder.service.BreederService;
import ch.catnip.catbreeder.view.CatView;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
public class CatPresenter implements Presenter, CatView.CatViewListener {
	
	private static final transient Logger logger = LoggerFactory.getLogger(CatPresenter.class);

	@Autowired
	@Lazy
	private CatView view;
	
	@Autowired
	private BreederService breederService;

	@Override
	public void enter(Cat cat) {
		breederService.addCat(cat);
		view.renderSaveResponse();
	}

}
