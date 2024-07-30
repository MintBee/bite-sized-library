package groonvail.example.bitesizedlibrary.learner;

import groonvail.example.bitesizedlibrary.PostService;
import groonvail.example.bitesizedlibrary.qna.Category;
import groonvail.example.bitesizedlibrary.qna.Difficulty;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LearningController {
    private final PostService postService;

    @GetMapping("/random-post")
    public String selectRandomPost(@RequestParam(value = "difficulty", required = false) Difficulty difficulty,
                                   @RequestParam(value = "category", required = false) Category category,
                                   Model model) {
        Optional<PostDto> randomPost = postService.findRandomPost(difficulty, category);
        if (randomPost.isEmpty()) {
            return "empty-post";
        }
        model.addAttribute("post", randomPost.get());
        model.addAttribute("difficulty", difficulty);
        model.addAttribute("category", category);
        return "post-view";
    }

    @GetMapping("/qna/{postId}")
    public String viewPost(@PathVariable long postId, HttpServletResponse response, Model model) {
        Optional<PostDto> post = postService.findPost(postId);
        if (post.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "empty-post";
        }
        model.addAttribute("post", post.get());
        return "post-view";
    }
}
