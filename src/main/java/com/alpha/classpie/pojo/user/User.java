package com.alpha.classpie.pojo.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends org.springframework.security.core.userdetails.User {
    protected Integer id;

    protected String name;

    protected String school;

    //@Pattern(regexp = "^[0-9a-zA-Z]{9}$")
    protected String accountNumber;

    //@Email
    protected String emailNumber;

    protected String telephoneNumber;

    protected String password;

    protected List<Role> roles;

    public User(){
        this(UUID.randomUUID().toString(),UUID.randomUUID().toString(),new ArrayList<>());
    }

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public User(Integer id, String name, String school, String accountNumber, String emailNumber, String telephoneNumber, String password, List<Role> roles) {
        this(accountNumber,password,roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName())).collect(Collectors.toList()));
        this.id = id;
        this.roles=roles;
        this.name = name;
        this.school = school;
        this.accountNumber = accountNumber;
        this.emailNumber = emailNumber;
        this.telephoneNumber = telephoneNumber;
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmailNumber() {
        return emailNumber;
    }

    public void setEmailNumber(String emailNumber) {
        this.emailNumber = emailNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
