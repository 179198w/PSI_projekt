package traveler.model;

public enum PriceType {
	COMPONENT("Komponent"), BASE("Bazowa");
	
	private String name;

	private PriceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
