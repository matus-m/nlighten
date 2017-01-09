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
 * The Class Answer.
 * 
 * @author Lubo
 */
@Getter
@Setter
@Entity
@Table(name = "ANSWER")
public class Answer extends TraceAble {

  /** The author. */

  @ManyToOne(fetch = FetchType.LAZY)
  private User author;

  /** The text. */
  @Column(length = 2048)
  private String text;

  /** The approved. */
  private boolean approved;

  /** The question. */
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "QUESTION_ID")
  private Question question;
}
