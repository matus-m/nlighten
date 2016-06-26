package me.nlighten.backend.websocket;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

  private String message;
  private String sender;
  private List<String> receivers = new ArrayList<>();
}
