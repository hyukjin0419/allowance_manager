package allowance_manager.allowance_manager.controller.utility;

import allowance_manager.allowance_manager.Service.interfaces.ChildService;
import allowance_manager.allowance_manager.Service.interfaces.MemberService;
import allowance_manager.allowance_manager.Service.interfaces.MonthlyBudgetService;
import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.domain.MonthlyBudget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SessionUtil {

    private final MemberService memberService;
    private final ChildService childService;
    private final MonthlyBudgetService monthlyBudgetService;

    public String isChildExist(Long memberId) {
        // 자식 정보 가져오기
        List<Child> children = childService.findChildrenByParentId(memberId);

        // 자식이 없으면 자식 등록 화면으로 이동
        if (children.isEmpty()) {
            return "redirect:/child/createChildForm";
        }
        // 자식이 있으면 관리자 화면으로 이동
        return "redirect:/child/childManager";
    }

    public boolean isMonthlyBudgetExist(Long childId) {

        List<MonthlyBudget> monthlyBudgets = monthlyBudgetService.findAllMonthlyBudgetByChildId(childId);

        if (monthlyBudgets.isEmpty()) return false;
        return true;
    }
}

