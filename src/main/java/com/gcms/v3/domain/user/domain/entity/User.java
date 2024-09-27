package com.gcms.v3.domain.user.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User {

    @Id
    @GeneratedValue(generator = "ulidGenerator")
    @GenericGenerator(name = "ulidGenerator", strategy = "com.gcms.v3.global.common.ulid.ULIDGenerator")
    @Column(nullable = false, unique = true)
    private String id;

    @Column(columnDefinition = "VARCHAR(5)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String profileImg;
}
