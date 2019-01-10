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

import com.gnoxy.SoftLi.Initializer;
import com.gnoxy.SoftLi.am.LicenseRight;
import com.gnoxy.SoftLi.am.StatusMessage;
import com.gnoxy.SoftLi.am.LicenseRights;
import java.util.HashMap;
import javax.annotation.PostConstruct;
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


    
    @RequestMapping("/reserveRights")
    @ResponseBody
    public StatusMessage reserve(@RequestParam(value = "appID", defaultValue = "0") String appID,
            @RequestParam(value = "imageID") String imageID,
            @RequestParam(value = "vCPUs") String vCPUs,
            @RequestParam(value = "ram") String ram,
            @RequestParam(value = "instances") String instances) {
        return licenseRights.reserveRights(appID, imageID,
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

    @PostConstruct
    public void init() {
        licenseRights = Initializer.getLicenseRights();
    }
}
