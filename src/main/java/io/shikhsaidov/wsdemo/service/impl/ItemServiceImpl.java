package io.shikhsaidov.wsdemo.service.impl;

import io.shikhsaidov.wsdemo.enums.Action;
import io.shikhsaidov.wsdemo.enums.State;
import io.shikhsaidov.wsdemo.enums.Type;
import io.shikhsaidov.wsdemo.model.Item;
import io.shikhsaidov.wsdemo.repository.ItemRepository;
import io.shikhsaidov.wsdemo.response.ChangeActivityResponse;
import io.shikhsaidov.wsdemo.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final SimpMessagingTemplate messagingTemplate;
    private final ItemRepository itemRepository;


    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public void removeItem(long docNo) {
        log.info("removing docNo: {}", docNo);

        Optional<Item> itemOptional = itemRepository.findById(docNo);

        if (itemOptional.isPresent()) {
            var request = new ChangeActivityResponse();
            request.setAction(Action.DELETE);
            request.setType(Type.COLLEAGUE);
            request.setState(State.ACTIVE);
            request.setDocNo(docNo);
            messagingTemplate.convertAndSend("/topic/activity", request);
            log.info("docNo: {} removed successfully", docNo);

            itemRepository.delete(itemOptional.get());
        }
    }
}
