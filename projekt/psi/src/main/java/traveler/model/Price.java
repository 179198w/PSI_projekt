package traveler.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "prices")
@Data
public class Price {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment") // TODO: change to sequence
	private Long id;
	
	private Integer adultValue;
	
	private Integer childValue;
	
	@Enumerated(EnumType.STRING)
	private PriceType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "period_id")
	private Period period;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tourist_event_component_id")
	private TouristEventComponent touristEventComponent;
	
	@ManyToMany(mappedBy = "prices", fetch = FetchType.LAZY)
	private List<Reservation> prices;
	
}
