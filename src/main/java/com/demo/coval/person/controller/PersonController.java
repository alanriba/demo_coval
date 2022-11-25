/**
 *
 */
package com.demo.coval.person.controller;


import com.demo.coval.person.service.PersonService;
import com.demo.coval.person.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author alex
 * @version v1
 */
@RestController
@RequestMapping(value = "api/v1/")
public class PersonController {

    PersonService personService;

    PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping(value = "all/person", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Person", description = "Get information all person")
    @ApiResponse(responseCode = "200", description = "Search person successfully")
    @ApiResponse(responseCode = "404", description = "Not found information for the all persons")
    @ApiResponse(responseCode = "500", description = "Internal Error Server")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PersonDTO>> getAllPerson() {
        return new ResponseEntity<>(personService.getPersonAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/person", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Person", description = "Search person by id in the database")
    @ApiResponse(responseCode = "200", description = "Search person successfully")
    @ApiResponse(responseCode = "404", description = "Not found information for person")
    @ApiResponse(responseCode = "422", description = "Params not valid")
    @ApiResponse(responseCode = "500", description = "Internal Error Server")
    public ResponseEntity<PersonDTO> gerPersonById(@PathVariable() Long id) {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/person", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Person", description = "Search person by name in the database")
    @ApiResponse(responseCode = "200", description = "Search person successfully")
    @ApiResponse(responseCode = "404", description = "Not found information for person")
    @ApiResponse(responseCode = "422", description = "Params not valid")
    @ApiResponse(responseCode = "500", description = "Internal Error Server")
    public ResponseEntity<List<PersonDTO>> gerPersonByName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(personService.getPersonByName(name), HttpStatus.OK);
    }

    @PutMapping(value = "/person/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Person", description = "Update information of the person")
    @ApiResponse(responseCode = "204", description = "person update successfully")
    @ApiResponse(responseCode = "422", description = "Params not valid")
    @ApiResponse(responseCode = "500", description = "Internal Error Server")
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO person, @PathVariable Long id) {
        return new ResponseEntity<>(personService.updatePerson(person, id), HttpStatus.OK);
    }

    @PostMapping(value = "/person", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Person", description = "Add information of the person")
    @ApiResponse(responseCode = "201", description = "person insert successfully")
    @ApiResponse(responseCode = "422", description = "Params not valid")
    @ApiResponse(responseCode = "500", description = "Internal Error Server")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.OK);
    }
}
