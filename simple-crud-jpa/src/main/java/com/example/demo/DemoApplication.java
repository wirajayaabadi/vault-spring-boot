package com.example.demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoApplication implements CommandLineRunner {
  
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      System.out.println("Hello this is quickstart application!");
      logger.info("INFO log - Application started");
      logger.warn("WARN log - Just a warning");
      logger.error("ERROR log - Something went wrong");
      logger.debug("DEBUG log - Debugging info");
    }

}