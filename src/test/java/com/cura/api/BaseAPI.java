package com.cura.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseAPI {

    protected static Properties prop = new Properties();

    @BeforeClass
    public void setUp() {
        try {
            // Use your current absolute path
            FileInputStream fis = new FileInputStream(
                    "C:\\Users\\alame\\eclipse-workspace\\seleniumautomation"
                    + "\\src\\test\\java\\resources\\configFiles\\config.properties"
            );

            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set base URI for all tests
        RestAssured.baseURI = prop.getProperty("base.url");
        System.out.println("Base URI set to: " + RestAssured.baseURI);
    }

    // Helper method to get API key
    protected String getApiKey() {
        return prop.getProperty("api.key");
    }
}
