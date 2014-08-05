package ch.catnip.catbreeder.presenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.catnip.catbreeder.view.CatView;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
public class CatPresenter implements Presenter<CatView> {
	
	private static final transient Logger logger = LoggerFactory.getLogger(LoginPresenter.class);

	private CatView view;

	@Override
	public void setView(CatView view) {
		this.view = view;
	}

}
