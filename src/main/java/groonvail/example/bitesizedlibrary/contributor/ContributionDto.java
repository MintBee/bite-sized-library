package groonvail.example.bitesizedlibrary.contributor;

import groonvail.example.bitesizedlibrary.qna.Category;
import groonvail.example.bitesizedlibrary.qna.Difficulty;
import groonvail.example.bitesizedlibrary.qna.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContributionDto {
    @NotBlank
    @Size(max = 500)
    private String question;

    @NotBlank
    @Size(max = 500)
    private String answer;

    private String answerReference;

    @NotNull
    private Difficulty difficulty;
    @NotNull
    private Category category;

    public Post toPost() {
        return Post.createContribution(question, answer, answerReference, difficulty, category);
    }
}

