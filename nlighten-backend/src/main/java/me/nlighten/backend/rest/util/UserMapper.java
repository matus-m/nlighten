package me.nlighten.backend.rest.util;

import java.util.List;

import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import me.nlighten.backend.db.model.User;
import me.nlighten.backend.rest.model.UserDTO;

/**
 * The interface DTOUserMapper.
 * 
 * @author Dorin Gheorghe Brage
 */
@Mapper
public interface UserMapper {

  /**
   * From UserDTO to User, with all collections.
   *
   * @param userDTO the user dto
   * @return the user
   */
  User toUser(UserDTO userDTO);


  UserDTO toUserDTO(User user);


  /**
   * From User to UserDTO, without all collections.
   *
   * @param user the user
   * @return the user
   */
  // TODO Add all ignored files
  @Maps(withIgnoreFields = {})
  UserDTO toUserDTOWithoutCollections(User user);


  // TODO Add all ignored files
  @Maps(withIgnoreFields = {})
  User toUser(UserDTO userDTO, User user);


  @Maps
  List<UserDTO> toUsersDTO(List<User> users);
}
