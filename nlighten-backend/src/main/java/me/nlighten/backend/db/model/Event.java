package me.nlighten.backend.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Event.
 * 
 * @author Lubo
 */
@Getter
@Setter
@Entity
@Table(name = "EVENT")
public class Event {

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  /** The title. */
  private String title;

  /** The start time. */
  @Temporal(TemporalType.TIMESTAMP)
  private Date startTime;

  /** The on air. */
  private boolean onAir;

  /** The course. */
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "COURSE_ID")
  private Course course;

  /** List of participants **/
  @ManyToMany
  List<User> participants = new ArrayList<User>();

  /** The owner **/
  @OneToOne
  @JoinColumn(name = "USER_ID")
  private User owner;

}
