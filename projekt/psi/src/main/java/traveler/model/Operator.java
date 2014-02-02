package traveler.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Type;

@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "operators")
@Data
@ToString(exclude = {"touristEvents"})
public class Operator {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment") // TODO: change to sequence
	private Long id;

	private String name;

	@Type(type="text")
	private String description;
	
	@OneToMany(mappedBy = "operator", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<TouristEvent> touristEvents;
	
}
