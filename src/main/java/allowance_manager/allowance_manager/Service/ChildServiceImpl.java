package allowance_manager.allowance_manager.Service;

import allowance_manager.allowance_manager.Service.interfaces.ChildService;
import allowance_manager.allowance_manager.domain.Child;
import allowance_manager.allowance_manager.repository.ChildRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {
    private final ChildRepository childRepository;

    @Override
    @Transactional
    public Long join(Child child) {
        childRepository.save(child);

        return child.getId();
    }

    @Override
    @Transactional
    public void withdraw(Long childId) {
        childRepository.deleteById(childId);
    }

    @Override
    @Transactional
    public void update(Long childId, String childName) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child Not Found"));

        child.setName(childName);
    }

    @Override
    @Transactional
    public void changePlannedBudget(Long childId, Long newBudget) {
        childRepository.updatePlannedBudget(childId, newBudget);
    }

    @Override
    public List<Child> findChildren() {
        return List.of();
    }

    @Override
    public Optional<Child> findChild(Long childId) {
        return Optional.empty();
    }


}
