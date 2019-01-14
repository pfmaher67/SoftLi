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

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@ConfigurationProperties(prefix = "manifest-values")
@EnableConfigurationProperties
@Configuration
public class ManifestsInitializer {

    private final List<ManifestTemplate> manifestTemplates = new ArrayList<>();


    public ManifestsInitializer() {
    }

    public List<ManifestTemplate> getManifestTemplates() {
        return this.manifestTemplates;
    }
    
    public static class ManifestTemplate {
        private String imageId;
        private String swReleaseId;
        
        public void setImageId(String imageId) {
            this.imageId = imageId;
        }
        public String getImageId() {
            return imageId;
        }
        
        public void setSwReleaseId(String swReleaseId) {
            this.swReleaseId = swReleaseId;
        }
        public String getSwReleaseId() {
            return swReleaseId;
        }
        
    }  // End ManifestTemplate

} 