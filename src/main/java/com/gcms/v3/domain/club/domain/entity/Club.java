package com.gcms.v3.domain.club.domain.entity;

import com.gcms.v3.domain.attendance.domain.enums.Status;
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
public class Club {

    @Id
    @GeneratedValue(generator = "ulidGenerator")
    @GenericGenerator(name = "ulidGenerator", strategy = "com.gcms.v3.global.common.ulid.ULIDGenerator")
    @Column(nullable = false, unique = true)
    private String id;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String clubImg;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String introduction;

    @Column(columnDefinition = "TEXT")
    private String notionLink;

    @Column(nullable = false)
    private Boolean recruitment;

    @Column(columnDefinition = "VARCHAR(20)")
    private String startedAt;

    @Column(columnDefinition = "VARCHAR(20)")
    private String endedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
