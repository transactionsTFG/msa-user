package business.mapper;

import business.dto.CreateUserDTO;
import business.user.User;
import business.user.UserDTO;
import javax.annotation.processing.Generated;
import msa.commons.saga.SagaPhases;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-04T15:48:16+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.13 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO entityToDto(User userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserDTO userDTO1 = new UserDTO();

        userDTO1.setId( userDTO.getId() );
        userDTO1.setActive( userDTO.getActive() );
        userDTO1.setBorn( userDTO.getBorn() );
        userDTO1.setEmail( userDTO.getEmail() );
        userDTO1.setName( userDTO.getName() );
        userDTO1.setPassword( userDTO.getPassword() );
        userDTO1.setPhone( userDTO.getPhone() );
        userDTO1.setSurname( userDTO.getSurname() );
        userDTO1.setTypeUser( userDTO.getTypeUser() );

        return userDTO1;
    }

    @Override
    public User createUserDTOtoEntity(CreateUserDTO userDTO, SagaPhases status) {
        if ( userDTO == null && status == null ) {
            return null;
        }

        User user = new User();

        if ( userDTO != null ) {
            user.setBorn( userDTO.getBorn() );
            user.setEmail( userDTO.getEmail() );
            user.setName( userDTO.getName() );
            user.setPassword( userDTO.getPassword() );
            user.setPhone( userDTO.getPhone() );
            user.setSurname( userDTO.getSurname() );
            user.setTypeUser( userDTO.getTypeUser() );
        }
        user.setStatus( status );

        return user;
    }
}
