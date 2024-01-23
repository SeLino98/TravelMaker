package com.ssafy.gumibom.domain.pamphlet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor
@AllArgsConstructor
public class Pamphlet {

    @Id @GeneratedValue
    @Column(name = "pamphlet_id")
    private long id;

    private String title;
    private int love;
    private LocalDateTime createTime;
}
