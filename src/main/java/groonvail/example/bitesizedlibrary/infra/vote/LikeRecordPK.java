package groonvail.example.bitesizedlibrary.infra.vote;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LikeRecordPK implements Serializable {
    private String userName;
    private Long postId;

    public LikeRecordPK(String userName, Long postId) {
        this.userName = userName;
        this.postId = postId;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LikeRecordPK that)) {
            return false;
        }

        return Objects.equals(userName, that.userName) && Objects.equals(postId,
                that.postId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(userName);
        result = 31 * result + Objects.hashCode(postId);
        return result;
    }
}
