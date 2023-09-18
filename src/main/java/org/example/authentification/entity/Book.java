package org.example.authentification.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @CreationTimestamp
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date createdAt;

    @Column(name = "main_story_id")
    private Long mainStoryId;

    @Column(name = "title")
    private String title;

//    @ElementCollection
//    private Set<Story> stories;

//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinTable(name = "stories")
//    private Set<Story> stories;

}
