package com.example.demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;

@Service
public class KafkaConsumerService {

    private final MessageRepository repository;

    public KafkaConsumerService(MessageRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "my-topic", groupId = "myGroup")
    public void consume(String message) {

        System.out.println("Received : " + message);

        repository.save(new Message(message));

        System.out.println("Saved into MySQL");
    }
}