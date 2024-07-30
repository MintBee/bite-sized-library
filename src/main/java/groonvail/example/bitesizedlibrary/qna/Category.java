package groonvail.example.bitesizedlibrary.qna;

import lombok.Getter;

@Getter
public enum Category {
    GENERAL("General"),
    BACKEND("Backend"),
    FRONTEND("Frontend"),
    GAME("Game"),
    INFRASTRUCTURE("Infrastructure"),
    DATA_SCIENCE("Data Science"),
    ALGORITHM("Algorithm");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}
