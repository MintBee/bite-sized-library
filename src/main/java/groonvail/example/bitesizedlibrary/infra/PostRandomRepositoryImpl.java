package groonvail.example.bitesizedlibrary.infra;

import groonvail.example.bitesizedlibrary.qna.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
public class PostRandomRepositoryImpl implements PostRandomRepository{
    private final EntityManager em;

    @Override
    public Optional<Post> findRandomPost(Random random) {
        Long postCount = em.createQuery("select count(p) from Post p", Long.class)
                .getSingleResult();
        if (postCount == 0L) {
            return Optional.empty();
        }

        int randomIndex = random.nextInt(postCount.intValue());
        return Optional.of(em.createQuery("select p from Post p", Post.class)
                .setFirstResult(randomIndex)
                .setMaxResults(1)
                .getSingleResult());
    }
}
