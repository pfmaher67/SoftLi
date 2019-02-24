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

import com.gnoxy.SoftLi.am.LicenseRight;
import com.gnoxy.SoftLi.repository.LicenseRightRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@Controller
public class SoftLiController {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${am.database}")
    private String db;

//    @Autowired
//    LicenseRightsManager lrm;

    @Autowired
    private LicenseRightRepository licenseRightRepository;

    @GetMapping("/listRights2")
    public String list2(Model model) {
        List<LicenseRight> rights = licenseRightRepository.findAll();
        String rightsString = new String();
        for (LicenseRight r : rights) {
            rightsString = rightsString.concat(r.toString());
        }
        System.out.println("listRights2 Result: " + rightsString);
        model.addAttribute("rightsString", rightsString);
        return "results";
    }

    @PostConstruct
    public void init() {
        System.out.println("Running(2) application: " + appName);
        System.out.println("Using(2) Repository: " + db);
    }
}
