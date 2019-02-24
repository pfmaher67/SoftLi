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
package com.gnoxy.SoftLi.data;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class SoftLiRequest {

    private String appID;
    private String imageID;
    private String vCPUs;
    private String ram;
    private String instances;

    public SoftLiRequest() {

    }

    public SoftLiRequest(String appId, String imageId, String vCPUs, String ram, String instances) {
        this.appID = appId;
        this.imageID = imageId;
        this.vCPUs = vCPUs;
        this.ram = ram;
        this.instances = instances;
    }

    public String getAppId() {
        return appID;
    }

    public void setAppId(String appId) {
        this.appID = appId;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getvCPUs() {
        return vCPUs;
    }

    public void setvCPUs(String vCPUs) {
        this.vCPUs = vCPUs;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getInstances() {
        return instances;
    }

    public void setInstances(String instances) {
        this.instances = instances;
    }

    @Override
    public String toString() {
        return String.format("SoftLiRequest[appId=%s, imageId=%s, vCPUs=%s, ram=%s, instances=%s]",
                appID, imageID, vCPUs, ram, instances);
    }
}

