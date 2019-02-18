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
package com.gnoxy.SoftLi.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gnoxy.SoftLi.am.LicenseRight;
import com.gnoxy.SoftLi.am.StatusMessage;
import com.gnoxy.SoftLi.am.LicenseRightsManager;
import com.gnoxy.SoftLi.repository.ImageRepository;
import com.gnoxy.SoftLi.repository.LicenseRightRepository;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@RestController
public class SoftLiController {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${am.database}")
    private String db;
    LicenseRightsManager lrm;

    @Autowired
    private LicenseRightRepository licenseRightRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/reserveRights")
    @ResponseBody
    public StatusMessage reserve(@RequestParam(value = "appID", defaultValue = "0") String appID,
            @RequestParam(value = "imageID") String imageID,
            @RequestParam(value = "vCPUs") String vCPUs,
            @RequestParam(value = "ram") String ram,
            @RequestParam(value = "instances") String instances) {

        return lrm.reserveRights(appID, imageID,
                Long.parseLong(vCPUs), Long.parseLong(ram), Integer.parseInt(instances));
    }

    @RequestMapping("/releaseRights")
    @ResponseBody
    public StatusMessage release(@RequestParam(value = "appID", defaultValue = "0") String appID,
            @RequestParam(value = "imageID") String imageID,
            @RequestParam(value = "vCPUs") String vCPUs,
            @RequestParam(value = "ram") String ram,
            @RequestParam(value = "instances") String instances) {
        return lrm.releaseRights(appID, imageID,
                Long.parseLong(vCPUs), Long.parseLong(ram), Integer.parseInt(instances));
    }

    @RequestMapping("/checkRights")
    @ResponseBody
    public StatusMessage check(@RequestParam(value = "appID", defaultValue = "0") String appID,
            @RequestParam(value = "imageID") String imageID,
            @RequestParam(value = "vCPUs") String vCPUs,
            @RequestParam(value = "ram") String ram,
            @RequestParam(value = "instances") String instances) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            SoftLiRequest softLiRequest = new SoftLiRequest(appID, imageID, vCPUs, ram, instances);
            String jsonRequest = mapper.writeValueAsString(softLiRequest);
            jmsTemplate.convertAndSend("SoftLi.checkRights.inbound", jsonRequest);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SoftLiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lrm.checkRights(appID, imageID,
                Long.parseLong(vCPUs), Long.parseLong(ram), Integer.parseInt(instances));
    }

    @RequestMapping("/checkRights2")
    @ResponseBody
    public StatusMessage check2(@RequestParam(value = "appID", defaultValue = "0") String appID,
            @RequestParam(value = "imageID") String imageID,
            @RequestParam(value = "vCPUs") String vCPUs,
            @RequestParam(value = "ram") String ram,
            @RequestParam(value = "instances") String instances) {

        StatusMessage s = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            SoftLiRequest softLiRequest = new SoftLiRequest(appID, imageID, vCPUs, ram, instances);
            
            String jsonRequest = mapper.writeValueAsString(softLiRequest);
            Object o = jmsMessagingTemplate.convertSendAndReceive("SoftLi.checkRights2.inbound", jsonRequest, String.class);
            if (o != null) {
                System.out.println("Object: " + o.toString());
                try {
                    s = mapper.readValue(o.toString(), StatusMessage.class);
                } catch (IOException ex) {
                    Logger.getLogger(SoftLiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Object is null");
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SoftLiController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return lrm.checkRights(appID, imageID,
//                Long.parseLong(vCPUs), Long.parseLong(ram), Integer.parseInt(instances));

        return s;
    }

    @RequestMapping("/listRights")
    public List<LicenseRight> list() {
        return licenseRightRepository.findAll();
    }

    @PostConstruct
    public void init() {
        lrm = new LicenseRightsManager(imageRepository, licenseRightRepository);

        System.out.println("Running application: " + appName);
        System.out.println("Using Repository: " + db);

    }
}
