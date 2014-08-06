package ch.catnip.catbreeder.presenter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.catnip.catbreeder.model.Cat;
import ch.catnip.catbreeder.service.BreederService;
import ch.catnip.catbreeder.view.CatListView;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
public class CatListPresenter implements Presenter<CatListView> {
	
	private static final transient Logger logger = LoggerFactory.getLogger(LoginPresenter.class);

	private CatListView view;
	
	@Autowired
	BreederService breederService;

	@Override
	public void setView(CatListView view) {
		this.view = view;
	}
	
	// TODO Is this ok?
	public List<Cat> getCatList() {
		return breederService.myCats();
	}

}
