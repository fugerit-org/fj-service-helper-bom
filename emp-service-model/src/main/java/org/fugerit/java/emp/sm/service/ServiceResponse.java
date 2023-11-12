package org.fugerit.java.emp.sm.service;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ServiceResponse {
	
	@Schema(description = "Error messages list", example = "[{\"code\":\"500001\",\"severity\":\"E\",\"text\":\"sample error\"}]" , required = false )
	@Getter @Setter private List<ServiceMessage> errors;

	@Schema(description = "Warning messages list", example = "[{\"code\":\"401001\",\"severity\":\"E\",\"text\":\"sample warning\"}]" , required = false )
	@Getter @Setter private List<ServiceMessage> warnings;
	
	@Schema(description = "Info messages list", example = "[{\"code\":\"100001\",\"severity\":\"E\",\"text\":\"sample info\"}]" , required = false )
	@Getter @Setter private List<ServiceMessage> infos;
	
	@Schema(description = "Success messages list", example = "[{\"code\":\"200001\",\"severity\":\"S\",\"text\":\"sample success\"}]" , required = false )
	@Getter @Setter private List<ServiceMessage> success;
	
}
