package groonvail.example.bitesizedlibrary.learner;

import groonvail.example.bitesizedlibrary.PostService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LearningController {
    private final PostService postService;

    @GetMapping("/random-post")
    public String selectRandomPost(Model model) {
        Optional<PostDto> randomPost = postService.findRandomPost();
        if (randomPost.isEmpty()) {
            return "empty-post";
        }
        model.addAttribute("post", randomPost.get());
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
