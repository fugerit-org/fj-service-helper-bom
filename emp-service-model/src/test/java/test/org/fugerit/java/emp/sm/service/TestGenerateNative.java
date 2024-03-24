package test.org.fugerit.java.emp.sm.service;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.emp.sm.service.ServiceMessage;
import org.fugerit.java.emp.sm.service.ServiceResponse;
import org.fugerit.java.nhg.GenerateReflectConfig;
import org.fugerit.java.nhg.ReflectConfigUtil;
import org.fugerit.java.nhg.reflect.config.Entry;
import org.fugerit.java.nhg.reflect.config.EntryHelper;
import org.fugerit.java.nhg.reflect.config.EntryMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
class TestGenerateNative {

    @Test
    void generateNative() throws IOException {
        String basePath = "src/main/resources/META-INF/native-image";
        File reflectConfig = new File( basePath, "reflect-config.json" );
        if ( !reflectConfig.getParentFile().exists() ) {
            reflectConfig.getParentFile().mkdirs();
        }
        log.info( "{} path : {}", reflectConfig.getName(), reflectConfig.getCanonicalPath() );
        ReflectConfigUtil util = ReflectConfigUtil.ALL_METHODS;
        List<Entry> entries = Arrays.asList(
                EntryHelper.fixedOrder( EntryHelper.addDefaultInit( util.toEntry( ServiceMessage.class ) ) ),
                EntryHelper.fixedOrder( EntryHelper.addDefaultInit( util.toEntry( ServiceResponse.class ) ) )
        );
        try (FileWriter writer = new FileWriter( reflectConfig )){
            GenerateReflectConfig config = new GenerateReflectConfig();
            config.generate( writer, entries );
        }
        Assertions.assertTrue( reflectConfig.exists() );
    }

}
