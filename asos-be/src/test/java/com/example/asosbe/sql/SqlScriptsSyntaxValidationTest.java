package com.example.asosbe.sql;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

class SqlScriptsSyntaxValidationTest {

    @Test
    void validateSqlScriptsSequentially() {
        Path sqlDirectory = Paths.get("src/main/resources/init-sql");

        Assertions.assertTrue(Files.exists(sqlDirectory), "SQL directory does not exist: " + sqlDirectory.toAbsolutePath());

        List<String> scriptExecutionOrder = List.of("init.sql");

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "")) {
            for (String scriptName : scriptExecutionOrder) {
                Path scriptPath = sqlDirectory.resolve(scriptName);
                Assertions.assertTrue(Files.exists(scriptPath), "SQL file does not exist: " + scriptPath);

                validateSqlFile(scriptPath, connection);
            }
        } catch (Exception e) {
            Assertions.fail("Failed to validate SQL scripts", e);
        }
    }

    private void validateSqlFile(Path sqlFilePath, Connection connection) {
        try (FileReader reader = new FileReader(sqlFilePath.toFile())) {
            RunScript.execute(connection, reader);
            System.out.println("Valid SQL script: " + sqlFilePath.getFileName());
        } catch (Exception e) {
            Assertions.fail("Invalid SQL syntax in file: " + sqlFilePath.getFileName(), e);
        }
    }

}
