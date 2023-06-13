package com.isa.jjdzr.walletweb.mapper;

import com.isa.jjdzr.walletweb.dto.UserDto;
import com.isa.jjdzr.walletweb.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    UserEntity dtoToEntity(UserDto userDto);
    UserDto entityToDto(UserEntity userEntity);
}
