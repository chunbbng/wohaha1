package team.woo.algorithm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import team.woo.domain.Schedule;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 각 TaskType은 하나의 Weight를 가진다.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weight_id")
    private Weight weight;

    // Schedule 클래스와의 1:N 관계 설정
    @OneToMany(mappedBy = "taskType", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    // 기본 생성자 (JPA용)
    public TaskType() {}

    public TaskType(String name, Weight weight) {
        this.name = name;
        this.weight = weight;
    }

    // 초기화 메서드 - 기본 가중치 값 설정
    public static TaskType createDefaultTaskType(String name) {
        Weight defaultWeight;
        switch (name) {
            case "회의":
                defaultWeight = new Weight(0.1, 0.05, 0.05, 0.1);
                break;
            case "학습":
                defaultWeight = new Weight(0.2, 0.1, 0.05, 0.15);
                break;
            case "운동":
                defaultWeight = new Weight(0.15, 0.05, 0.1, 0.1);
                break;
            case "보고서 작성":
                defaultWeight = new Weight(0.25, 0.2, 0.1, 0.2);
                break;
            case "가사일":
                defaultWeight = new Weight(0.1, 0.05, 0.05, 0.05);
                break;
            case "창의적인 작업":
                defaultWeight = new Weight(0.3, 0.15, 0.1, 0.2);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type: " + name);
        }
        return new TaskType(name, defaultWeight);
    }

    public double calculateAdjustedTime(double estimatedTime, int difficulty, int stress, int urgency, int importance) {
        return estimatedTime * (1 + (difficulty * weight.getDifficultyWeight()))
                * (1 + (stress * weight.getStressWeight()))
                * (1 - (urgency * weight.getUrgencyWeight()))
                * (1 + (importance * weight.getImportanceWeight()));  // 계산된 조정 시간을 반환
    }
}