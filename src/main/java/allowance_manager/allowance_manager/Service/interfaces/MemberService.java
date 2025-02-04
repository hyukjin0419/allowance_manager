package allowance_manager.allowance_manager.Service.interfaces;

import allowance_manager.allowance_manager.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Long join(Member member);
    public void withdraw(Long id);
    public void update(Long id, String name);
    public List<Member> findMembers();
    public Optional<Member> findOne(Long memberId);
}



