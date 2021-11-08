package com.kitchen.iChef.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class Token {
    @Id
    private String tokenId;
    private String userId;
    private ZonedDateTime expirationDate;
    private String value;

    public boolean isExpired() {
        return this.expirationDate.isBefore(ZonedDateTime.now());
    }

    @PrePersist
    private void ensureId() {
        this.setTokenId(UUID.randomUUID().toString());
    }
}
