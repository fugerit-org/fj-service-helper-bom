package org.fugerit.java.emp.sm.service;

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

    public static void addAllBySeverity(ServiceResponse response, List<ServiceMessage> messages) {
        addErrors( response, filterBySeverity( messages, ServiceMessage.SEVERITY_ERROR ) );
        addWarnings( response, filterBySeverity( messages, ServiceMessage.SEVERITY_WARNING ) );
        addInfos( response, filterBySeverity( messages, ServiceMessage.SEVERITY_INFO ) );
        addSuccess( response, filterBySeverity( messages, ServiceMessage.SEVERITY_SUCCESS ) );
    }

}
