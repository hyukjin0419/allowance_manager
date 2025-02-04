package allowance_manager.allowance_manager.Service;

import allowance_manager.allowance_manager.domain.Member;
import allowance_manager.allowance_manager.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {
    @Autowired MemberServiceImpl memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    public void 회원가입(){
        //given
        Member member = new Member();
        member.setName("choi");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findById(savedId)
                .orElseThrow(()-> new AssertionError("Member not found")));
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("choi");

        Member member2 = new Member();
        member2.setName("choi");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return ;
        }

        //then
        fail("예외가 발생해야만 한다");
    }
}