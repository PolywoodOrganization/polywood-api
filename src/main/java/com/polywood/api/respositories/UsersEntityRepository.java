package com.polywood.api.respositories;

import com.polywood.api.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersEntityRepository extends JpaRepository<UsersEntity, Integer>
{
    UsersEntity findByLogin(String login);
}