package me.nlighten.backend.websocket;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.Session;

import org.slf4j.Logger;

import me.nlighten.backend.db.dao.EventDAO;
import me.nlighten.backend.db.model.Event;

/**
 * The Class DeviceSessionHandler.
 * 
 * @author Lubo
 */
@ApplicationScoped
public class DeviceSessionHandler {

  /** The logger. */
  @Inject
  private Logger logger;

  /** The event dao. */
  @EJB
  private EventDAO eventDAO;

  /** The sessions. */
  private final Set<Session> sessions = new HashSet<Session>();

  /** The events. */
  private List<Event> events;

  /**
   * Send to all connected sessions.
   *
   * @param message the message
   * @param event the event
   * @param userId the user id
   */
  public void sendToAllConnectedSessions(Message message, String event, String userId) {
    for (Session session : sessions) {
      try {
        if (session.isOpen() && session.getUserProperties().get("event").equals(event)
            && !session.getUserProperties().get("userId").equals(userId)) {
          if (message.getReceivers() == null || message.getReceivers().isEmpty()) {
            session.getBasicRemote().sendObject(message);
          } else if (message.getReceivers() != null
              && message.getReceivers().contains(session.getUserProperties().get("userId"))) {
            session.getBasicRemote().sendObject(message);
          }
        }
      } catch (Exception e) {
        logger.error("Error durring sending message to all conected users: " + e.getMessage());
      }
    }
  }

  /**
   * Check and connect to event.
   *
   * @param session the session
   * @param event the event
   * @param userId the user id
   */
  public void checkAndConnectToEvent(Session session, String event, String userId) {
    try {
      Event foundEvent = eventDAO.findByTitle(event);
      Date currentDateAndTime = Calendar.getInstance().getTime();
      // check whether event exist and whether event is started
      if (currentDateAndTime.compareTo(foundEvent.getStartTime()) >= 0) {
        session.getUserProperties().put("event", event);
        session.getUserProperties().put("userId", userId);
        addSession(session);
      }
    } catch (Exception e) {
      logger.error("Error durring connecting to event: " + e.getMessage());
    }
  }

  /**
   * Adds the session.
   *
   * @param session the session
   */
  public void addSession(Session session) {
    sessions.add(session);
  }

  /**
   * Removes the session.
   *
   * @param session the session
   */
  public void removeSession(Session session) {
    sessions.remove(session);
  }
}
