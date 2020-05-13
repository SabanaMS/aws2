package com.goldys.gymservice.service;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;
import com.goldys.gymservice.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * This class is implementing the ProgramService interface. This class has to be annotated with
 * @Service annotation.
 * @Service - is an annotation that annotates classes at the service layer, thus
 * clarifying it's role.
 *
 * */
@Service
public class ProgramServiceImpl implements ProgramService {

    /*
     * Constructor Autowiring should be implemented for the ProgramRepository.
     */

    private ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    /*
     * Add a new program. Throw ProgramAlreadyExistsException if the program with specified
     * programCode already exists. Current price should be a calculated value and hence should
     * not be given as an input. In case it is given, it has to be overridden.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again
     */
    @CacheEvict(value = "programs-cache", allEntries = true)
    @Override
    public Program addNewProgram(Program program) throws ProgramAlreadyExistsException {
        if (programRepository.findById(program.getProgramCode()).isPresent()) {
            throw new ProgramAlreadyExistsException();
        }

        program.setCurrentPrice();
        return programRepository.save(program);
    }

    /*
     * Update an existing Program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist. Current price should be a calculated value and hence should
     * not be given as an input. In case it is given, it has to be overridden.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     */
    @CacheEvict(value = "programs-cache", allEntries = true)
    @Override
    public Program updateExistingProgram(Program program) throws ProgramNotFoundException {
        if (programRepository.findById(program.getProgramCode()).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        program.setCurrentPrice();
        return programRepository.save(program);
    }


    /*
     * Retrieve an existing program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist.
     * Caching should be implemented to reduce method calls.
     */
    @Cacheable(value = "programs-cache", key = "#p0")
    @Override
    public Program getProgramByCode(String programCode) throws ProgramNotFoundException {
        if (programRepository.findById(programCode).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        return programRepository.findById(programCode).get();
    }


    /*
     * Retrieve all existing programs by it's duration.
     * Caching should be implemented to reduce method calls.
     */
    @Cacheable(value = "programs-cache", key = "#p0")
    @Override
    public List<Program> getProgramByDuration(int durationInMonths) {
        return programRepository.findByDurationInMonths(durationInMonths);
    }


    /*
     * Retrieve all existing programs
     * Caching should be implemented to reduce method calls.
     */
    @Cacheable(value = "programs-cache")
    @Override
    public List<Program> listAllPrograms() {
        return programRepository.findAll();
    }

    /*
     * Retrieve all existing programs which are active.
     * Caching should be implemented to reduce method calls.
     */
    @Cacheable(value = "active-programs-cache")
    @Override
    public List<Program> listAllActivePrograms() {
        return programRepository.findByIsActiveTrue();
    }


    /*
     * Delete an existing Program by it's programCode. Throw ProgramNotFoundException if the
     * program with specified programCode does not exist.
     * @CacheEvict annotation is to be used to indicate the removal of all values,
     * so that fresh values can be loaded into the cache again.
     */
    @CacheEvict(value = "programs-cache", allEntries = true)
    @Override
    public void deleteProgram(String programCode) throws ProgramNotFoundException {
        if (programRepository.findById(programCode).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        programRepository.deleteById(programCode);
    }


}
