package ch.catnip.catbreeder.presenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.catnip.catbreeder.view.LoginView;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
public class LoginPresenter implements Presenter {

	private static final transient Logger logger = LoggerFactory.getLogger(LoginPresenter.class);

	private LoginView view;
}
