package io.bootify.minor_project.rest;

import io.bootify.minor_project.model.AddressDTO;
import io.bootify.minor_project.model.UserDTO;
import io.bootify.minor_project.model.UserStatus;
import io.bootify.minor_project.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminPanelController {

    Logger LOGGER = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createUser(@RequestBody @Valid final UserDTO userDTO) {
        final Long createdId = userService.create(userDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/markUserInactive/{userId}")
    public ResponseEntity<Void> markUserInactive(@PathVariable Long userId){
        userService.markInactive(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/markUserActive/{userId}")
    public ResponseEntity<Void> markUserActive(@PathVariable Long userId){
        userService.markActive(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam Integer pageSize, @RequestParam Integer pageNumber){
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        return ResponseEntity.ok(userService.findAllWithPagination(pageable));
    }



    @PostMapping("/user-csv-upload")
    public ResponseEntity<List<String>> uploadFileForUserCreation(@RequestParam("file") MultipartFile file){
        LOGGER.info("File available : {}",file.getName());
        //
        //
        //
        List<String> response = new ArrayList<>();
        try {
            BufferedReader fileReader= new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for(CSVRecord csvRecord : csvRecords){
                UserDTO userDTO = new UserDTO();
                userDTO.setName(csvRecord.get("name"));
                userDTO.setEmail(csvRecord.get("email"));
                userDTO.setPhone(csvRecord.get("phone"));
                userDTO.setFlatNumber(csvRecord.get("flatNumber"));
                userDTO.setRole(csvRecord.get("role"));
                userDTO.setStatus(UserStatus.ACTIVE);
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setLine1(csvRecord.get("line1"));
                addressDTO.setLine2(csvRecord.get("line2"));
                addressDTO.setCity(csvRecord.get("city"));
                addressDTO.setPincode(csvRecord.get("pincode"));
                addressDTO.setCountry(csvRecord.get("country"));
                userDTO.setAddress(addressDTO);
                try{
                    Long id = userService.create(userDTO);
                    response.add("Created User "+userDTO.getName()+"with id:"+id);
                }
                catch (Exception e){
                    response.add("Unable to created User "+userDTO.getName()+" msg:"+e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(response);
    }
}