package me.nlighten.backend.websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@ServerEndpoint(value = "/courses/{event}", encoders = MessageEncoder.class,
    decoders = MessageDecoder.class)
public class WebSocketEndpoint {

  final Logger logger = LoggerFactory.getLogger(WebSocketEndpoint.class);

  @Inject
  private DeviceSessionHandler sessionHandler;

  @OnMessage
  public void onMessage(Message message, Session session) {
    String event = (String) session.getUserProperties().get("event");
    sessionHandler.sendToAllConnectedSessions(message, event);
  }

  @OnError
  public void onError(Throwable t) {
    logger.info("Websocket error " + t.getMessage());
  }

  @OnOpen
  public void onOpen(Session session, @PathParam("event") final String event) {
    sessionHandler.checkAndConnectToEvent(session, event);
  }

  @OnClose
  public void onClose(Session session, CloseReason reason) {
    sessionHandler.removeSession(session);
    logger.info("Closing a WebSocket due to " + reason.getReasonPhrase());
  }
}
