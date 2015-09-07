package entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrii on 19/07/2015.
 */
@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue
    protected int id;

    @Column(name = "LOGIN", unique = true)
    protected String login;

    @Column(name = "PASSWORD")
    protected String password;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @Cascade(CascadeType.ALL)
    protected UserDetails userDetails;

    protected boolean enabled;
    
    @ManyToMany
    @JoinTable
    private List<Role> roles;
    
    public User() {
    }

    public User(String login, String password, UserDetails userDetails) {
        this.login = login;
        this.password = password;
        this.userDetails = userDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
