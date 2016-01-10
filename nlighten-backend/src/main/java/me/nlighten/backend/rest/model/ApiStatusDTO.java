package me.nlighten.backend.rest.model;

import java.util.Date;

import me.nlighten.backend.rest.model.enums.ServerStatusEnum;

/**
 * @author Martin
 *
 */
public class ApiStatusDTO {

  private ServerStatusEnum status;
  // @XmlJavaTypeAdapter(type = Date.class, value = DateAdapter.class)
  private Date serverTime;

  public Date getServerTime() {
    return serverTime;
  }

  public void setServerTime(Date serverTime) {
    this.serverTime = serverTime;
  }

  public ServerStatusEnum getStatus() {
    return status;
  }

  public void setStatus(ServerStatusEnum status) {
    this.status = status;
  }
}
