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

/**
 *
 *  * @author Patrick Maher<dev@gnoxy.com>
 * 
 */
public class LicenseModel {
    private final String id;
    private final LicenseMetric metric;
    private final SoftwareCategory category;
    
    public LicenseModel(String id, LicenseMetric metric, SoftwareCategory category) {
        this.id = id;
        this.metric = metric;
        this.category = category;
    }
    
    public String getID() {
        return id;
    }
    
    public LicenseMetric getLicenseMetric() {
        return metric;
    }
    
    public SoftwareCategory getSoftwareCategory() {
        return category;
    }
    
}
