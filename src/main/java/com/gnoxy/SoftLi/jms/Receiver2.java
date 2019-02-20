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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gnoxy.SoftLi.am.LicenseRightsManager;
import com.gnoxy.SoftLi.am.StatusMessage;
import com.gnoxy.SoftLi.controller.SoftLiRequest;
import com.gnoxy.SoftLi.repository.ImageRepository;
import com.gnoxy.SoftLi.repository.LicenseRightRepository;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
//@Service
public class Receiver2 {

    @Autowired
    private LicenseRightsManager lrm;

//    @Autowired
//    private LicenseRightRepository licenseRightRepository;
//
//    @Autowired
//    private ImageRepository imageRepository;

    public Receiver2() {
//        lrm = new LicenseRightsManager(imageRepository, licenseRightRepository);
//        lrm = new LicenseRightsManager();
//        System.out.println("\n\n\nInitialized Receiver2");
//        if (licenseRightRepository == null) {
//            System.out.println("licenseRightRepository is null");
//        }
//        if (imageRepository == null) {
//            System.out.println("imageRepository is null");
//        }
 
    }

    @JmsListener(destination = "SoftLi.checkRights2.inbound")
    @SendTo("SoftLiRequests2.outbound")
    public String receiveMessage(String softLiRequestJson) throws JMSException {
        System.out.println("\n\nreceiveMessage2:");
        System.out.println("Received String <" + softLiRequestJson + ">");
        StatusMessage m = null;
//        m = new StatusMessage(StatusMessage.SUCCESS, "This is a test");
        ObjectMapper mapper = new ObjectMapper();
        try {
            SoftLiRequest softLiRequest = mapper.readValue(softLiRequestJson, SoftLiRequest.class);
            if (softLiRequest != null) {
                System.out.println("Got SoftLiRequest: " + softLiRequest.toString());
                if (lrm != null) {
                m = lrm.checkRights(softLiRequest.getAppId(), softLiRequest.getImageID(),
                        Long.parseLong(softLiRequest.getvCPUs()),
                        Long.parseLong(softLiRequest.getRam()),
                        Integer.parseInt(softLiRequest.getInstances()));
                } else {
                    System.out.println("The LicenseRightsMapper is null");
                }
            } else {
                System.out.println("SoftLiRequest is null");
            }
            if (m != null) {
                System.out.println("Status Message: " + m.toString());
            } else {
                System.out.println("Status is null");
            }
        } catch (IOException ex) {
            Logger.getLogger(Receiver2.class.getName()).log(Level.SEVERE, null, ex);
        }

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String s = null;
        try {
            s = mapper.writeValueAsString(m);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Receiver2.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return "{\"status\": 0, \"message\": \"Test\", \"elements\": null}";
        return s;
    }

}
