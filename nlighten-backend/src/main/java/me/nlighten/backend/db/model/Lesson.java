package me.nlighten.backend.db.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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

/**
 * The Class Lesson.
 * 
 * @author Lubo
 */
@NamedQueries({
    @NamedQuery(name = "Lesson.loadById", query = "SELECT l FROM Lesson l WHERE l.id = :id")})
@NamedEntityGraph(name = "lesson.comments.questions",
    attributeNodes = {@NamedAttributeNode("comments"), @NamedAttributeNode("questions")})
@Getter
@Setter
@Entity
@Table(name = "LESSON")
public class Lesson extends TraceAble {

  /** The title. */
  private String title;

  /** The description. */
  @Column(length = 2048)
  private String description;

  /** The order. */
  @Column(name = "LESSON_ORDER")
  private int order;

  /** The duration. */
  @Temporal(TemporalType.TIMESTAMP)
  private Date duration;

  /** The comments. */
  @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Comment> comments;

  /** The questions. */
  @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Question> questions;

  /** The course. */
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "COURSE_ID")
  private Course course;
}
