package allowance_manager.allowance_manager.controller;

import allowance_manager.allowance_manager.Service.interfaces.ChildService;
import allowance_manager.allowance_manager.Service.interfaces.MemberService;
import allowance_manager.allowance_manager.controller.form.MemberForm;
import allowance_manager.allowance_manager.utility.SessionUtil;
import allowance_manager.allowance_manager.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final ChildService childService;
    private final SessionUtil sessionUtil;

    //로그인
    @PostMapping("/signin")
    public String signIn(@Valid MemberForm form, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "redirect:/";
        }
        boolean success = memberService.logIn(form.getName(), form.getPassword());
        if (success){
            log.info("signIn success");
            Member member = sessionSet(form, session);

            return sessionUtil.isChildExist(member.getId());
        } else {
            log.info("signIn failed");
            model.addAttribute("errorMessage", "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/";
        }
    }

    private Member sessionSet(MemberForm form, HttpSession session) {
        Member member = memberService.findByName(form.getName())
                .orElseThrow(()->new EntityNotFoundException("회원을 찾을 수 없습니다."));

        session.setAttribute("member", member);
        return member;
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
