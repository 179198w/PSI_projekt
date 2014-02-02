package traveler.model;

import java.util.List;

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
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "cities")
@Data
@ToString(exclude = { "hotels" })
public class City {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	// TODO: change to sequence
	private Long id;

	@NotEmpty
	private String name;

	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	private List<Hotel> hotels;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "country_id")
	private Country country;

}
