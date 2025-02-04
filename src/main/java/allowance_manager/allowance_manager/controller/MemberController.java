package allowance_manager.allowance_manager.controller;

import allowance_manager.allowance_manager.Service.MemberServiceImpl;
import allowance_manager.allowance_manager.Service.interfaces.MemberService;
import allowance_manager.allowance_manager.controller.form.MemberForm;
import allowance_manager.allowance_manager.domain.Member;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    //로그인
    @PostMapping("/signin")
    public String signIn(@Valid MemberForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/";
        }
        boolean success = memberService.logIn(form.getName(), form.getPassword());
//        log.info(form.getName());
//        log.info(form.getPassword());
        if (success){
            log.info("signIn success");
            return "manager";
        } else {
            log.info("signIn failed");
            model.addAttribute("errorMessage", "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/";
        }
    }

    //회원가입
    @PostMapping("/signup")
    public String register(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.info("register error");
            return "redirect:/";
        }
        Member member = Member.createMember(form.getName(), form.getPassword());
        memberService.join(member);
        return "redirect:/";
    }
}
