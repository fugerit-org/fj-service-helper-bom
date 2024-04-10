package teat.org.fugerit.java.simple.config;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.simple.config.ConfigParams;
import org.fugerit.java.simple.config.SimpleConfigurable;

@Slf4j
public class SimpleConfigurableObject implements SimpleConfigurable {

    @Override
    public void configure(ConfigParams configParams) {
        log.info( "configuration ok! : {}", configParams );
    }

}
