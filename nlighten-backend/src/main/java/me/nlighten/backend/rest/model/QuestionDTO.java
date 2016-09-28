package me.nlighten.backend.rest.model;

import java.util.Set;

/**
 * The Class Question.
 *
 * @author Lubo
 */
public class QuestionDTO extends TraceAbleDTO {

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

  /**
   * The answers.
   */
  private Set<AnswerDTO> answers;

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

  public Set<AnswerDTO> getAnswers() {
    return answers;
  }

  public void setAnswers(Set<AnswerDTO> answers) {
    this.answers = answers;
  }

}
