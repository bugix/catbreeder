package ch.catnip.catbreeder.presenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.catnip.catbreeder.service.BreederService;
import ch.catnip.catbreeder.view.LoginView;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
public class LoginPresenter implements Presenter, LoginView.LoginViewListener {

	private static final transient Logger logger = LoggerFactory.getLogger(LoginPresenter.class);

	@Autowired
	@Lazy
	private LoginView loginView;
	
	@Autowired
	private BreederService breederService;

	@Override
	public void enter(String login, String password) {
		logger.debug("Login attemp of " + login);
		
		boolean loginSuccessfull = breederService.loginBreeder(login, password);
		
		if (loginSuccessfull)
		{
			logger.debug("Login attemp successfull");
		}
		else
		{
			logger.debug("Login attemp failed");
			loginView.renderLoginFailed();
		}
		
	}
}
