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
        StatusMessageElement element = new StatusMessageElement("Software License Right created.", licenseRight);
        return new StatusMessage(StatusMessage.SUCCESS, "OK", element);
    }

    public StatusMessage reserveRights(String appID, String imageID,
            long vCPU, long ram, long instances) {
        StatusMessage statusMessage = new StatusMessage();
        String slrKey;
        long quantity;
        // From the image id, check the manifest and get all the Software Release IDs associated with the image
        Set<String> swReleaseIDs = manifests.getManifest(imageID).getSwReleaseIDs();
        if (!swReleaseIDs.isEmpty()) {
            boolean rightsAvailable = true;
            statusMessage.setMessage("App ID = " + appID);
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
                        statusMessage.setElement(new StatusMessageElement("Rights are not available for: ", slr));
                    } else {
                        statusMessage.setElement(new StatusMessageElement("Rights are available for: ", slr));
                    }
                } else {
                    // !licenseRights.containsKey(slrKey)
                    if (models.getModel(swReleaseID).getSoftwareCategory().equals(SoftwareCategory.APPLICATION)) {
                        rightsAvailable = false;
                        statusMessage.setElement(new StatusMessageElement("Application doesn't have license right for software, Release ID: "
                                + swReleaseID, null));
                    } else {
                        statusMessage.setElement(new StatusMessageElement("Non-application software, Release ID: "
                                + swReleaseID, null));
                    }
                }
            }
            // All rights have been checked at this point.
            if (rightsAvailable) {
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
                        statusMessage.setElement(new StatusMessageElement("Rights reserverd for: ", slr));
                    }
                }
                statusMessage.setStatus(StatusMessage.SUCCESS);
            } else {
                // !rightsAvailable
                statusMessage.setMessage("Rights are not available for all titles ");
            }
        } else {
            // (swReleaseIDs.isEmpty())
            statusMessage.setMessage("No manifest found for ImageID: " + imageID); //TO DO: Test this condition
        }
        return statusMessage;
    }

    public HashMap<String, LicenseRight> getSoftwareLicenseRights() {
        return rights;
    }

}
