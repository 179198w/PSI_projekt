package traveler.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "catalog")
@Data
public class Catalog {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment") // TODO: change to sequence
	private Long id;

	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "catalog_tourist_event", joinColumns = { @JoinColumn(name = "catalog_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tourist_event_id", nullable = false, updatable = false) })
	private List<TouristEvent> touristEvents;
	
}
