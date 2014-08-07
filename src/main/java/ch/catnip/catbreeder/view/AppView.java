package ch.catnip.catbreeder.view;


public interface AppView {

	public default void construct() {
		initComponent();
		addEventHandlers();
		setLayout();
	}

	public void postConstruct();

	/**
	 * All the child component should be initialized here
	 */
	public void initComponent();

	/**
	 * All event should be handle in this method
	 */
	public void addEventHandlers();

	/**
	 * View layout should be set in this method
	 */
	public void setLayout();

	/**
	 * For add event registration
	 */
	public void attach();

	/**
	 * For add event un-registration
	 */
	public void detach();

}
