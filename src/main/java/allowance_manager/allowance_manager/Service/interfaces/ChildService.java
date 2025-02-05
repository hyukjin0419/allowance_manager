package allowance_manager.allowance_manager.Service.interfaces;

import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.domain.Member;

import java.util.List;
import java.util.Optional;

public interface ChildService {
    public Long join(Child child);
    public void withdraw(Long childId);
    public void update(Long childId, String childName);
    public List<Child> findChildrenByParentId(Long memberId);
    public List<Child> findChildrenByParentName(String memberName);
    public List<Child> findChildren();
    public Optional<Child> findChild(Long childId);
    public void changePlannedBudget(Long childId, Long newBudget);
}
