package allowance_manager.allowance_manager.controller.form;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChildForm {

    private  Long id;

    @NotEmpty(message =  "자녀의 이름을 입력하세요.")
    private String name;

    @NotNull(message = "자녀에게 지급할 총 버젯을 입력하세요.")
    private Long plannedBudget;
}
