package groonvail.example.bitesizedlibrary;

import groonvail.example.bitesizedlibrary.contributor.ContributionDto;
import groonvail.example.bitesizedlibrary.qna.Category;
import groonvail.example.bitesizedlibrary.qna.Difficulty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TestConfig {
    private final PostService postService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        String presetQuestion = "커뮤니티 게시글의 조회 성능을 개선하기 위한 방법을 논하시오.";
        String presetAnswer = "커뮤니티 게시글은 한번 작성되면 잘 변경되지 않고, 수정되어도 실시간으로 수정이 적용되지 않아도 되기 때문에 in-memory 캐시를 이용할 수 있습니다.";
        ContributionDto contributionDto = ContributionDto.builder()
                .question(presetQuestion)
                .answer(presetAnswer)
                .difficulty(Difficulty.JUNIOR)
                .category(Category.BACKEND)
                .build();
        postService.contribute(contributionDto);
        log.info("Preset question is contributed: {}", presetQuestion);
    }
}
