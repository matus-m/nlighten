package me.nlighten.backend.rest.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import me.nlighten.backend.rest.model.enums.ServerStatusEnum;

/**
 * @author Martin
 *
 */
@Getter
@Setter
public class ApiStatusDTO {

  private ServerStatusEnum status;
  private Date serverTime;
}
