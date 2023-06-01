package com.isa.jjdzr.walletweb.repository;

import com.isa.jjdzr.walletcore.common.FileRepository;
import com.isa.jjdzr.walletweb.dto.UserDto;
import org.springframework.stereotype.Component;
@Component
public class FileUserRepository extends FileRepository<UserDto> implements UserRepository{

}
