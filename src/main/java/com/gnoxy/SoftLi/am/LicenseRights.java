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

import com.gnoxy.SoftLi.Initializer;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class LicenseRights {

    // All software license rights, indexed by the key: App ID - SW Release ID
    private final HashMap<String, LicenseRight> rights;
    // The manifest contains the list of SW Release IDs associated with an Image ID
    private final Manifests manifests;
    private final LicenseModels models;

    public LicenseRights() {
        rights = new HashMap<>();
        manifests = Initializer.getManifests();  // temporary measure
        models = Initializer.getLicenseModels();
    }

    public StatusMessage addRight(String appID, String swReleaseID, long quantity) {
        LicenseRight licenseRight = new LicenseRight(appID, swReleaseID, quantity);
        rights.put(licenseRight.generateKey(), licenseRight);
        return new StatusMessage(StatusMessage.SUCCESS, "Software License Right created.", licenseRight);
    }

    public StatusMessage reserveRights(String appID, String imageID,
            long vCPU, long ram, long instances) {
        StatusMessage m = null;
        String statusMsg = null;
        String slrKey;
        long quantity;
        // From the image id, check the manifest and get all the Software Release IDs associated with the image
        Set<String> swReleaseIDs = manifests.getManifest(imageID).getSwReleaseIDs();
        if (!swReleaseIDs.isEmpty()) {
            boolean rightsAvailable = true;
            statusMsg = appID + ":";
            // First check each swReleaseID to see if the App has enough rights to set
            for (String swReleaseID : swReleaseIDs) {
                slrKey = appID + "-" + swReleaseID;
                if (rights.containsKey(slrKey)) {
                    LicenseRight slr = rights.get(slrKey);
                    if (models.getModel(swReleaseID).getLicenseMetric().equals(LicenseMetric.INSTANCE)) {
                        quantity = instances;
                    } else if (models.getModel(swReleaseID).getLicenseMetric().equals(LicenseMetric.RAM)) {
                        quantity = ram;
                    } else if (models.getModel(swReleaseID).getLicenseMetric().equals(LicenseMetric.VCPU)) {
                        quantity = vCPU;
                    } else {
                        quantity = -1;   // TODO: Handle this case
                    }

                    if (!slr.hasAvailableRights(quantity)) {
                        rightsAvailable = false;
                    }
                    statusMsg = statusMsg.concat("{  swReleaseID: " + swReleaseID
                            + "  qtyOwned: " + slr.getQuantityOwned()
                            + "  qtyInUse: " + slr.getQuantityReserved()
                            + "  category: " + models.getModel(swReleaseID).getSoftwareCategory().toString()
                            + "}");
                } else {
                    // !licenseRights.containsKey(slrKey)
                    if (models.getModel(swReleaseID).getSoftwareCategory().equals(SoftwareCategory.APPLICATION)) {
                        rightsAvailable = false;
                    }
                    statusMsg = statusMsg.concat("{  swReleaseID: " + swReleaseID
                            + "  qtyOwned: 0"
                            + "  category: " + models.getModel(swReleaseID).getSoftwareCategory().toString()
                            + "}");
                }
            }
            // All rights have been checked at this point.
            if (rightsAvailable) {
                statusMsg = " { App ID : " + appID + " ";
                for (String swReleaseID : swReleaseIDs) {
                    slrKey = appID + "-" + swReleaseID;
                    if (rights.containsKey(slrKey)) {
                        LicenseRight slr = rights.get(slrKey);
                    if (models.getModel(swReleaseID).getLicenseMetric().equals(LicenseMetric.INSTANCE)) {
                        quantity = instances;
                    } else if (models.getModel(swReleaseID).getLicenseMetric().equals(LicenseMetric.RAM)) {
                        quantity = ram;
                    } else if (models.getModel(swReleaseID).getLicenseMetric().equals(LicenseMetric.VCPU)) {
                        quantity = vCPU;
                    } else {
                        quantity = -1;   // TODO: Handle this case
                    }
                        if (models.getModel(swReleaseID).getSoftwareCategory().equals(SoftwareCategory.APPLICATION)) {
                            slr.reserveRights(quantity);
                        }
                        statusMsg = statusMsg.concat("{  swReleaseID: " + swReleaseID
                                + "  qtyOwned: " + slr.getQuantityOwned()
                                + "  qtyInUse: " + slr.getQuantityReserved()
                                + "  category: " + models.getModel(swReleaseID).getSoftwareCategory().toString()
                                + "}");
                    }
                }
                m = new StatusMessage(StatusMessage.SUCCESS, "Rights successfully assigned " + statusMsg);
            } else {
                // !rightsAvailable
                m = new StatusMessage(StatusMessage.FAILURE, "Rights are not available for all titles " + statusMsg);
            }
        } else {
            m = new StatusMessage(StatusMessage.FAILURE, "No manifest found for ImageID: " + imageID);
        }
        return m;
    }

    public HashMap<String, LicenseRight> getSoftwareLicenseRights() {
        return rights;
    }

}
