package me.nlighten.backend.rest.model;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import me.nlighten.backend.rest.model.enums.ServerStatusEnum;
import me.nlighten.backend.rest.util.DateAdapter;

/**
 * @author Martin
 *
 */
public class ApiStatusDTO {

  private ServerStatusEnum status;
  @XmlJavaTypeAdapter(type = Date.class, value = DateAdapter.class)
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
