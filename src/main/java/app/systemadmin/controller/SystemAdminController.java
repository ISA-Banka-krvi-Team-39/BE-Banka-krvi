package app.systemadmin.controller;

import app.person.model.Person;
import app.person.service.IPersonService;
import app.systemadmin.dto.SystemAdminDTO;
import app.systemadmin.model.SystemAdmin;
import app.systemadmin.service.ISystemAdminService;
import app.user.dto.UpdateUserDTO;
import app.user.model.User;
import app.user.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "System admin controller", description = "The Admin API")
@RestController
@RequestMapping(value = "/api/systemAdmin")
public class SystemAdminController {
    @Autowired
    private ISystemAdminService systemAdminService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Get one admin", description = "Get one admin", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SystemAdmin.class))))
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SystemAdminDTO> getOne(@Parameter(name="id", description = "ID of a admin to return", required = true) @PathVariable("id") int id) {
        User user = userService.findOne(id);
        Person person = personService.findOne(id);
        SystemAdmin admin = systemAdminService.findOneByPersonId(id);
        SystemAdminDTO adminDTO = new SystemAdminDTO(admin);
        admin.setWasLoggedIn(true);
        systemAdminService.update(admin);
        return new ResponseEntity<SystemAdminDTO>(adminDTO, HttpStatus.OK);
    }



}
