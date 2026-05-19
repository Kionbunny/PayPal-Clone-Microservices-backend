package com.paypal.user_service.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


@Entity // this User class is stored as Table in DB
@Data // lombok manages getters & setters for our fields
@Table(name="app_user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String role;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;

//    public void setRole(String roleUser) {
//        this.role = role;
//    }

}
