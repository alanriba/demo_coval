package com.demo.coval.person.service;

import com.demo.coval.person.controller.exception.PersonNotFoundException;
import com.demo.coval.person.controller.exception.PersonParamIsNotValidException;
import com.demo.coval.person.controller.exception.ServiceException;
import com.demo.coval.person.repository.IPersonRespository;
import com.demo.coval.person.dto.PersonDTO;
import com.demo.coval.person.entity.PersonEntity;
import com.demo.coval.person.utils.UtilsModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    public final IPersonRespository personRepository;

    PersonService(IPersonRespository personRepository) {
        this.personRepository = personRepository;
    }

    private static final String ERROR_CODE = "-1";

    /**
     * return all persons
     *
     * @return List<PersonDTO>
     */
    @Override
    public List<PersonDTO> getPersonAll() {
        List<PersonDTO> list = UtilsModelMapper.mapAll(personRepository.findAll(), PersonDTO.class);

        if (list.isEmpty()) {
            throw new PersonNotFoundException(ERROR_CODE, HttpStatus.NOT_FOUND, "Not found information about all persons");
        }
        return list;
    }

    /**
     * search person by id
     *
     * @param id
     * @return PersonDTO
     */
    @Override
    public PersonDTO getPersonById(Long id) {

        if (id <= 0) {
            throw new ServiceException(ERROR_CODE, HttpStatus.UNPROCESSABLE_ENTITY, "Is necessary to send id of the person");
        }

        PersonDTO person = UtilsModelMapper.map(personRepository.findById(id), PersonDTO.class);

        if (person == null) {
            throw new PersonNotFoundException(ERROR_CODE, HttpStatus.NOT_FOUND, "Not found information about idPerson " + id);
        }
        return person;
    }

    /**
     * search persons by name
     *
     * @param name
     * @return List<PersonDTO>
     */
    @Override
    public List<PersonDTO> getPersonByName(String name) {

        if (name.equals("")) {
            throw new ServiceException(ERROR_CODE, HttpStatus.UNPROCESSABLE_ENTITY, "Is necessary to send name of the person");
        }

        List<PersonDTO> person = UtilsModelMapper.mapAll(personRepository.findAllByName(name), PersonDTO.class);

        if (person.isEmpty()) {
            throw new PersonNotFoundException(ERROR_CODE, HttpStatus.NOT_FOUND, "Not found information about name Person " + name);
        }
        return person;
    }

    /**
     * @param person
     * @return PersonDTO
     */
    @Override
    public PersonDTO savePerson(PersonDTO person) {

        validateInformationPerson(person);

        PersonEntity personEntity = personRepository.save(PersonEntity.builder().name(person.getName()).lastName(person.getLastName()).status(person.getStatus()).build());
        PersonDTO p = UtilsModelMapper.map(personEntity, PersonDTO.class);

        if (p == null) {
            throw new ServiceException(ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR, "Error to save information of the person");
        }
        return p;
    }

    /**
     * @param person
     * @param id
     * @return PersonDTO
     */
    @Override
    public PersonDTO updatePerson(PersonDTO person, Long id) {

        validateInformationPerson(person);

        if (id == 0) {
            throw new PersonNotFoundException(ERROR_CODE, HttpStatus.UNPROCESSABLE_ENTITY, "The value Id is null " + id);
        }

        PersonEntity personEntity = personRepository.save(PersonEntity.builder().id(id).name(person.getName()).lastName(person.getLastName()).status(person.getStatus()).build());
        PersonDTO p = UtilsModelMapper.map(personEntity, PersonDTO.class);

        if (p == null) {
            throw new ServiceException(ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR, "Error to update information of the person");
        }
        return p;
    }

    private static void validateInformationPerson(PersonDTO person) {
        if (person == null) {
            throw new PersonParamIsNotValidException(ERROR_CODE, HttpStatus.UNPROCESSABLE_ENTITY, "Is necessary to send information about the person save or update");
        }
    }
}
