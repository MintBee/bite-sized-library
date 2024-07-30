package groonvail.example.bitesizedlibrary.qna;

import lombok.Getter;

@Getter
public enum Difficulty {
    STUDENT("Student"),
    JUNIOR("Junior"),
    INTERMEDIATE("Intermediate"),
    SENIOR("Senior");

    private final String displayName;

    Difficulty(String displayName) {
        this.displayName = displayName;
    }
}
