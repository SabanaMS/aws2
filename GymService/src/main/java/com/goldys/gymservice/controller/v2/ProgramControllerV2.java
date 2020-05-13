package com.goldys.gymservice.controller.v2;

import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.proxy.GymServiceProxy;
import com.goldys.gymservice.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Boilerplate Code: Do Not Change
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v2/gymservice")
public class ProgramControllerV2 {

    private ProgramService programService;

    private GymServiceProxy gymServiceProxy;

    @Autowired
    public ProgramControllerV2(ProgramService programService, GymServiceProxy gymServiceProxy) {
        this.programService = programService;
        this.gymServiceProxy = gymServiceProxy;
    }


    @GetMapping("/showAllActive")
    public ResponseEntity<?> listAllActivePrograms() {
        return new ResponseEntity<>(programService.listAllActivePrograms(), HttpStatus.OK);
    }

    @GetMapping("/{durationInMonths}")
    public ResponseEntity<?> getProgramByDuration(@PathVariable int durationInMonths) throws ProgramNotFoundException {
        return new ResponseEntity<>(programService.getProgramByDuration(durationInMonths), HttpStatus.OK);
    }

}
