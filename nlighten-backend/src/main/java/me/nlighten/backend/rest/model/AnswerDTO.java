package me.nlighten.backend.rest.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Answer.
 * 
 * @author Lubo
 */
@Getter
@Setter
public class AnswerDTO extends TraceAbleDTO {

  /** The author. */
  private String author;

  /** The text. */
  private String text;

  /** The approved. */
  private boolean approved;

  /** The question. */
  private QuestionDTO question;
}
