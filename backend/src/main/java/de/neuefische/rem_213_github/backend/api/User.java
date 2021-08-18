package de.neuefische.rem_213_github.backend.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @ApiModelProperty(required = true, example = "Max Muster", notes = "The name of the user")
    private String name;

    @ApiModelProperty(example = "http://foo.de", notes = "The url of the user avatar")
    private String avatar;

}
