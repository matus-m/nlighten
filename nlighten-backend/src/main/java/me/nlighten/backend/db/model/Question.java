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

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Question.
 * 
 * @author Lubo
 */
@NamedQueries({
    @NamedQuery(name = "Question.loadById", query = "SELECT q FROM Question q WHERE q.id = :id")})
@NamedEntityGraph(name = "questions.answers", attributeNodes = @NamedAttributeNode("answers") )
@Getter
@Setter
@Entity
@Table(name = "QUESTION")
public class Question extends TraceAble {

  /** The author. */
  private String author;

  /** The text. */
  @Column(length = 2048)
  private String text;

  /** The course. */
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "COURSE_ID")
  private Course course;

  /** The lesson. */
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "LESSON_ID")
  private Lesson lesson;

  /** The answers. */
  @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Answer> answers;
}
