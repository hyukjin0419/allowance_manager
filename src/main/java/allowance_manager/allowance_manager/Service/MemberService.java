package allowance_manager.allowance_manager.Service;

import allowance_manager.allowance_manager.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Long join(Member member);
    public List<Member> findMembers();
    public Optional<Member> findOne(Long memberId);
}
