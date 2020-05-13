package com.goldys.gymservice.controller.v1;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;
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
@RequestMapping("/api/v1/gymservice")
public class ProgramControllerV1 {

    private ProgramService programService;

    private GymServiceProxy gymServiceProxy;

    @Autowired
    public ProgramControllerV1(ProgramService programService, GymServiceProxy gymServiceProxy) {
        this.programService = programService;
        this.gymServiceProxy = gymServiceProxy;
    }

    @GetMapping
    public ResponseEntity<?> listAllPrograms() {
        return new ResponseEntity<>(programService.listAllPrograms(), HttpStatus.OK);
    }


    @GetMapping("/{programCode}")
    public ResponseEntity<?> getProgramByCode(@PathVariable String programCode) throws ProgramNotFoundException {
        return new ResponseEntity<>(programService.getProgramByCode(programCode), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProgram(@RequestBody Program program) throws ProgramAlreadyExistsException {
        return new ResponseEntity<>(programService.addNewProgram(program), HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<?> updateProgram(@RequestBody Program program) throws ProgramNotFoundException {
        return new ResponseEntity<>(programService.updateExistingProgram(program), HttpStatus.OK);
    }


    @DeleteMapping("/{programCode}")
    public ResponseEntity<?> deleteProgram(@PathVariable String programCode) throws ProgramNotFoundException {
        programService.deleteProgram(programCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
