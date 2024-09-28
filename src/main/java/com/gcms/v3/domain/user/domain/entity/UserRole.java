package com.gcms.v3.domain.user.domain.entity;

import com.gcms.v3.domain.user.domain.enums.Authority;
import jakarta.persistence.*;
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
public class UserRole {

    @Id
    @GeneratedValue(generator = "ulidGenerator")
    @GenericGenerator(name = "ulidGenerator", strategy = "com.gcms.v3.global.common.ulid.ULIDGenerator")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private byte[] id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
