package com.goldys.gymservice.commander.service;

import com.goldys.gymservice.exception.ProgramAlreadyExistsException;
import com.goldys.gymservice.exception.ProgramNotFoundException;
import com.goldys.gymservice.model.Program;
import com.goldys.gymservice.repository.ProgramRepository;
import com.goldys.gymservice.service.ProgramServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProgramServiceTest {

    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private ProgramServiceImpl programService;

    private Program program;

    private List<Program> programList;

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        programList = new ArrayList<>();
        program = new Program("All Access(1 Month)", "Access to all equipments with general trainers", 1, 1500, 0.1f, true);
        program.setProgramCode("1");
        programList.add(program);

    }

    @AfterEach
    public void tearDown() throws Exception {
        program = null;
    }

    @Test
    @Rollback(true)
    public void testAddNewProgramSuccess() throws ProgramAlreadyExistsException {

        when(programRepository.findById(any())).thenReturn(Optional.empty());
        when(programRepository.save(any())).thenReturn(program);

        assertEquals(program, programService.addNewProgram(program));

        verify(programRepository, times(1)).findById(any());
        verify(programRepository, times(1)).save(any());

    }

    @Test
    @Rollback(true)
    public void testAddNewProgramFailure() throws ProgramAlreadyExistsException {

        when(programRepository.findById(any())).thenReturn(Optional.of(program));

        assertThrows(ProgramAlreadyExistsException.class, () -> programService.addNewProgram(program));

        verify(programRepository, times(1)).findById(any());
        verify(programRepository, times(0)).save(any());

    }


    @Test
    @Rollback(true)
    public void testUpdateProgramSuccess() throws ProgramNotFoundException {

        when(programRepository.findById(any())).thenReturn(Optional.ofNullable(program));
        when(programRepository.save(any())).thenReturn(program);

        assertEquals(program, programService.updateExistingProgram(program));

        verify(programRepository, times(1)).findById(any());
        verify(programRepository, times(1)).save(any());

    }

    @Test
    @Rollback(true)
    public void testUpdateProgramFailure() throws ProgramNotFoundException {

        when(programRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        assertThrows(ProgramNotFoundException.class, () -> programService.updateExistingProgram(program));

        verify(programRepository, times(1)).findById(any());
        verify(programRepository, times(0)).save(any());

    }

    @Test
    @Rollback(true)
    public void testGetAllProgramsSuccess() {

        when(programRepository.findAll()).thenReturn(programList);

        assertEquals(programList, programService.listAllPrograms());

        verify(programRepository, times(1)).findAll();

    }

    @Test
    @Rollback(true)
    public void testGetAllActiveProgramsSuccess() {

        when(programRepository.findByIsActiveTrue()).thenReturn(programList);

        assertEquals(programList, programService.listAllActivePrograms());

        verify(programRepository, times(1)).findByIsActiveTrue();

    }

    @Test
    @Rollback(true)
    public void testGetAllProgramsByDurationSuccess() {

        when(programRepository.findByDurationInMonths(1)).thenReturn(programList);

        assertEquals(programList, programService.getProgramByDuration(1));

        verify(programRepository, times(1)).findByDurationInMonths(1);

    }

    @Test
    @Rollback(true)
    public void testGetProgramSuccess() throws ProgramNotFoundException {

        when(programRepository.findById(any())).thenReturn(Optional.of(program));

        assertEquals(program, programService.getProgramByCode(program.getProgramCode()));

        verify(programRepository, times(2)).findById(any());

    }

    @Test
    @Rollback(true)
    public void testGetProgramFailure() throws ProgramNotFoundException {

        when(programRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ProgramNotFoundException.class, () -> programService.getProgramByCode(program.getProgramCode()));

        verify(programRepository, times(1)).findById(any());

    }

    @Test
    @Rollback(true)
    public void testDeleteProgramSuccess() throws ProgramNotFoundException {

        when(programRepository.findById(any())).thenReturn(Optional.of(program));

        programService.deleteProgram(program.getProgramCode());

        verify(programRepository, times(1)).findById(any());
        verify(programRepository, times(1)).deleteById(any());

    }

    @Test
    @Rollback(true)
    public void testDeleteProgramFailure() throws ProgramNotFoundException {

        when(programRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ProgramNotFoundException.class, () -> programService.deleteProgram(program.getProgramCode()));

        verify(programRepository, times(1)).findById(any());
        verify(programRepository, times(0)).deleteById(any());

    }

}
