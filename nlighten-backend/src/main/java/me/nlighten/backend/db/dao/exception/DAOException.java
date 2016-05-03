package me.nlighten.backend.db.dao.exception;

/**
 * The Class DAOException.
 * 
 * @author Lubo3
 */
public class DAOException extends Exception {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = -1605349987310445931L;

  /**
   * The message key.
   */
  private Enum<?> messageKey;

  /**
   * The arguments for the message.
   */
  private Object[] arguments;

  /**
   * Instantiates a new DAO exception.
   */
  public DAOException() {
    super();
  }

  /**
   * @param message
   */
  public DAOException(String message) {
    super(message);
  }

  /**
   * @param cause
   */
  public DAOException(Throwable cause) {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public DAOException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new DAO exception.
   *
   * @param messageKey the message key
   * @param arguments the arguments
   */
  public DAOException(Enum<?> messageKey, Object... arguments) {
    this.messageKey = messageKey;
    this.arguments = arguments;
  }

  /**
   * @param message the message
   * @param cause the cause
   * @param enableSuppression the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  protected DAOException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public Enum<?> getMessageKey() {
    return messageKey;
  }

  public void setMessageKey(Enum<?> messageKey) {
    this.messageKey = messageKey;
  }

  public Object[] getArguments() {
    return arguments;
  }

  public void setArguments(Object[] arguments) {
    this.arguments = arguments;
  }

}
