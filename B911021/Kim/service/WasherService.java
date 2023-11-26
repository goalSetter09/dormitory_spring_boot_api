package B911021.Kim.service;

import B911021.Kim.entity.Washer;
import B911021.Kim.repository.WasherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WasherService {

    private final WasherRepository washerRepository;

    @Transactional
    public Washer createWasher(int number) {
        if (validateDuplicatedWasher(number)) {
            Washer washer = new Washer(number, true);
            return washerRepository.save(washer);
        } else {
            throw new IllegalArgumentException("이미 존재하는 세탁기입니다.");
        }
    }

    //이미 같은 번호로 세탁기가 존재하면 false 리턴
    private boolean validateDuplicatedWasher(int number) {
        Optional<Washer> byNumber = washerRepository.findByNumber(number);
        if (byNumber.isPresent()) {
            return false;
        }
        return true;
    }

    @Transactional
    public int deleteWasher(int number) {
        Optional<Washer> byNumber = washerRepository.findByNumber(number);
        if (byNumber.isPresent()) {
            Washer deleteWasher = byNumber.get();
            washerRepository.delete(deleteWasher);
            return deleteWasher.getNumber();
        } else {
            throw new NullPointerException();
        }
    }

    public Washer findByWasherNumber(int number) {
        Optional<Washer> byNumber = washerRepository.findByNumber(number);
        if(byNumber.isPresent()) {
            return byNumber.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<Washer> findAvailableWashers() {
        List<Washer> allByAvailableWashers = washerRepository.findAllByAvailable(true);

        if (allByAvailableWashers.isEmpty()) {
            throw new IllegalStateException();
        } else {
            return allByAvailableWashers;
        }
    }
}
