package me.nlighten.backend.websocket;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Message.
 * 
 * @author Lubo
 */
@Getter
@Setter
public class Message {

  /** The message. */
  private String message;

  /** The receivers. */
  private List<String> receivers = new ArrayList<>();
}
