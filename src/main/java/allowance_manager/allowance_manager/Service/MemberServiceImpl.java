package allowance_manager.allowance_manager.Service;

import allowance_manager.allowance_manager.Service.interfaces.MemberService;
import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.domain.Member;
import allowance_manager.allowance_manager.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long join(Member member) {
        validateDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        //실무에서는 이걸로 부족함 -> 만약 동시에 같은 이름으로 2명이 가입한다면?
        Optional<Member> findMembers = memberRepository.findByName(member.getName());
        if (findMembers.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    @Transactional
    public void withdraw(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    @Transactional
    public void update(Long memberId, String memberName) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        member.setName(memberName);
    }


    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
