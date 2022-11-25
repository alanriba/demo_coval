package com.demo.coval.person.service;

import com.demo.coval.person.dto.PersonDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IPersonService {

	List<PersonDTO> getPersonAll();

	PersonDTO getPersonById(Long id);

	List<PersonDTO> getPersonByName(String name);

	PersonDTO savePerson(PersonDTO person);

	PersonDTO updatePerson(PersonDTO person, Long id);

}