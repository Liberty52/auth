package com.liberty52.auth.global.config;

import org.junit.jupiter.api.DisplayName;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@DisplayName("Redis Test Containers")
@Configuration
@ActiveProfiles("test")
public class RedisTestContainer {

    private static final String REDIS_DOCKER_IMAGE = "redis:alpine";

    static {    // (1)
        GenericContainer<?> REDIS_CONTAINER =
                new GenericContainer<>(DockerImageName.parse(REDIS_DOCKER_IMAGE))
                        .withExposedPorts(6379)
                        .withReuse(true);

        REDIS_CONTAINER.start();    // (2)

        // (3)
        System.setProperty("spring.data.redis.host", REDIS_CONTAINER.getHost());
        System.setProperty("spring.data.redis.port", REDIS_CONTAINER.getMappedPort(6379).toString());
    }
}