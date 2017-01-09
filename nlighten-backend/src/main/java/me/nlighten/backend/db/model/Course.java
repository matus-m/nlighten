package me.nlighten.backend.db.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import me.nlighten.backend.db.enums.CourseType;
import me.nlighten.backend.db.enums.Difficulty;

/**
 * The Class Course.
 * 
 * @author Lubo
 */
@NamedQueries({
    @NamedQuery(name = "Course.loadById", query = "SELECT c FROM Course c WHERE c.id = :id") })
@NamedEntityGraph(name = "course.all", attributeNodes = { @NamedAttributeNode("lessons"),
    @NamedAttributeNode("comments"), @NamedAttributeNode("questions") })
@Getter
@Setter
@Entity
@Table(name = "COURSE")
public class Course extends TraceAble {

  /** The load by id constant. */
  public static String LOAD_BY_ID = "Course.loadById";

  /** The title. */
  private String title;

  /** The description. */
  @Column(length = 2048)
  private String description;

  /** The tags. */
  private String tags;

  /** The author. */
  @ManyToOne(fetch = FetchType.LAZY)
  private User author;

  /** The Course type. */
  @Column(name = "COURSE_TYPE")
  @Enumerated(EnumType.STRING)
  private CourseType CourseType;

  /** The language. */
  private String language;

  /** The duration. */
  @Temporal(TemporalType.TIMESTAMP)
  private Date duration;

  /** The difficulty. */
  @Enumerated(EnumType.STRING)
  private Difficulty difficulty;

  /** The released. */
  @Temporal(TemporalType.TIMESTAMP)
  private Date released;

  /** The rating. */
  private int rating;

  /** The resources. */
  private String resources;

  /** The lessons. */
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Lesson> lessons;

  /** The comments. */
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Comment> comments;

  /** The questions. */
  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Question> questions;

  @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Event> events;
}
