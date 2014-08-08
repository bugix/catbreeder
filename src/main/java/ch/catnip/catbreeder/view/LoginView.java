package ch.catnip.catbreeder.view;


public interface LoginView extends AppView {
	
	void renderLoginFailed();
	
	interface LoginViewListener {
		void enter(String login, String password);
	}

}
