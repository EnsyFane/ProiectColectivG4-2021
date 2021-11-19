package com.kitchen.iChef.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "app_user")
public class AppUser {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String firstName;
    private String lastName;

    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    private ZonedDateTime joinedDate;
    private ZonedDateTime lastOnline;
    private Boolean isAdmin;
    private String hashedPassword;

    @PrePersist
    private void ensureId() {
        this.setUserId(UUID.randomUUID().toString());
    }
}
