package groonvail.example.bitesizedlibrary.infra;

import groonvail.example.bitesizedlibrary.qna.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRandomRepository {
}
