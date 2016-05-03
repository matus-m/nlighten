package me.nlighten.backend.rest.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Comment.
 * 
 * @author Lubo
 */
@Getter
@Setter
public class CommentDTO extends TraceAbleDTO {

  /** The author. */
  private String author;

  /** The text. */
  private String text;

  /** The course. */
  private CourseDTO course;

  /** The lesson. */
  private LessonDTO lesson;
}
