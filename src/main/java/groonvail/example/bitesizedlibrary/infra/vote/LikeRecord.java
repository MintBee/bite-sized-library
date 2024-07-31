package groonvail.example.bitesizedlibrary.infra.vote;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "like_record")
@IdClass(LikeRecordPK.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LikeRecord {
    @Id
    private String userName;

    @Id
    private Long postId;
}
