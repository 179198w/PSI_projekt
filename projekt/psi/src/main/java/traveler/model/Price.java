package traveler.model;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@ToString(exclude = { "reservations", "touristEventComponent", "period" })
@EqualsAndHashCode(exclude = { "reservations", "touristEventComponent",
		"period" })
public class Price {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@Max(value = 1000000)
	@Min(value = 0)
	@NotNull
	private Integer adultValue;

	@Max(value = 1000000)
	@Min(value = 0)
	@NotNull
	private Integer childValue;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PriceType type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "period_id")
	private Period period;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tourist_event_component_id")
	private TouristEventComponent touristEventComponent;

	@ManyToMany(mappedBy = "prices", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reservations;

}
