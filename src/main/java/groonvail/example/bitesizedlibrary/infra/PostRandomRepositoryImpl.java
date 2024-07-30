package groonvail.example.bitesizedlibrary.infra;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import groonvail.example.bitesizedlibrary.qna.Category;
import groonvail.example.bitesizedlibrary.qna.Difficulty;
import groonvail.example.bitesizedlibrary.qna.Post;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.Random;

import static com.querydsl.core.types.ExpressionUtils.count;
import static groonvail.example.bitesizedlibrary.qna.QPost.post;

public class PostRandomRepositoryImpl implements PostRandomRepository{
    private final JPAQueryFactory queryFactory;

    public PostRandomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Post> findRandomPost(Random random) {
        return findRandomPost(random, null, null);
    }

    @Override
    public Optional<Post> findRandomPost(Random random, Difficulty difficulty, Category category) {
        Long postCount = queryFactory.select(count(post))
                .from(post)
                .where(eqDifficulty(difficulty), eqCategory(category))
                .fetchOne();
        assert postCount != null;
        if (postCount == 0L) {
            return Optional.empty();
        }

        int randomIndex = random.nextInt(postCount.intValue());

        Post foundPost = queryFactory.selectFrom(post)
                .where(eqDifficulty(difficulty), eqCategory(category))
                .offset(randomIndex)
                .limit(1)
                .fetchOne();
        assert foundPost != null;
        return Optional.of(foundPost);
    }

    private Predicate eqDifficulty(Difficulty difficulty) {
        return difficulty == null ? null : post.difficulty.eq(difficulty);
    }

    private Predicate eqCategory(Category category) {
        return category == null ? null : post.category.eq(category);
    }
}
