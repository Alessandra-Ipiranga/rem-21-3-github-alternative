package de.neuefische.rem_213_github.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import static de.neuefische.rem_213_github.backend.controller.HelloController.HELLO_TAG;
import static org.springframework.http.ResponseEntity.ok;

@Tag(name = HELLO_TAG, description = "This controller only says Hello to REM-21-3!")
@Api(
        tags = HELLO_TAG
)
@ApiIgnore
@CrossOrigin
@RestController
public class HelloController {

    public static final String HELLO_TAG = "Hello";

    @ApiOperation(value = "Say hello to REM 21-3", hidden = true)
    @GetMapping("/")
    public ResponseEntity<String> hello() {
        String cssStyle = "position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);";
        String swaggerLink = "/api/rem213/swagger-ui/";
        String linkDesc = "Github Review Karma Swagger REM 21-3";

        return ok(String.format(
                "<html><body><h1 style=\"%s\"><a href=\"%s\">%s</a></h1></body></html>",
                cssStyle, swaggerLink, linkDesc
        ));
    }
}
