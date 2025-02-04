package allowance_manager.allowance_manager.Service;

import allowance_manager.allowance_manager.Service.interfaces.ChildService;
import allowance_manager.allowance_manager.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {
    private final ChildRepository childRepository;

    public void changePlannedBudget(Long childId, Long newBudget) {
        childRepository.updatePlannedBudget(childId, newBudget);
    }
}
