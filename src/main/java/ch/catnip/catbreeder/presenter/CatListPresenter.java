package ch.catnip.catbreeder.presenter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.catnip.catbreeder.model.Cat;
import ch.catnip.catbreeder.service.BreederService;
import ch.catnip.catbreeder.view.CatListView;

@SuppressWarnings("serial")
@Component("catListPresenter")
@Scope("prototype")
public class CatListPresenter implements Presenter, CatListView.CatListViewListener {
	
	private static final transient Logger logger = LoggerFactory.getLogger(LoginPresenter.class);

	private CatListView view;
	
	@Autowired
	private BreederService breederService;

	@Override
	public void enter(String parameters) {
		List<Cat> cats = breederService.myCats();
		
		view.renderCatList(cats);
	}

	@Override
	public void setView(CatListView view) {
		this.view = view;
	}
}
