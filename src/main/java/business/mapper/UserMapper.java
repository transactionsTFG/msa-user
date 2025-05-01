package business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import business.dto.CreateUserDTO;
import business.user.User;
import business.user.UserDTO;
import msa.commons.saga.SagaPhases;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO entityToDto(User userDTO);
    
    @Mapping(target = "active", expression = "java(false)")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    User createUserDTOtoEntity(CreateUserDTO userDTO, SagaPhases status);
}
