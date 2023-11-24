package B911021.Kim.service;

import B911021.Kim.entity.Washer;
import B911021.Kim.repository.WasherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WasherService {

    private final WasherRepository washerRepository;

    public Washer createWasher(int number) {
        Washer washer = new Washer(number, true);
        return washerRepository.save(washer);
    }

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

    public List<Washer> findAvailableWashers() {
        List<Washer> allByAvailableWashers = washerRepository.findAllByAvailable(true);

        if (allByAvailableWashers.isEmpty()) {
            throw new IllegalStateException();
        } else {
            return allByAvailableWashers;
        }
    }
}
