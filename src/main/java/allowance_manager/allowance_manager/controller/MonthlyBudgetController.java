package allowance_manager.allowance_manager.controller;

import allowance_manager.allowance_manager.Service.interfaces.ChildService;
import allowance_manager.allowance_manager.Service.interfaces.MonthlyBudgetService;
import allowance_manager.allowance_manager.controller.form.MonthlyBudgetForm;
import allowance_manager.allowance_manager.utility.SessionUtil;
import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.domain.MonthlyBudget;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MonthlyBudgetController {
    private final ChildService childService;
    private final SessionUtil sessionUtil;
    private final MonthlyBudgetService monthlyBudgetService;

    @GetMapping("/navigateToMonthlyBudget/{id}")
    public String navigateToMonthlyBudget(@PathVariable Long id) {
        if (sessionUtil.isMonthlyBudgetExist(id)) {
            return "redirect:/monthlyBudget/monthlyBudgetManager/" + id;
        } else {
            return "redirect:/monthlyBudget/createMonthlyBudgetForm/" + id;
        }
    }

    @GetMapping("/monthlyBudget/createMonthlyBudgetForm/{id}")
    public String createMonthlyBudgetForm(@PathVariable Long id, Model model) {
        Child child = childService.findChild(id)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        model.addAttribute("child", child);

        return "/monthlyBudget/createMonthlyBudgetForm";
    }

    @PostMapping("/monthlyBudget/createMonthlyBudgetForm")
    public String registerMonthlyBudget(@Valid MonthlyBudgetForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.error("monthlyBudget register error");
            return "redirect:/child/childManager";
        }

        Child child = childService.findChild(form.getChildId())
                .orElseThrow(() -> new EntityNotFoundException("child not found"));
        MonthlyBudget monthlyBudget = MonthlyBudget.createMonthlyBudget(child, form.getYearMonth(), form.getTotalBudget());

        monthlyBudgetService.add(monthlyBudget);

        return "redirect:/navigateToMonthlyBudget/" + form.getChildId();
    }

    @GetMapping("/monthlyBudget/monthlyBudgetManager/{id}")
    public String showMonthlyBudget(@PathVariable Long id, Model model) {
        Child child = childService.findChild(id)
                .orElseThrow(() -> new EntityNotFoundException("Child not found"));

        List<MonthlyBudget> monthlyBudgets = monthlyBudgetService.findAllMonthlyBudgetByChildId(id);

        model.addAttribute("monthlyBudgets", monthlyBudgets);

        return "monthlyBudget/monthlyBudgetManager";
    }

    @GetMapping("/monthlyBudget/monthlyBudgetEditor/{id}")
    public String showMonthlyBudgetEditor(@PathVariable Long id, Model model) {
        log.info("Id: " + id);
        MonthlyBudget monthlyBudget = monthlyBudgetService.findOneMonthlyBudget(id)
                .orElseThrow(() -> new EntityNotFoundException("Monthly Budget Not Found"));

        model.addAttribute("monthlyBudget", monthlyBudget);

        return "/monthlyBudget/monthlyBudgetEditor";
    }

    @PostMapping("/monthlyBudget/monthlyBudgetEditor/")
    public String updateMonthlyBudgetForm(@Valid MonthlyBudgetForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.error("monthlyBudget update error");
            return "redirect:monthlyBudget/navigateToMonthlyBudget/" + form.getChildId();
        }
        log.info("monthlyBudget edit success");

        monthlyBudgetService.update(form.getId(), form.getYearMonth(), form.getTotalBudget());

        return "redirect:/navigateToMonthlyBudget/" + form.getChildId();
    }

    @PostMapping("/monthlyBudget/delete")
    public String deleteMonthlyBudget(@Valid MonthlyBudgetForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.error("monthlyBudget delete error");
            return "redirect:monthlyBudget/navigateToMonthlyBudget/" + form.getChildId();
        }
        log.info("monthlyBudget delete success");

        monthlyBudgetService.delete(form.getId());

        return "redirect:/navigateToMonthlyBudget/" + form.getChildId();
    }
}
