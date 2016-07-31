package me.nlighten.backend.rest.model;

/**
 * The Class Comment.
 *
 * @author Lubo
 */
public class CommentDTO extends TraceAbleDTO {

  /**
   * The author.
   */
  private String author;

  /**
   * The text.
   */
  private String text;

  /**
   * The course.
   */
  private CourseDTO course;

  /**
   * The lesson.
   */
  private LessonDTO lesson;

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

  public CourseDTO getCourse() {
    return course;
  }

  public void setCourse(CourseDTO course) {
    this.course = course;
  }

  public LessonDTO getLesson() {
    return lesson;
  }

  public void setLesson(LessonDTO lesson) {
    this.lesson = lesson;
  }

}
