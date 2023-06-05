package com.karrot.domain.demo_postgis_docker.service;

import com.karrot.domain.demo_postgis_docker.Users;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    public Users getUsersById(UUID id);

    public List<Users> getAllUsers();

    public Users save(Users user);
}
