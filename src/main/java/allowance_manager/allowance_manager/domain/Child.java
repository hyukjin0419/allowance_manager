package allowance_manager.allowance_manager.domain;

import allowance_manager.allowance_manager.Service.interfaces.ChildService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Child {

    @Id @GeneratedValue
    @Column(name = "child_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member parent;

    private Long plannedBudget;

    //==연관 관계 메소드==// 없어도 됨. 단방향 관계이기 때

    //==생성 메서드==//
    public static Child createChild(Member member, String name, Long plannedBudget) {
        Child child = new Child();
        child.setParent(member);
        child.setName(name);
        child.setPlannedBudget(plannedBudget);

        return child;
    }

    //==비지니스 로직==// 미정
}
