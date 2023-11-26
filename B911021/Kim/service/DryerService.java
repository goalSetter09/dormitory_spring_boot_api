package B911021.Kim.service;

import B911021.Kim.entity.Dryer;
import B911021.Kim.repository.DryerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DryerService {

    private final DryerRepository dryerRepository;

    @Transactional
    public Dryer createDryer(int number) {
        if(validateDuplicatedDryer(number)) {
            Dryer dryer = new Dryer(number, true);
            return dryerRepository.save(dryer);
        } else {
            throw new IllegalArgumentException("이미 존재하는 건조기입니다.");
        }
    }

    //이미 같은 번호로 건조기가 존재하면 false 리턴
    private boolean validateDuplicatedDryer(int number) {
        Optional<Dryer> byNumber = dryerRepository.findByNumber(number);
        return byNumber.isEmpty();
    }

    @Transactional
    public int deleteDryer(int number) {
        Optional<Dryer> byNumber = dryerRepository.findByNumber(number);
        if (byNumber.isPresent()) {
            Dryer deleteDryer = byNumber.get();
            dryerRepository.delete(deleteDryer);
            return deleteDryer.getNumber();
        } else {
            throw new NullPointerException();
        }
    }

    public List<Dryer> findAvailableDryers() {
        List<Dryer> allByAvailableDryers = dryerRepository.findAllByAvailable(true);

        if (allByAvailableDryers.isEmpty()) {
            throw new IllegalStateException();
        } else {
            return allByAvailableDryers;
        }
    }
}