package me.nlighten.backend.db.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Comment.
 * 
 * @author Lubo
 */
@Getter
@Setter
@Entity
@Table(name = "COMMENT")
public class Comment extends TraceAble {

  /** The author. */
  @ManyToOne(fetch = FetchType.LAZY)
  private User author;

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
}
