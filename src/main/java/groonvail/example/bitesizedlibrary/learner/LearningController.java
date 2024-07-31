package groonvail.example.bitesizedlibrary.learner;

import groonvail.example.bitesizedlibrary.LikeService;
import groonvail.example.bitesizedlibrary.PostService;
import groonvail.example.bitesizedlibrary.qna.Category;
import groonvail.example.bitesizedlibrary.qna.Difficulty;
import groonvail.example.bitesizedlibrary.util.UsernameResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LearningController {
    private final PostService postService;
    private final LikeService likeService;

    @GetMapping("/random-post")
    public String selectRandomPost(@RequestParam(value = "difficulty", required = false) Difficulty difficulty,
                                   @RequestParam(value = "category", required = false) Category category,
                                   RedirectAttributes redirectAttributes) {
        Optional<PostDto> randomPostOrNull = postService.findRandomPost(difficulty, category);
        if (randomPostOrNull.isEmpty()) {
            return "empty-post";
        }

        PostDto randomPost = randomPostOrNull.get();

        redirectAttributes.addAttribute("difficulty", difficulty);
        redirectAttributes.addAttribute("category", category);
        redirectAttributes.addFlashAttribute("post", randomPost);
        return "redirect:/qna/" + randomPost.getId();
    }

    @GetMapping("/qna/{postId}")
    public String viewPost(@PathVariable long postId,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           Model model) {
        if (!model.containsAttribute("post")) {
            PostDto postOrNull = postService.findPost(postId).orElse(null);
            model.addAttribute("post", postOrNull);
        }

        if (model.getAttribute("post") == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "empty-post";
        }

        PostDto post = (PostDto) model.getAttribute("post");
        assert post != null;

        String userIdentity = UsernameResolver.ipPortToUsername(request);
        if (likeService.hasUserLiked(post.getId(), userIdentity)) {
            model.addAttribute("already_liked", true);
        }

        return "post-view";
    }

    @PostMapping("/qna/{postId}/like")
    @ResponseBody
    public void like(@PathVariable("postId") long postId,
                     HttpServletRequest request,
                     HttpServletResponse response) {
        String userIdentity = UsernameResolver.ipPortToUsername(request);
        if (likeService.hasUserLiked(postId, userIdentity)) {
            log.info("User {} failed to like post {} since liked again", userIdentity, postId);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }
        log.info("User {} liked post {}", userIdentity, postId);
        likeService.like(postId, userIdentity);
    }
}
