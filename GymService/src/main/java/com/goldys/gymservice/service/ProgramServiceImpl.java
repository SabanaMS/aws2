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
 * Boilerplate Code: Do Not Change
 */
@Service
public class ProgramServiceImpl implements ProgramService {


    private ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @CacheEvict(value = "programs-cache", allEntries = true)
    @Override
    public Program addNewProgram(Program program) throws ProgramAlreadyExistsException {
        if (programRepository.findById(program.getProgramCode()).isPresent()) {
            throw new ProgramAlreadyExistsException();
        }

        program.setCurrentPrice();
        return programRepository.save(program);
    }

    @CacheEvict(value = "programs-cache", allEntries = true)
    @Override
    public Program updateExistingProgram(Program program) throws ProgramNotFoundException {
        if (programRepository.findById(program.getProgramCode()).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        program.setCurrentPrice();
        return programRepository.save(program);
    }


    @Cacheable(value = "programs-cache", key = "#p0")
    @Override
    public Program getProgramByCode(String programCode) throws ProgramNotFoundException {
        if (programRepository.findById(programCode).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        return programRepository.findById(programCode).get();
    }

    @Cacheable(value = "programs-cache", key = "#p0")
    @Override
    public List<Program> getProgramByDuration(int durationInMonths) {
        return programRepository.findByDurationInMonths(durationInMonths);
    }

    @Cacheable(value = "programs-cache")
    @Override
    public List<Program> listAllPrograms() {
        return programRepository.findAll();
    }


    @Cacheable(value = "active-programs-cache")
    @Override
    public List<Program> listAllActivePrograms() {
        return programRepository.findByIsActiveTrue();
    }


    @CacheEvict(value = "programs-cache", allEntries = true)
    @Override
    public void deleteProgram(String programCode) throws ProgramNotFoundException {
        if (programRepository.findById(programCode).isEmpty()) {
            throw new ProgramNotFoundException();
        }
        programRepository.deleteById(programCode);
    }


}
