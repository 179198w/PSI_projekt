package traveler.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
import org.joda.time.LocalDate;

@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "tourist_events")
@ToString(exclude = {"photoUrls","catalogs","periods","touristEventComponents","reservations"})
@Data
public class TouristEvent {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment") // TODO: change to sequence
	private Long id;

	private String name;

	@Type(type="text")
	private String description;

	@Column(name = "statue_url")
	private String statueUrl;

	@Column(name = "people_limit")
	private Integer peopleLimit;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "photos", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "photo_url")
	private List<String> photoUrls;
	
	@ManyToMany(mappedBy = "touristEvents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Catalog> catalogs;

	@ManyToOne(fetch = FetchType.LAZY)
	private Hotel hotel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operator_id")
	private Operator operator;
	
	private Boolean visible;
	
	@Column(name = "active_from")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate activeFrom;
	
	@Column(name = "active_to")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate activeTo;
	
	@OneToMany(mappedBy = "touristEvent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Period> periods;
	
	@ManyToMany(mappedBy = "touristEvents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TouristEventComponent> touristEventComponents;
	
	@OneToMany(mappedBy = "touristEvent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reservation> reservations;
	
}
