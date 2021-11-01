package com.kitchen.iChef.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
public class AppUser {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
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
