package com.familyfirstsoftware.PhotoAppApiUsers.controller;

import com.familyfirstsoftware.PhotoAppApiUsers.model.CreateUserRequestModel;
import com.familyfirstsoftware.PhotoAppApiUsers.service.UsersService;
import com.familyfirstsoftware.PhotoAppApiUsers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    // inject port number using env
    @Autowired
    private Environment env;

    @Autowired
    UsersService usersService;

    @GetMapping(value = "/status/check")
    public String status(){
        return "Users Working on port " + env.getProperty("local.server.port") ;
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CreateUserRequestModel> createUser(@RequestBody @Valid CreateUserRequestModel userDetails){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails,UserDto.class);
        UserDto createdUser = usersService.createUser(userDto);

        CreateUserRequestModel returnValue = modelMapper.map(createdUser, CreateUserRequestModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

}
