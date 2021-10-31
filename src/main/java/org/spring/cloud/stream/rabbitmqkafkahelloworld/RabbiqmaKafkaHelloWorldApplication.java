package org.spring.cloud.stream.rabbitmqkafkahelloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(TaskBinding.class)
@SpringBootApplication
public class RabbiqmaKafkaHelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbiqmaKafkaHelloWorldApplication.class, args);
    }

    @StreamListener(TaskBinding.TASK_CHANNEL_INPUT)
    public void processTaskChannel(String msg) {
        System.out.println(msg);
    }

}
