package com.polywood.api.dto;

import java.sql.Date;

public class FavoritesDto {

   private int idfavorite;
   private String idmovie;
   private String commentary;
   private Date added;
   private int iduser;

    public String getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(String idmovie) {
        this.idmovie = idmovie;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdfavorite() {
        return idfavorite;
    }

    public void setIdfavorite(int idfavorite) {
        this.idfavorite = idfavorite;
    }

    @Override
    public String toString() {
        return "FavoritesDto{" +
                "idmovie='" + idmovie + '\'' +
                ", commentary='" + commentary + '\'' +
                ", added=" + added +
                ", iduser=" + iduser +
                '}';
    }
}
