package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.State;
import umc.spring.domain.mapping.MissionState;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 20)
    private String password;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalTime inactiveDate;

    @Column(name = "mypoint")
    private Long mypoint;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MissionState> missionStateList = new ArrayList<>();

}

