package com.polywood.api.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "favorites", schema = "polywood", catalog = "")
public class FavoritesEntity {
    private int idfavorite;
    private String idmovie;
    private String commentary;
    private Date added;
    private int iduser;

    public FavoritesEntity() {

    }

    @Id
    @Column(name = "idfavorite")
    public int getIdfavorite() {
        return idfavorite;
    }

    public void setIdfavorite(int idfavorite) {
        this.idfavorite = idfavorite;
    }

    @Basic
    @Column(name = "idmovie")
    public String getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(String idmovie) {
        this.idmovie = idmovie;
    }

    @Basic
    @Column(name = "commentary")
    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    @Basic
    @Column(name = "added")
    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesEntity that = (FavoritesEntity) o;
        return idfavorite == that.idfavorite &&
                Objects.equals(idmovie, that.idmovie) &&
                Objects.equals(commentary, that.commentary) &&
                Objects.equals(added, that.added);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idfavorite, idmovie, commentary, added);
    }

    @Basic
    @Column(name = "iduser")
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
