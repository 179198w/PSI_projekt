package traveler.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "tourist_event_components")
@Data
public class TouristEventComponent {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment") // TODO: change to sequence
	private Long id;
	
	private String name;
	
	private TouristEventComponentType type;
	
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tourist_event_component_tourist_event", joinColumns = { @JoinColumn(name = "tourist_event_component_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tourist_event_id", nullable = false, updatable = false) })
	private List<TouristEvent> touristEvents;
	
	@OneToMany(mappedBy = "touristEventComponent", fetch = FetchType.LAZY)
	private List<Price> prices;
	
}
