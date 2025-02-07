package allowance_manager.allowance_manager.controller;

import allowance_manager.allowance_manager.Service.interfaces.ChildService;
import allowance_manager.allowance_manager.controller.form.ChildForm;
import allowance_manager.allowance_manager.utility.SessionUtil;
import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/child")
public class ChildController {
    private final SessionUtil sessionUtil;
    private final ChildService childService;

    @GetMapping("/createChildForm")
    public String createForm() {
        return "/child/createChildForm";
    }

    @PostMapping("/createChildForm")
    public String registerChild(@Valid ChildForm form, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            log.info("child register error");
            return "redirect:/child/createChildForm";
        }
        log.info("child register success");

        Member parent = (Member) session.getAttribute("member");

        Child child = Child.createChild(parent, form.getName(), form.getPlannedBudget());

        childService.join(child);
        return sessionUtil.isChildExist(parent.getId());
    }

    @GetMapping("childManager")
    public String showChildren(Model model, HttpSession session) {
        Member parent = (Member) session.getAttribute("member");
        List<Child> children = childService.findChildrenByParentId(parent.getId());

        model.addAttribute("children", children);

        return "child/childManager";
    }


    @GetMapping("/edit/{id}")
    public String updateChildForm(@PathVariable Long id, Model model) {
        Child child = childService.findChild(id)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        model.addAttribute("child", child);
        model.addAttribute("id", id);
        return "child/updateChildForm";
    }

    @PostMapping("/updateChildForm")
    public String updateChild(@Valid ChildForm form,BindingResult result) {
        if (result.hasErrors()) {
            log.info("child update error");
            return "/child/childManager";
        }

        log.info(String.valueOf(form.getId()));
        log.info(String.valueOf(form.getName()));
        log.info(String.valueOf(form.getPlannedBudget()));


        childService.update(form.getId(),form.getName(),form.getPlannedBudget());

        log.info("child edit success");

        return "redirect:/child/childManager";
    }

    @PostMapping("/deleteChild")
    public String deleteChild(@Valid ChildForm form,BindingResult result) {
        if (result.hasErrors()) {
            log.info("child update error");
            return "/child/childManager";
        }

        log.info(String.valueOf(form.getId()));
        log.info(String.valueOf(form.getName()));
        log.info(String.valueOf(form.getPlannedBudget()));



        Child child = childService.findChild(form.getId())
                .orElseThrow(() -> {
                    log.error("Child not found with ID: {}", form.getId());
                    return new EntityNotFoundException("자녀를 찾을 수 없습니다");
                });


        childService.withdraw(form.getId());

        log.info("child delete success");

        return "redirect:/child/childManager";
    }

}
