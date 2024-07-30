package groonvail.example.bitesizedlibrary.infra;

import groonvail.example.bitesizedlibrary.qna.Category;
import groonvail.example.bitesizedlibrary.qna.Difficulty;
import groonvail.example.bitesizedlibrary.qna.Post;

import java.util.Optional;
import java.util.Random;

public interface PostRandomRepository {
    Optional<Post> findRandomPost(Random random);

    Optional<Post> findRandomPost(Random random, Difficulty difficulty, Category category);
}
