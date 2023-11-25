package B911021.Kim.service;

import B911021.Kim.entity.Dryer;
import B911021.Kim.repository.DryerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DryerService {

    private final DryerRepository dryerRepository;

    public Dryer createDryer(int number) {
        Dryer dryer = new Dryer(number, true);
        return dryerRepository.save(dryer);
    }

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