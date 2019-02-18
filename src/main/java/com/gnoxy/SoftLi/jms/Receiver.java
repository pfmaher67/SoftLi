/*
 * Copyright 2019 Patrick Maher<dev@gnoxy.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gnoxy.SoftLi.jms;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnoxy.SoftLi.controller.SoftLiRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "SoftLi.checkRights.inbound")
    @SendTo("SoftLiRequests.outbound")
    public String receiveMessage(String softLiRequestJson) throws JMSException {
        System.out.println("\n\nreceiveMessage:");
        System.out.println("Received String <" + softLiRequestJson + ">");
        ObjectMapper mapper = new ObjectMapper();
        try {
            SoftLiRequest softLiRequest = mapper.readValue(softLiRequestJson, SoftLiRequest.class);
            System.out.println("Converted to Object: " + softLiRequest.toString());
        } catch (IOException ex) {
            Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Message Received <" + softLiRequestJson + ">";
    }

}
