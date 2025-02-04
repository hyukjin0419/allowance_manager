package allowance_manager.allowance_manager.controller.form;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름을 입력하세요.")
    private String name;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;
}
