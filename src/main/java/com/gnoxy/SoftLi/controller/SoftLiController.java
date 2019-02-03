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

import com.gnoxy.SoftLi.am.LicenseModels;
import com.gnoxy.SoftLi.am.LicenseRight;
import com.gnoxy.SoftLi.am.StatusMessage;
import com.gnoxy.SoftLi.am.LicenseRights;
import com.gnoxy.SoftLi.am.LicenseRightsManager;
import com.gnoxy.SoftLi.am.Manifests;
import com.gnoxy.SoftLi.init.LicenseModelsInitializer;
import com.gnoxy.SoftLi.init.LicenseRightsInitializer;
import com.gnoxy.SoftLi.init.ManifestsInitializer;
import com.gnoxy.SoftLi.repository.LicenseModelRepository;
import com.gnoxy.SoftLi.repository.LicenseRightRepository;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@RestController
public class SoftLiController {

    private LicenseRights licenseRights;
    @Value("${spring.application.name}")
    private String appName;
    @Value("${am.database}")
    private String db;
    
    @Autowired
    private LicenseRightRepository licenseRightRepository;
    
    @Autowired
    private LicenseModelRepository licenseModelRepository;

    @Autowired
    private LicenseModelsInitializer lmi;
    
    @Autowired
    private ManifestsInitializer mi;
    
    @Autowired
    private LicenseRightsInitializer lri;

    @RequestMapping("/reserveRights")
    @ResponseBody
    public StatusMessage reserve(@RequestParam(value = "appID", defaultValue = "0") String appID,
            @RequestParam(value = "imageID") String imageID,
            @RequestParam(value = "vCPUs") String vCPUs,
            @RequestParam(value = "ram") String ram,
            @RequestParam(value = "instances") String instances) {
//        return licenseRights.reserveRights(appID, imageID,
//                Long.parseLong(vCPUs), Long.parseLong(ram), Integer.parseInt(instances));
        LicenseRightsManager lrm = new LicenseRightsManager();
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
        LicenseRightsManager lrm = new LicenseRightsManager();
        return lrm.releaseRights(appID, imageID,
                Long.parseLong(vCPUs), Long.parseLong(ram), Integer.parseInt(instances));
    }

    @RequestMapping("/createRights")
    public StatusMessage create(@RequestParam(value = "appID", defaultValue = "0") String appID,
            @RequestParam(value = "swReleaseID", defaultValue = "0") String swReleaseID,
            @RequestParam(value = "quantity", defaultValue = "0") String quantity) {
        return licenseRights.addRight(appID, swReleaseID, Long.parseLong(quantity));
    }

    @RequestMapping("/listRights")
    public HashMap<String, LicenseRight> list() {
        return licenseRights.getSoftwareLicenseRights();
    }
    
    @RequestMapping("/addRight")
    public StatusMessage add(@RequestParam(value = "appID", defaultValue = "0") String appID,
            @RequestParam(value = "swReleaseID", defaultValue = "0") String swReleaseID,
            @RequestParam(value = "quantity", defaultValue = "0") String quantity) {
        LicenseRight l = new LicenseRight(appID, swReleaseID, Long.parseLong(quantity));
        licenseRightRepository.save(l);
        return licenseRights.addRight(appID, swReleaseID, Long.parseLong(quantity));
    }

    @RequestMapping("/listRights2")
    public List<LicenseRight> list2() {
        return licenseRightRepository.findAll();
    }

    @PostConstruct
    public void init() {
        LicenseModels models = new LicenseModels();
        models.init(lmi);
        Manifests manifests = new Manifests(models);
        manifests.init(mi);
        licenseRights = new LicenseRights(models, manifests);
        licenseRights.init(lri);        
        
        System.out.println("Running application: " + appName);
        System.out.println("Using Repository: " + db);
                        
    }
}
