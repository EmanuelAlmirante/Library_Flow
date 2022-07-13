package com.twoimpulse.libraryflow;

import java.time.Clock;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryFlowApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

    @Bean
    public Clock clock() {

        return Clock.systemUTC();
    }
}
