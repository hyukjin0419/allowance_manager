package allowance_manager.allowance_manager.controller;

import allowance_manager.allowance_manager.Service.interfaces.ChildService;
import allowance_manager.allowance_manager.controller.form.ChildForm;
import allowance_manager.allowance_manager.controller.form.MemberForm;
import allowance_manager.allowance_manager.controller.utility.SessionUtil;
import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.domain.Member;
import allowance_manager.allowance_manager.repository.ChildRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChildController {
    private final SessionUtil sessionUtil;
    private final ChildService childService;

    @GetMapping("/child/createChildForm")
    public String createForm() {
        return "/child/createChildForm";
    }

    @PostMapping("child/createChildForm")
    public String registerChild(@Valid ChildForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            log.info("child register error");
            return "redirect:/child/createChildForm";
        }

        Member parent = (Member) session.getAttribute("member");

        Child child = Child.createChild(parent, form.getName(), form.getPlannedBudget());

        childService.join(child);
        return sessionUtil.isChildExist(parent.getId());
    }

    @GetMapping("child/childManager")
    public String showChildren(Model model, HttpSession session) {
        Member parent = (Member) session.getAttribute("member");
        List<Child> children = childService.findChildrenByParentId(parent.getId());

        model.addAttribute("children", children);

        return "child/childManager";
    }
}
