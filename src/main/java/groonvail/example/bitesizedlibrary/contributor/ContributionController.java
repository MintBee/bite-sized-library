package groonvail.example.bitesizedlibrary.contributor;

import groonvail.example.bitesizedlibrary.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ContributionController {
    private final PostService postService;

    @GetMapping("/contribution")
    public String contributionPage(Model model) {
        model.addAttribute("contributionDto", new ContributionDto());
        return "contribution-page";
    }

    @PostMapping("/contribution")
    public String contribute(@Valid @ModelAttribute ContributionDto contributionDto, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "contribution-page";
        }

        long postId = postService.contribute(contributionDto);
        log.info("Contribution created: {}", postId);
        return "redirect:/qna/" + postId;
    }
}

