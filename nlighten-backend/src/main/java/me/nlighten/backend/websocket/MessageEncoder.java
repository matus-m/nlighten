package me.nlighten.backend.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

/**
 * The Class MessageEncoder.
 * 
 * @author lubo
 */
public class MessageEncoder implements Encoder.Text<Message> {

  private static Gson gson = new Gson();

  @Override
  public void init(final EndpointConfig config) {
    System.out.println("init ENCODER");
  }

  @Override
  public void destroy() {}

  @Override
  public String encode(Message message) throws EncodeException {
    return gson.toJson(message, Message.class);
  }
}
