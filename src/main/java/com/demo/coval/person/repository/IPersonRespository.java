package com.demo.coval.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.coval.person.entity.PersonEntity;

import java.util.List;
@Repository
public interface IPersonRespository extends JpaRepository<PersonEntity, Long> {

    List<PersonEntity> findAllByName(String name);
}