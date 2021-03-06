package com.goldys.gymservice.proxy;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Boilerplate Code: Do Not Change
 */
@FeignClient(name = "gym-service")
@RibbonClient(name = "gym-service")
public interface GymServiceProxy {

    @GetMapping("/api/v1/gymservice")
    ResponseEntity<?> listAllPrograms();

    @GetMapping("/api/v2/gymservice/showAllActive")
    ResponseEntity<?> listAllActivePrograms();

    @GetMapping("/api/v2/gymservice/{durationInMonths}")
    ResponseEntity<?> getProgramByDuration(@PathVariable int durationInMonths) throws ProgramNotFoundException;

    @GetMapping("/v1/gymservice/{programCode}")
    ResponseEntity<?> getProgramByCode(@PathVariable String programCode) throws ProgramNotFoundException;

    @PostMapping("/v1/gymservice")
    ResponseEntity<?> addProgram(@RequestBody Program program) throws ProgramAlreadyExistsException;

    @PutMapping("/v1/gymservice")
    ResponseEntity<?> updateProgram(@RequestBody Program program) throws ProgramNotFoundException;
}
