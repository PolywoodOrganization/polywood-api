package com.polywood.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "polywood")
public class UsersEntity {
    private int iduser;
    private String login;
    private String password;
    private String firstname;
    private String lastname;

    @Id @GeneratedValue(strategy= javax.persistence.GenerationType.IDENTITY)
    @Column(name = "iduser")
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "login", unique = true)
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return iduser == that.iduser &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(iduser, login, password, firstname, lastname);
    }
}
