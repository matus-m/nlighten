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
   * @param cause
   */
  public DAOException(Throwable cause) {
    super(cause);
  }

  /**
   * Instantiates a new DAO exception.
   *
   * @param cause the cause
   * @param messageKey the message key
   * @param arguments the arguments
   */
  public DAOException(Throwable cause, Enum<?> messageKey, Object... arguments) {
    super(cause);
    this.messageKey = messageKey;
    this.arguments = arguments;
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
