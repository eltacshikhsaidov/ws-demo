package io.shikhsaidov.wsdemo.controller;

import io.shikhsaidov.wsdemo.response.ChangeActivityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class WebSocketController {

    @MessageMapping("/changeActivity")
    @SendTo("/topic/activity")
    public ChangeActivityResponse changeActivity(ChangeActivityResponse response) {
        log.info("response: {}", response);
        return response;
    }
}
