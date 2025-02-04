package allowance_manager.allowance_manager.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    //나중에 업데이트 예정
    private String password;

    //==생성자 메소드==//
    public static Member createMember(String name, String password) {
        Member member = new Member();
        member.setName(name);
        member.setPassword(password);

        return member;
    }
}
