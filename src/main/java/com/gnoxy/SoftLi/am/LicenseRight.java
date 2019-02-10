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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "LicenseRight")
@Proxy(lazy=false)
public class LicenseRight {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "appId")
    private String appId;
//    @Column(name = "licenseModelId")
//    private String licenseModelId;
    @Column(name = "qtyOwned")
    private long qtyOwned;
    @Column(name = "qtyReserved")
    private long qtyReserved;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="licenseModelId")
    private LicenseModel licenseModel;    
    
    public LicenseRight() {
        
    }
    
    public LicenseRight(String appID, LicenseModel licenseModel, long quantity) {
        this.id = appID + "-" + licenseModel.getId();
        this.appId = appID;
//        this.licenseModelId = licenseModelId;
        this.qtyOwned = quantity;
        qtyReserved = 0;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

//    public String getLicenseModelId() {
//        return licenseModelId;
//    }
//    
//    public void setLicenseModelId(String licenseModelId) {
//        this.licenseModelId = licenseModelId;
//    }
    
    public LicenseModel getLicenseModel() {
        return licenseModel;
    }
    
    public void setLicenseModel(LicenseModel licenseModel) {
        this.licenseModel = licenseModel;
    }

    public long getQuantityOwned() {
        return qtyOwned;
    }
    
    public void setQuantityOwned(long qtyOwned) {
        this.qtyOwned = qtyOwned;
    }

    public long getQuantityReserved() {
        return qtyReserved;
    }
    
    public void setQuantityReserved(long qtyReserved) {
        this.qtyReserved = qtyReserved;
    }
    
    @Override
    public String toString() {
        return String.format("LicenseRight[appId=%s, licenseModel=%s, qtyOwned=%d, qtyReserved=%d]",
                appId, licenseModel, qtyOwned, qtyReserved);
    }

    public boolean reserveRights(long quantity) {
        boolean status = false;
        if (qtyReserved + quantity <= qtyOwned) {
            qtyReserved += quantity;
            status = true;
        }
        return status;
    }    
    public boolean hasAvailableRights(long quantity) {
        boolean status = false;
        if (qtyReserved + quantity <= qtyOwned) {
            status = true;
        }
        return status;
    }
    
    public void releaseRights(long quantity) {
        if (quantity <= qtyReserved) {
            qtyReserved -= quantity;
        } else {
            qtyReserved = 0;
        }
    }
    
    public LicenseRight copy() {
        LicenseRight l = new LicenseRight(
                this.appId,
                this.licenseModel,
                this.qtyOwned
        );
        l.reserveRights(qtyReserved);
        return l;
    }

}
