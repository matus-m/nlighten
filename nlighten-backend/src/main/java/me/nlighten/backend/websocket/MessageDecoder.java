package me.nlighten.backend.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

/**
 * The Class MessageDecoder.
 * 
 * @author Lubo
 */
public class MessageDecoder implements Decoder.Text<Message> {

  private static Gson gson = new Gson();

  @Override
  public void init(final EndpointConfig config) {
    System.out.println("init DECODER");
  }

  @Override
  public void destroy() {}

  @Override
  public boolean willDecode(final String s) {
    return true;
  }

  @Override
  public Message decode(String stringMessage) throws DecodeException {
    return gson.fromJson(stringMessage, Message.class);
  }
}
