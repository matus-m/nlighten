package me.nlighten.backend.websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class WebSocketEndpoint.
 */
@ApplicationScoped
@ServerEndpoint(value = "/courses/{event}", encoders = MessageEncoder.class,
    decoders = MessageDecoder.class)
public class WebSocketEndpoint {

  /** The logger. */
  final Logger logger = LoggerFactory.getLogger(WebSocketEndpoint.class);

  /** The session handler. */
  @Inject
  private DeviceSessionHandler sessionHandler;

  /**
   * On message.
   *
   * @param message the message
   * @param session the session
   */
  @OnMessage
  public void onMessage(Message message, Session session) {
    String event = (String) session.getUserProperties().get("event");
    sessionHandler.sendToAllConnectedSessions(message, event);
  }

  /**
   * On error.
   *
   * @param t the t
   */
  @OnError
  public void onError(Throwable t) {
    logger.info("Websocket error " + t.getMessage());
  }

  /**
   * On open.
   *
   * @param session the session
   * @param event the event
   */
  @OnOpen
  public void onOpen(Session session, @PathParam("event") final String event) {
    sessionHandler.checkAndConnectToEvent(session, event);
  }

  /**
   * On close.
   *
   * @param session the session
   * @param reason the reason
   */
  @OnClose
  public void onClose(Session session, CloseReason reason) {
    sessionHandler.removeSession(session);
    logger.info("Closing a WebSocket due to " + reason.getReasonPhrase());
  }
}