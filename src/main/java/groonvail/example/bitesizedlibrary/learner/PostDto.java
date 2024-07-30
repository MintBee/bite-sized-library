package groonvail.example.bitesizedlibrary.learner;

import groonvail.example.bitesizedlibrary.qna.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    private long id;
    private String question;
    private String answer;
    private String answerReference;
    private String difficulty;
    private String category;
    private long like;
    private long dislike;

    public static PostDto fromPost(Post post) {
        return new PostDto(post.getId(), post.getQuestion(), post.getAnswer(), post.getAnswerReference(), post.getDifficulty().name(), post.getCategory().name(), post.getLike(), post.getDislike());
    }
}
