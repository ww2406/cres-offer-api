package com.ww.cresofferapi.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Configuration
public class OracleConfiguration {
    private final ResourceLoader resourceLoader;
    private final String tempDir = Files.createTempDirectory("Wallet").toString();

    private void extractFile(String fileName) throws IOException {
        // Extract the file from JAR to temp directory
        InputStream is = Objects.requireNonNull(resourceLoader.getClassLoader()).getResourceAsStream(Path.of("Wallet_NUEZ3045UIJ37YGF",fileName).toString());
        File file = new File(tempDir, fileName);
        Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private void configureOracle() {
        try {
            String[] files = {"ojdbc.properties", "tnsnames.ora", "ewallet.p12", "ewallet.pem", "cwallet.sso"};
            for (String file: files) {
                extractFile(file);
            }
            // Set location to extracted file
            System.setProperty("oracle.net.tns_admin", tempDir);
        } catch (Exception e) {
            System.out.println("Failed to load tnsnames.ora");
            e.printStackTrace();
        }
    }

    public OracleConfiguration(ResourceLoader resourceLoader) throws IOException {
        this.resourceLoader = resourceLoader;
        configureOracle();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
        dataSourceBuilder.url(String.format("jdbc:oracle:thin:@%s?oracle.jdbc.fanEnabled=false", System.getenv("SPRING_CRES_ORACLE_INSTANCE")));
        dataSourceBuilder.username("CRES_APP");
        dataSourceBuilder.password(System.getenv("SPRING_CRES_APP_PWD"));

        return dataSourceBuilder.build();
    }
}
