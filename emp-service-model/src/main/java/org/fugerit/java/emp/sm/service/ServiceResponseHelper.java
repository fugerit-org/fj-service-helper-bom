package org.fugerit.java.emp.sm.service;

import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceResponseHelper {

    private ServiceResponseHelper() {}

    private static List<ServiceMessage> addHelper( List<ServiceMessage> currentMessages, List<ServiceMessage> messages ) {
        List<ServiceMessage> result = currentMessages == null ? new ArrayList<>() : currentMessages;
        result.addAll( messages );
        return result;
    }

    public static void addErrors(ServiceResponse response, List<ServiceMessage> messages) {
        response.setErrors( addHelper( response.getErrors(), messages ) );
    }

    public static void addWarnings(ServiceResponse response, List<ServiceMessage> messages) {
        response.setWarnings( addHelper( response.getWarnings(), messages ) );
    }

    public static void addInfos(ServiceResponse response, List<ServiceMessage> messages) {
        response.setInfos( addHelper( response.getInfos(), messages ) );
    }

    public static void addSuccess(ServiceResponse response, List<ServiceMessage> messages) {
        response.setSuccess( addHelper( response.getSuccess(), messages ) );
    }

    public static List<ServiceMessage> filterBySeverity( List<ServiceMessage> messages, String severity ) {
        return messages.stream().filter( m -> m.getSeverity().equalsIgnoreCase( severity ) ).collect( Collectors.toList() );
    }

    public static ServiceMessage newMessageByStatus(Response.StatusType status, String message ) {
        switch ( status.getFamily() ) {
            case INFORMATIONAL:
                return newDefaultInfoMessage( message );
            case SUCCESSFUL:
                return newDefaultSuccessMessage( message );
            case OTHER:
                return newDefaultWarningMessage( message );
            default:
                return newDefaultErrorMessage( message );
        }
    }

    public static ServiceMessage newMessage( String code, String severity, String message ) {
        return new ServiceMessage( code, severity, message );
    }

    public static ServiceMessage newDefaultErrorMessage( String message ) {
        return newErrorMessage( ServiceMessage.SEVERITY_ERROR, message );
    }

    public static ServiceMessage newDefaultWarningMessage( String message ) {
        return newWarningMessage( ServiceMessage.SEVERITY_WARNING, message );
    }

    public static ServiceMessage newDefaultSuccessMessage( String message ) {
        return newSuccessMessage( ServiceMessage.SEVERITY_SUCCESS, message );
    }

    public static ServiceMessage newDefaultInfoMessage( String message ) {
        return newInfoMessage( ServiceMessage.SEVERITY_INFO, message );
    }

    public static ServiceMessage newErrorMessage( String code, String message ) {
        return newMessage( code, ServiceMessage.SEVERITY_ERROR, message );
    }

    public static ServiceMessage newWarningMessage( String code, String message ) {
        return newMessage( code, ServiceMessage.SEVERITY_WARNING, message );
    }

    public static ServiceMessage newSuccessMessage( String code, String message ) {
        return newMessage( code, ServiceMessage.SEVERITY_SUCCESS, message );
    }

    public static ServiceMessage newInfoMessage( String code, String message ) {
        return newMessage( code, ServiceMessage.SEVERITY_INFO, message );
    }

    public static void addAllBySeverity(ServiceResponse response, List<ServiceMessage> messages) {
        addErrors( response, filterBySeverity( messages, ServiceMessage.SEVERITY_ERROR ) );
        addWarnings( response, filterBySeverity( messages, ServiceMessage.SEVERITY_WARNING ) );
        addInfos( response, filterBySeverity( messages, ServiceMessage.SEVERITY_INFO ) );
        addSuccess( response, filterBySeverity( messages, ServiceMessage.SEVERITY_SUCCESS ) );
    }

}
