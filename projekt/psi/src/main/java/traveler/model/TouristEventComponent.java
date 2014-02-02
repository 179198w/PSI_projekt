package traveler.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "tourist_event_components")
@ToString(exclude = {"touristEvents","prices"})
@Data
public class TouristEventComponent {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment") // TODO: change to sequence
	private Long id;
	
	private String name;

	@Enumerated(EnumType.STRING)
	private TouristEventComponentType type;
	
	private String description;
	
	@ManyToMany(mappedBy = "touristEventComponents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TouristEvent> touristEvents;
	
	@OneToMany(mappedBy = "touristEventComponent", fetch = FetchType.LAZY)
	private List<Price> prices;
	
}
