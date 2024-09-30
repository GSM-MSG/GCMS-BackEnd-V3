package com.gcms.v3.domain.attendance.domain.entity;

import com.gcms.v3.domain.attendance.domain.enums.Period;
import com.gcms.v3.domain.attendance.domain.enums.Status;
import com.gcms.v3.domain.club.domain.entity.Club;
import com.gcms.v3.domain.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Attendance {

    @Id
    @GeneratedValue(generator = "ulidGenerator")
    @GenericGenerator(name = "ulidGenerator", strategy = "com.gcms.v3.global.common.ulid.ULIDGenerator")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private byte[] id;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Period period;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}
