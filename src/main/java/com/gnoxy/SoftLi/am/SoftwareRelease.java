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

/**
 *
 *  * @author Patrick Maher<dev@gnoxy.com>
 *
 */
@Entity
@Table(name = "SoftwareRelease")
public class SoftwareRelease {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "version")
    private String version;
    @Column(name = "licenseModelId")
    private String licenseModelId;

    public SoftwareRelease() {

    }

    public SoftwareRelease(String id, String name, String version, String licenseModelId) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.licenseModelId = licenseModelId;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        // use the methods to ensure the metric and category have been initialized.
        return String.format("LicenseModel[id=%s, name=%s, version=%s, licenseModelId=%s]",
                id, name, version, licenseModelId);
    }    

}
