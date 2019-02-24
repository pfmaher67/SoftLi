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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnoxy.SoftLi.am.LicenseRight;
import com.gnoxy.SoftLi.am.StatusMessage;
import com.gnoxy.SoftLi.am.LicenseRightsManager;
import com.gnoxy.SoftLi.repository.LicenseRightRepository;
import com.gnoxy.SoftLi.data.SoftLiRequest;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@RestController
public class SoftLiRestController {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${am.database}")
    private String db;

    @Autowired
    LicenseRightsManager lrm;

    @Autowired
    private LicenseRightRepository licenseRightRepository;

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

    @PostMapping("/reserveRightsP/std")
    @ResponseBody
    public StatusMessage pReserve(@RequestBody String softLiRequestJson) {
        StatusMessage m = new StatusMessage(StatusMessage.NO_STATUS, "Something went wrong");
        System.out.println("reserveRightsP reqeust body: " + softLiRequestJson);
        ObjectMapper mapper = new ObjectMapper();
        try {
            SoftLiRequest softLiRequest = mapper.readValue(softLiRequestJson, SoftLiRequest.class);
            if (softLiRequest != null) {
                System.out.println("Got SoftLiRequest: " + softLiRequest.toString());
                if (lrm != null) {
                    m = lrm.reserveRights(softLiRequest.getAppId(), softLiRequest.getImageID(),
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
            Logger.getLogger(SoftLiRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
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

    @PostMapping("/releaseRightsP/std")
    @ResponseBody
    public StatusMessage pRelease(@RequestBody String softLiRequestJson) {
        StatusMessage m = new StatusMessage(StatusMessage.NO_STATUS, "Something went wrong");
        System.out.println("releaseRightsP reqeust body: " + softLiRequestJson);
        ObjectMapper mapper = new ObjectMapper();
        try {
            SoftLiRequest softLiRequest = mapper.readValue(softLiRequestJson, SoftLiRequest.class);
            if (softLiRequest != null) {
                System.out.println("Got SoftLiRequest: " + softLiRequest.toString());
                if (lrm != null) {
                    m = lrm.releaseRights(softLiRequest.getAppId(), softLiRequest.getImageID(),
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
            Logger.getLogger(SoftLiRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    @RequestMapping("/checkRights")
    @ResponseBody
    public StatusMessage check(@RequestParam(value = "appID", defaultValue = "0") String appID,
            @RequestParam(value = "imageID") String imageID,
            @RequestParam(value = "vCPUs") String vCPUs,
            @RequestParam(value = "ram") String ram,
            @RequestParam(value = "instances") String instances) {
        return lrm.checkRights(appID, imageID,
                Long.parseLong(vCPUs), Long.parseLong(ram), Integer.parseInt(instances));
    }

//    @RequestMapping("/createRights")
//    public StatusMessage create(@RequestParam(value = "appID", defaultValue = "0") String appID,
//            @RequestParam(value = "swReleaseID", defaultValue = "0") String swReleaseID,
//            @RequestParam(value = "quantity", defaultValue = "0") String quantity) {
//        return licenseRights.addRight(appID, swReleaseID, Long.parseLong(quantity));
//    }
//    @RequestMapping("/addRight")
//    public StatusMessage add(@RequestParam(value = "appID", defaultValue = "0") String appID,
//            @RequestParam(value = "swReleaseID", defaultValue = "0") String swReleaseID,
//            @RequestParam(value = "quantity", defaultValue = "0") String quantity) {
//        LicenseRight l = new LicenseRight(appID, swReleaseID, Long.parseLong(quantity));
//        licenseRightRepository.save(l);
//        return licenseRights.addRight(appID, swReleaseID, Long.parseLong(quantity));
//    }
    @RequestMapping("/listRights")
    public List<LicenseRight> list() {
        return licenseRightRepository.findAll();
    }

    @PostConstruct
    public void init() {
        System.out.println("Running application: " + appName);
        System.out.println("Using Repository: " + db);
    }
}
