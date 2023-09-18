package org.example.authentification.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "fulltexts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Fulltext {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String text;
}
