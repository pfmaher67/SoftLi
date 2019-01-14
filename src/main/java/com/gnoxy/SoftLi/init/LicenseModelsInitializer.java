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
package com.gnoxy.SoftLi.init;

import com.gnoxy.SoftLi.am.LicenseModels;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@ConfigurationProperties(prefix = "software-model-values")
@EnableConfigurationProperties
@Configuration
public class SoftwareModelsInitializer {

    private final List<SoftwareModelTemplate> softwareModelTemplates = new ArrayList<>();

    public SoftwareModelsInitializer() {
        System.out.println("Inside SoftwareModelsInitializer");

    }

    public List<SoftwareModelTemplate> getSoftwareModelTemplates() {
        System.out.println("...Inside SoftwareModelInitializers, size: " + softwareModelTemplates.size());
        return this.softwareModelTemplates;
    }
    
    public LicenseModels getLicenseModels() {
        System.out.println("Inside getLicenseModels");
        LicenseModels l = new LicenseModels();
        Iterator<SoftwareModelTemplate> i = this.getSoftwareModelTemplates().iterator();
        while(i.hasNext()) {
            System.out.println("getLicenseModels: Model " + i.next().getId()); 
        }         
        return l;
    }

    public static class SoftwareModelTemplate {
        private String id;
        private String metric;
        private String category;
        
        public void setID(String id) {
            System.out.println("Setting id: " + id);
            this.id = id;
        }
        public String getId() {
            return id;
        }
        
        public void setMetric(String metric) {
            this.metric = metric;
        }
        public String getMetric() {
            return metric;
        }
        
        public void setCategory(String category) {
            this.category = category;
        }
        public String getCategory() {
            return category;
        }
        
    }  // End SoftwareModelInitiator

} 