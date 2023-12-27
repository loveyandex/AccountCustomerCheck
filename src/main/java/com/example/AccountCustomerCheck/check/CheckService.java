package com.example.AccountCustomerCheck.check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
 
import java.util.Optional;

@Service
public class CheckService {

    @Autowired
    private CheckRepository checkRepository; // Assuming you have a CheckRepository

    public List<Check> getAllChecks() {
        return checkRepository.findAll();
    }

    public Check getCheckById(Long checkId) {
        Optional<Check> optionalCheck = checkRepository.findById(checkId);
        return optionalCheck.orElse(null);
    }

    public Check createCheck(CheckDTO checkDTO) {
        // Create a new Check entity based on the DTO
        Check newCheck = new Check();
        // Populate fields based on the DTO
        // For example: newCheck.setDescription(checkDTO.getDescription());
        return checkRepository.save(newCheck);
    }

    public Check updateCheck(Long checkId, CheckDTO checkDTO) {
        Optional<Check> optionalCheck = checkRepository.findById(checkId);
        if (optionalCheck.isPresent()) {
            Check existingCheck = optionalCheck.get();
            // Update fields based on the DTO
            // For example: existingCheck.setDescription(checkDTO.getDescription());
            return checkRepository.save(existingCheck);
        } else {
            return null; // Check not found
        }
    }

    public boolean deleteCheck(Long checkId) {
        Optional<Check> optionalCheck = checkRepository.findById(checkId);
        if (optionalCheck.isPresent()) {
            checkRepository.delete(optionalCheck.get());
            return true;
        } else {
            return false; // Check not found
        }
    }
}
