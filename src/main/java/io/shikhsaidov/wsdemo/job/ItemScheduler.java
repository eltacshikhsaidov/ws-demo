package io.shikhsaidov.wsdemo.job;

import io.shikhsaidov.wsdemo.enums.Action;
import io.shikhsaidov.wsdemo.enums.State;
import io.shikhsaidov.wsdemo.enums.Type;
import io.shikhsaidov.wsdemo.model.Item;
import io.shikhsaidov.wsdemo.repository.ItemRepository;
import io.shikhsaidov.wsdemo.response.ChangeActivityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemScheduler {

    private final ItemRepository repository;
    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 5000)
    public void checkItems() {
        log.info("checking if any items present...");
        var items = repository.findAll();
        log.info("items: {}", items);

        if (items.isEmpty()) {
            var colleagueNames = Arrays.asList(
                    "John",
                    "Alice",
                    "Bob",
                    "Eva",
                    "Michael",
                    "Sophie",
                    "David",
                    "Emily"
            );

            var usedNames = new ArrayList<>();

            log.info("reinitialization started");
            Random random = new Random();

            for (int i = 1; i < colleagueNames.size(); i++) {
                String randomName;
                do {
                    randomName = colleagueNames.get(random.nextInt(colleagueNames.size()));
                } while (usedNames.contains(randomName));

                usedNames.add(randomName);

                var row = new Item(randomName);
                repository.save(row);
            }

            var request = new ChangeActivityResponse();
            request.setAction(Action.REFRESH);
            request.setType(Type.COLLEAGUE);
            request.setState(State.ACTIVE);
            request.setDocNo(null);
            messagingTemplate.convertAndSend("/topic/activity", request);
            log.info("reinitialization ended");
        }

        log.info("checking items ended");
    }

}
