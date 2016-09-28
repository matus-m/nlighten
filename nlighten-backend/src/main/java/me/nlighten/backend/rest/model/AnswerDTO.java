package me.nlighten.backend.rest.model;

/**
 * The Class Answer.
 *
 * @author Lubo
 */
public class AnswerDTO extends TraceAbleDTO {

  /**
   * The author.
   */
  private String author;

  /**
   * The text.
   */
  private String text;

  /**
   * The approved.
   */
  private boolean approved;

  /**
   * The question.
   */
  private QuestionDTO question;

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

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  public QuestionDTO getQuestion() {
    return question;
  }

  public void setQuestion(QuestionDTO question) {
    this.question = question;
  }

}
