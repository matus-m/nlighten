package me.nlighten.backend.websocket;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Message.
 *
 * @author Lubo
 */
public class Message {

  /**
   * The message.
   */
  private String message;

  /**
   * The receivers.
   */
  private List<String> receivers = new ArrayList<>();

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getReceivers() {
    return receivers;
  }

  public void setReceivers(List<String> receivers) {
    this.receivers = receivers;
  }

}
