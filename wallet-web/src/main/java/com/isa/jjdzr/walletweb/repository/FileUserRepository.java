package com.isa.jjdzr.walletweb.repository;

import com.isa.jjdzr.walletcore.common.FileRepository;
import com.isa.jjdzr.walletweb.dto.User;
import org.springframework.stereotype.Component;
@Component
public class FileUserRepository extends FileRepository<User> implements UserRepository{

}
