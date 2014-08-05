package ch.catnip.catbreeder.model;

public enum Breed {

	SHORTHAIR("Short Hair"), LONGHAIR("Long Hair"), NAKED("Naked");
	
	private final String caption;
	
	private Breed(final String caption) {
        this.caption = caption;
    }
	
	public int getId() {
        return ordinal();
    }
	
	public String getCaption(){
        return caption;
    }

    @Override
    public String toString() {
        return caption;
    }
}
