package business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import business.dto.CreateUserDTO;
import business.user.User;
import business.user.UserDTO;
import msa.commons.saga.SagaPhases;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO entityToDto(User userDTO);
    User createUserDTOtoEntity(CreateUserDTO userDTO, SagaPhases status);
}
