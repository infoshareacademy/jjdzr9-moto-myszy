package com.isa.jjdzr.walletweb.mapper;
import com.isa.jjdzr.walletweb.dto.UserDto;
import com.isa.jjdzr.walletweb.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    @Test
    void testDtoToEntity() {

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("john_doe");

        UserMapper userMapper = UserMapper.MAPPER;

        UserEntity userEntity = userMapper.dtoToEntity(userDto);

        Assertions.assertEquals(userDto.getId(), userEntity.getId());
        Assertions.assertEquals(userDto.getUsername(), userEntity.getUsername());
    }

    @Test
    void testEntityToDto() {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("john_doe");

        UserMapper userMapper = UserMapper.MAPPER;

        UserDto userDto = userMapper.entityToDto(userEntity);

        Assertions.assertEquals(userEntity.getId(), userDto.getId());
        Assertions.assertEquals(userEntity.getUsername(), userDto.getUsername());
    }
}
