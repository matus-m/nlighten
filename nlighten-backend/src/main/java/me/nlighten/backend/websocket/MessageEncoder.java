package me.nlighten.backend.websocket;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * The Class MessageEncoder.
 */
public class MessageEncoder implements Encoder.Text<Message> {

  @Override
  public void init(final EndpointConfig config) {}

  @Override
  public void destroy() {}

  @Override
  public String encode(Message message) throws EncodeException {
    return Json.createObjectBuilder().add("message", message.getMessage())
        .add("sender", message.getSender()).build().toString();
  }
}