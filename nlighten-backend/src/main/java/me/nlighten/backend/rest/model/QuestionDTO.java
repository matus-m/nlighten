package me.nlighten.backend.rest.model;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Question.
 * 
 * @author Lubo
 */
@Getter
@Setter
public class QuestionDTO extends TraceAbleDTO {

  /** The author. */
  private String author;

  /** The text. */
  private String text;

  /** The course. */
  private CourseDTO course;

  /** The lesson. */
  private LessonDTO lesson;

  /** The answers. */
  private Set<AnswerDTO> answers;
}
