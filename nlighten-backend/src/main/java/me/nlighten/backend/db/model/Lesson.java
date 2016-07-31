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

/**
 * The Class Lesson.
 *
 * @author Lubo
 */
@NamedQueries({
  @NamedQuery(name = Lesson.LOAD_BY_ID, query = "SELECT l FROM Lesson l WHERE l.id = :id")})
@NamedEntityGraph(name = "lesson.comments.questions",
        attributeNodes = {
          @NamedAttributeNode("comments"),
          @NamedAttributeNode("questions")})
@Entity
@Table(name = "LESSON")
public class Lesson extends TraceAble {

  /**
   * The load by id constant.
   */
  public static final String LOAD_BY_ID = "Lesson.loadById";

  /**
   * The title.
   */
  private String title;

  /**
   * The description.
   */
  @Column(length = 2048)
  private String description;

  /**
   * The order.
   */
  @Column(name = "LESSON_ORDER")
  private int order;

  /**
   * The duration.
   */
  @Temporal(TemporalType.TIMESTAMP)
  private Date duration;

  /**
   * The comments.
   */
  @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Comment> comments;

  /**
   * The questions.
   */
  @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Question> questions;

  /**
   * The course.
   */
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "COURSE_ID")
  private Course course;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public Date getDuration() {
    return duration;
  }

  public void setDuration(Date duration) {
    this.duration = duration;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public Set<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(Set<Question> questions) {
    this.questions = questions;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

}
