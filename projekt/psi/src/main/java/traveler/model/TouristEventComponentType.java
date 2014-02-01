package traveler.model;

public enum TouristEventComponentType {
	ARRIVAL("Przyjazd"), DEPARTURE("Odjazd"), FEEDING_TYPE("Wyżywienie");
	
	private String name;

	private TouristEventComponentType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
