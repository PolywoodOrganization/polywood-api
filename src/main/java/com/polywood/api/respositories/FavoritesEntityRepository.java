package com.polywood.api.respositories;

import com.polywood.api.model.FavoritesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesEntityRepository extends JpaRepository<FavoritesEntity, Integer> {

    List<FavoritesEntity> findAllByIduser(int iduser);

    List<FavoritesEntity> findAllByIdmovie(String idmovie);
}
