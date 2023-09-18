package org.example.authentification.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stories")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Story {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "imgsrc")
    private String imgSrc;

    @Column(name = "fulltext_preview")
    private String fulltextPreview;

    @Column(name = "fulltext_id")
    private Long fulltextId;

    @Column(name = "is_new")
    private boolean isNew;

    @Column(name = "source")
    private String source;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date createdAt;
}
