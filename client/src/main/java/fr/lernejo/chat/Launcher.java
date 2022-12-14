package fr.lernejo.chat;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Launcher.class);
        try {
            var rabbitTemplate = context.getBean(RabbitTemplate.class);

            Scanner scan = new Scanner(System.in);
            System.out.println("Input a message, we will sent it for you (q for quit)");
            Boolean loop = true;
            while(loop) {
                String message = scan.nextLine();
                if ("q".equals(message)) {
                    loop = false;
                } else {
                    rabbitTemplate.convertAndSend("", "chat_messages", message);
                    System.out.println("Message sent. Input a message, we will send it for you");
                }
            }
            System.out.println("Bye");
        } finally {
            context.stop();
        }
    }
}
