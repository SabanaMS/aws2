package com.goldys.gymservice.repository;

import com.goldys.gymservice.model.Program;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Boilerplate Code: Do Not Change
 */
@Repository
public interface ProgramRepository extends MongoRepository<Program, String> {

    List<Program> findByIsActiveTrue();

    List<Program> findByDurationInMonths(int durationInMonths);
}
