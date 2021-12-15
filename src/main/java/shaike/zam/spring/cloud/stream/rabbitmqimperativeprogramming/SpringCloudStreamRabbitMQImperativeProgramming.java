package shaike.zam.spring.cloud.stream.rabbitmqimperativeprogramming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableBinding(TaskBinding.class)
@EnableScheduling
public class SpringCloudStreamRabbitMQImperativeProgramming {

    private MessageChannel messageChannel;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamRabbitMQImperativeProgramming.class, args);
    }

    public SpringCloudStreamRabbitMQImperativeProgramming(TaskBinding taskBinding) {
        this.messageChannel = taskBinding.outboundTasks();
    }

    @Scheduled(fixedDelay = 1000)
    public void publishTask() {
        Task task = new Task.TaskBuilder().build();
        System.out.println("producing task: " + task);
        messageChannel.send(MessageBuilder.withPayload(task).build());
    }

    @StreamListener(TaskBinding.TASK_CHANNEL_INPUT)
    public void consumeTask(String msg) {
        System.out.println("consumed task: " + msg);
    }

}
