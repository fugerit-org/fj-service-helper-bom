package org.fugerit.java.emp.sm.service;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * Utility model wrapping a message.
 * </p>
 * 
 * <p>
 * Messages have four default severities represented by one character code :
 * INFO(I), WARNING(W), ERROR(E) and SUCCESS(S)
 * </p>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceMessage {

	/**
	 * Constant for severity SUCCESS (S)
	 */
	public static final String SEVERITY_SUCCESS = "S";

	/**
	 * Constant for severity INFO (I)
	 */
	public static final String SEVERITY_INFO = "I";

	/**
	 * Constant for severity WARNING (W)
	 */
	public static final String SEVERITY_WARNING = "W";

	/**
	 * Constant for severity ERROR (E)
	 */
	public static final String SEVERITY_ERROR = "E";
	
	/**
	 * The code of the message
	 * 
	 * @param code the message's code
	 * @return this message's code
	 */
	@Schema( description = "Message identifier", example = "401001" )
	@Getter @Setter private String code;

	/**
	 * The severity of the message
	 * 
	 * @param severity the severity code
	 * @return this message's severity
	 */
	@Schema(description = "Message severity, E = Error, W = Warning, I = Info, S = Success", example = "E")
	@Getter @Setter private String severity;

	/**
	 * The text of the message
	 * 
	 * @param text the message's text
	 * @return this message's text
	 */
	@Schema(description = "Message test", example = "User not authorized to access the resource.")
	@Getter @Setter private String text;

}
