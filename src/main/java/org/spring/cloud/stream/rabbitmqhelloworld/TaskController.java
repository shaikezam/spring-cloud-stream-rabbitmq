package org.spring.cloud.stream.rabbitmqhelloworld;

import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/webapi")
public class TaskController {

    private MessageChannel messageChannel;

    public TaskController(TaskBinding taskBinding) {
        messageChannel = taskBinding.outboundTasks();
    }

    @PostMapping(value = "/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendTask(@RequestBody Task task) {
        messageChannel.send(MessageBuilder.withPayload(task).build());
    }

    @GetMapping("/tasks")
    public String sendTask() {
        return "Hello";
    }
}
