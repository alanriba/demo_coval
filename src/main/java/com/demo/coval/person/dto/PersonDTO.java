package com.demo.coval.person.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import jdk.jfr.BooleanFlag;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Id cannot be null")
	@Min(value = 1, message = "ID should not be less than 1 or equals 0")
	private Long id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@NotNull(message = "Name cannot be null")
	@Min(value = 3, message = "Name should not be less than 3")
	@Max(value = 100, message = "Name should not be more than 100")
	private String name;

	@Min(value = 3, message = "Last Name should not be less than 3")
	@Max(value = 100, message = "LastName should not be more than 100")
	private String lastName;

	private Boolean status = true;
}
