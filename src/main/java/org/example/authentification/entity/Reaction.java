package org.example.authentification.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "users_reactions")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private java.util.Date createdAt;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "story_id")
    private Long storyId;

    @Column(name = "reaction")
    private String reaction;

    public Reaction(long bookId, long userId, long storyId, String reaction) {
        this.bookId = bookId;
        this.userId = userId;
        this.storyId = storyId;
        this.reaction = reaction;
    }

}
