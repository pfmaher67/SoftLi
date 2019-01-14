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

import com.gnoxy.SoftLi.am.LicenseMetric;
import com.gnoxy.SoftLi.am.LicenseModel;
import com.gnoxy.SoftLi.am.SoftwareCategory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@ConfigurationProperties(prefix = "license-model-values")
@EnableConfigurationProperties
@Configuration
public class LicenseModelsInitializer {

    private final List<LicenseModelTemplate> licenseModelTemplates = new ArrayList<>();

    public LicenseModelsInitializer() {
    }

    public List<LicenseModelTemplate> getLicenseModelTemplates() {
        return this.licenseModelTemplates;
    }

    public static class LicenseModelTemplate {

        private String id;
        private String metric;
        private String category;

        public void setID(String id) {
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

        public LicenseModel getModel() {
            return new LicenseModel(
                    id,
                    findLicenseMetric(metric),
                    findCategory(category)
            );
        }

        private LicenseMetric findLicenseMetric(String m) {
            if (m.equals(LicenseMetric.VCPU.getDescription())) {
                return LicenseMetric.VCPU;
            }
            if (m.equals(LicenseMetric.INSTANCE.getDescription())) {
                return LicenseMetric.INSTANCE;
            }
            return LicenseMetric.RAM;
        }

        private SoftwareCategory findCategory(String c) {
            if (c.equals(SoftwareCategory.APPLICATION.getDescription())) {
                return SoftwareCategory.APPLICATION;
            }
            return SoftwareCategory.INFRASTRUCTURE;
        }

    }  // End LicenseModelTemplate

}
