package team.woo.algorithm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double difficultyWeight;
    private double stressWeight;
    private double urgencyWeight;
    private double importanceWeight;

    // 기본 생성자 (JPA용)
    public Weight() {}

    // 모든 필드를 받는 생성자
    public Weight(double difficultyWeight, double stressWeight, double urgencyWeight, double importanceWeight) {
        this.difficultyWeight = difficultyWeight;
        this.stressWeight = stressWeight;
        this.urgencyWeight = urgencyWeight;
        this.importanceWeight = importanceWeight;
    }
}
