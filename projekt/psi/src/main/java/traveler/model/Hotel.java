package traveler.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "hotels")
@Data
@ToString(exclude = {"touristEvents"})
public class Hotel {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	// TODO: change to sequence
	private Long id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String address;

	@Type(type = "text")
	private String description;

	@Column(name = "number_of_stars")
	private Integer numerOfStars;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "city_id")
	private City city;

	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
	private List<TouristEvent> touristEvents;

}
