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
package com.gnoxy.SoftLi.am;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 *  * @author Patrick Maher<dev@gnoxy.com>
 *
 */
@Entity
@Table(name = "LicenseModel")
public class LicenseModel {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "licenseMetricId")
    private int licenseMetricId = -1;
    @Column(name = "softwareCategoryId")
    private int softwareCategoryId = -1;
    @Transient
    private LicenseMetric metric;
    @Transient
    private SoftwareCategory category;

    public LicenseModel() {

    }

//      These two constructors were only used for testing to confirm the int values could
//      be converted to Metric and Category.
//    public LicenseModel(String id, int licenseMetricId) {
//        this.id = id;
//        this.licenseMetricId = licenseMetricId;
//    }
//    public LicenseModel(String id, int softwareCategoryId) {
//        this.id = id;
//        this.softwareCategoryId = softwareCategoryId;
//    }
    public LicenseModel(String id, LicenseMetric metric, SoftwareCategory category) {
        this.id = id;
        this.metric = metric;
        this.category = category;
        licenseMetricId = metric.getValue();
        softwareCategoryId = category.getValue();
    }

    public String getId() {
        return id;
    }

    public LicenseMetric getLicenseMetric() {
        if (metric == null) {
            metric = (LicenseMetric) LicenseMetric.getByValue(LicenseMetric.class, licenseMetricId);
        }
        return metric;
    }

    public SoftwareCategory getSoftwareCategory() {
        if (category == null) {
            category = (SoftwareCategory) SoftwareCategory.getByValue(SoftwareCategory.class, softwareCategoryId);
        }
        return category;
    }
    
    @Override
    public String toString() {
        // use the methods to ensure the metric and category have been initialized.
        return String.format("LicenseModel[id=%s, name=%s, licenseMetric=%s, softwareCategory=%s]",
                id, name, getLicenseMetric().getDescription(), getSoftwareCategory().getDescription());
    }    

}
