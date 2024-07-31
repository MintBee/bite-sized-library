package groonvail.example.bitesizedlibrary.infra.vote;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "dislike_record")
@IdClass(DislikeRecordPK.class)
@Getter
public class DislikeRecord {
    @Id
    private String userName;

    @Id
    private Long postId;
}
