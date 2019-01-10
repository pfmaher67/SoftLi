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


public class LicenseRight {

    private final String appID;
    private final String swReleaseID;
    private final long qtyOwned;
    private long qtyReserved;
    
    public LicenseRight(String appID, String swReleaseID, long quantity) {
        this.appID = appID;
        this.swReleaseID = swReleaseID;
        this.qtyOwned = quantity;
        qtyReserved = 0;
    }
    
    public String generateKey() {
        return appID + "-" + swReleaseID;
    }
    
    public String getAppID() {
        return appID;
    }
    
    public String getSwReleaseID() {
        return swReleaseID;
    }
    
    public long getQuantityOwned() {
        return qtyOwned;
    }
    
    public long getQuantityReserved() {
        return qtyReserved;
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
    

}
