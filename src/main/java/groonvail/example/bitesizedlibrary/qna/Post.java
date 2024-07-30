package groonvail.example.bitesizedlibrary.qna;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    private String answerReference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    private Category category = Category.GENERAL;

    @Column(name = "like_count")
    private long like;

    @Column(name = "dislike_count")
    private long dislike;

    public static Post createContribution(String question, String answer, String answerReference, Difficulty difficulty, Category category) {
        return new Post(question, answer, answerReference, difficulty, category, 0, 0);
    }

    Post(String question, String answer, String answerReference, Difficulty difficulty,
                Category category, long like, long dislike) {
        this.question = question;
        this.answer = answer;
        this.answerReference = answerReference;
        this.difficulty = difficulty;
        this.category = category;
        this.like = like;
        this.dislike = dislike;
    }
}
