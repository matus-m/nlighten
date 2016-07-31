package me.nlighten.backend.db.model;

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

/**
 * The Class Question.
 *
 * @author Lubo
 */
@NamedQueries({
  @NamedQuery(name = Question.LOAD_BY_ID, query = "SELECT q FROM Question q WHERE q.id = :id")})
@NamedEntityGraph(name = "questions.answers", attributeNodes = @NamedAttributeNode("answers"))
@Entity
@Table(name = "QUESTION")
public class Question extends TraceAble {

  /**
   * The load by id constant.
   */
  public static final String LOAD_BY_ID = "Question.loadById";

  /**
   * The author.
   */
  private String author;

  /**
   * The text.
   */
  @Column(length = 2048)
  private String text;

  /**
   * The course.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COURSE_ID")
  private Course course;

  /**
   * The lesson.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LESSON_ID")
  private Lesson lesson;

  /**
   * The answers.
   */
  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Answer> answers;

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Lesson getLesson() {
    return lesson;
  }

  public void setLesson(Lesson lesson) {
    this.lesson = lesson;
  }

  public Set<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(Set<Answer> answers) {
    this.answers = answers;
  }

}
