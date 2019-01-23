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

@Entity
@Table(name = "LicenseRight")
public class LicenseRight {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "appId")
    private String appId;
    @Column(name = "swReleaseId")
    private String swReleaseId;
    @Column(name = "qtyOwned")
    private long qtyOwned;
    @Column(name = "qtyReserved")
    private long qtyReserved;
    
    public LicenseRight() {
        
    }
    
    public LicenseRight(String appID, String swReleaseID, long quantity) {
        this.id = appID + "-" + swReleaseID;
        this.appId = appID;
        this.swReleaseId = swReleaseID;
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

    public String getSwReleaseId() {
        return swReleaseId;
    }
    
    public void setSwReleaseId(String swReleaseId) {
        this.swReleaseId = swReleaseId;
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
        return String.format("LicenseRight[appId=%s, swReleaseId=%s, qtyOwned=%d, qtyReserved=%d]",
                appId, swReleaseId, qtyOwned, qtyReserved);
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
                this.swReleaseId,
                this.qtyOwned
        );
        l.reserveRights(qtyReserved);
        return l;
    }

}
