package app.user.controller;

import app.user.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User controller", description = "The User API")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private IUserService userService;
}
