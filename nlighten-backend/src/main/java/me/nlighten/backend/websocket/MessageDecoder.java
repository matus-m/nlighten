package me.nlighten.backend.websocket;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

  @Override
  public void init(final EndpointConfig config) {}

  @Override
  public void destroy() {}

  @Override
  public boolean willDecode(final String s) {
    return true;
  }

  @Override
  public Message decode(String stringMessage) throws DecodeException {
    Message message = new Message();
    JsonObject obj = Json.createReader(new StringReader(stringMessage)).readObject();
    message.setMessage(obj.getString("message"));
    message.setSender(obj.getString("sender"));
    JsonArray jsonReceivers = obj.getJsonArray("receivers");
    List<String> receivers = null;
    if (jsonReceivers != null) {
      receivers = new ArrayList<>();
      for (int i = 0; i < jsonReceivers.size(); i++) {
        receivers.add(jsonReceivers.getString(i));
      }
    }
    message.setReceivers(receivers);
    return message;
  }
}
