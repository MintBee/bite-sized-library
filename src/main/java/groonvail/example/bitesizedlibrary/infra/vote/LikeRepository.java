package groonvail.example.bitesizedlibrary.infra.vote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeRecord, LikeRecordPK> {

}
