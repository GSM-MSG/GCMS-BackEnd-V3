package com.gcms.v3.domain.applicant.domain.entity;

import com.gcms.v3.domain.club.domain.entity.Club;
import com.gcms.v3.domain.user.domain.entity.User;
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
public class Applicant {

    @Id
    @GeneratedValue(generator = "ulidGenerator")
    @GenericGenerator(name = "ulidGenerator", strategy = "com.gcms.v3.global.common.ulid.ULIDGenerator")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private byte[] id;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
