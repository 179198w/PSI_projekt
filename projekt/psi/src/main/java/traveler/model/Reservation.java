package traveler.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@Table(name = "reservations")
@Data
public class Reservation {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment") // TODO: change to sequence
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	@Column(name = "date_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private DateTime dateTime;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "reservation_price", joinColumns = { @JoinColumn(name = "reservation_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "price_id", nullable = false, updatable = false) })
	private List<Price> prices;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private TouristEvent touristEvent;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "reservation_customer", joinColumns = { @JoinColumn(name = "reservation_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "customer_id", nullable = false, updatable = false) })
	private List<Customer> customers;
	
}
