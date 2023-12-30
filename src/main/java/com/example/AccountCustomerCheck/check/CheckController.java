package com.example.AccountCustomerCheck.check;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/checks")
public class CheckController {

    @Autowired
    private CheckService checkService; // Assuming you have a CheckService

     

    @GetMapping("/customer/{customerId}")
    public List<Check> getChecksForCustomer(@PathVariable Long customerId) {
        return checkService.getChecksForCustomer(customerId);
    }


    @GetMapping("/customer/{customerId}/query1")
    public List<Check> getChecksForCustomerQuery1(@PathVariable Long customerId) {
        return checkService.getChecksForCustomerQuery1(customerId);
    }

    @GetMapping("/customer/{customerId}/query2")
    public List<Check> getChecksForCustomerQuer2(@PathVariable Long customerId) {
        return checkService.getChecksForCustomerQuery2(customerId);
    }





    @GetMapping
    public ResponseEntity<List<CheckDTO>> getAllChecks() {
        List<CheckDTO> checkDTOList = checkService.getAllChecks()
                .stream()
                .map(CheckDTO::new) // Assuming a constructor in CheckDTO that takes a Check object
                .collect(Collectors.toList());
        return new ResponseEntity<>(checkDTOList, HttpStatus.OK);
    }

    @GetMapping("/{checkId}")
    public ResponseEntity<CheckDTO> getCheckById(@PathVariable Long checkId) {
        Check check = checkService.getCheckById(checkId);
        if (check != null) {
            CheckDTO checkDTO = new CheckDTO(check);
            return new ResponseEntity<>(checkDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CheckDTO> createCheck(@RequestBody CheckDTO checkDTO) {
        Check savedCheck = checkService.createCheck(checkDTO);
        CheckDTO savedCheckDTO = new CheckDTO(savedCheck);
        return new ResponseEntity<>(savedCheckDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{checkId}")
    public ResponseEntity<CheckDTO> updateCheck(
            @PathVariable Long checkId,
            @RequestBody CheckDTO checkDTO) {
        Check updatedCheck = checkService.updateCheck(checkId, checkDTO);
        if (updatedCheck != null) {
            CheckDTO updatedCheckDTO = new CheckDTO(updatedCheck);
            return new ResponseEntity<>(updatedCheckDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{checkId}")
    public ResponseEntity<Void> deleteCheck(@PathVariable Long checkId) {
        if (checkService.deleteCheck(checkId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
