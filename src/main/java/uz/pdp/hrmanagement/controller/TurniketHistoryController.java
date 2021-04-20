package uz.pdp.hrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.TurniketHistoryDto;
import uz.pdp.hrmanagement.service.TurniketHistoryService;

import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api/turnikethistory")
public class TurniketHistoryController {

    @Autowired
    TurniketHistoryService turniketHistoryService;

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody TurniketHistoryDto turniketHistoryDto){
        ApiResponse apiResponse = turniketHistoryService.add(turniketHistoryDto);
        return ResponseEntity.status(apiResponse.isStatus()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @GetMapping("/bydate")
    public HttpEntity<?> getAllByDate(@RequestParam String number, @RequestParam Timestamp startTime, @RequestParam Timestamp endTime){
        ApiResponse apiResponse = turniketHistoryService.getAllByDate(number, startTime, endTime);
        return ResponseEntity.status(apiResponse.isStatus()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @GetMapping("/all")
    public HttpEntity<?> getAll(@RequestParam String number){
        ApiResponse apiResponse = turniketHistoryService.getAll(number);
        return ResponseEntity.status(apiResponse.isStatus()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
