package groonvail.example.bitesizedlibrary;

import groonvail.example.bitesizedlibrary.contributor.ContributionDto;
import groonvail.example.bitesizedlibrary.infra.PostRepository;
import groonvail.example.bitesizedlibrary.learner.PostDto;
import groonvail.example.bitesizedlibrary.qna.Category;
import groonvail.example.bitesizedlibrary.qna.Difficulty;
import groonvail.example.bitesizedlibrary.qna.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final Random random;

    public long contribute(ContributionDto contributionDto) {
        Post savedPost = postRepository.save(contributionDto.toPost());
        return savedPost.getId();
    }

    @Transactional
    public Optional<PostDto> findPost(long postId) {
        return postRepository.findById(postId).map(PostDto::fromPost);
    }

    @Transactional(readOnly = true)
    public Optional<PostDto> findRandomPost() {
        return postRepository.findRandomPost(random).map(PostDto::fromPost);
    }

    @Transactional(readOnly = true)
    public Optional<PostDto> findRandomPost(Difficulty difficulty, Category category) {
        return postRepository.findRandomPost(random, difficulty, category).map(PostDto::fromPost);
    }
}
