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
import org.joda.time.LocalDate;

@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "periods")
@Data
@ToString(exclude = {"prices"})
public class Period {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment") // TODO: change to sequence
	private Long id;
	
	@Column(name = "active_from")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate from;
	
	@Column(name = "active_to")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate to;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tourist_event_id")
	private TouristEvent touristEvent;
	
	@OneToMany(mappedBy = "period", fetch = FetchType.LAZY)
	private List<Price> prices;
	
}
