package groonvail.example.bitesizedlibrary;

import groonvail.example.bitesizedlibrary.infra.PostRepository;
import groonvail.example.bitesizedlibrary.infra.vote.LikeRecord;
import groonvail.example.bitesizedlibrary.infra.vote.LikeRecordPK;
import groonvail.example.bitesizedlibrary.infra.vote.LikeRepository;
import groonvail.example.bitesizedlibrary.qna.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public boolean hasUserLiked(long postId, String userName) {
        return likeRepository.existsById(new LikeRecordPK(userName, postId));
    }

    @Transactional
    public void like(long postId, String userName) {
        likeRepository.save(new LikeRecord(userName, postId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post to like not found for id: " + postId));
        post.like();
    }
}
