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

import java.util.HashMap;
import java.util.List;
import com.gnoxy.SoftLi.repository.ImageRepository;
import com.gnoxy.SoftLi.repository.LicenseRightRepository;
import java.util.Collection;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class LicenseRightsManager {

    private final ImageRepository imageRepository;
    private final LicenseRightRepository licenseRightRepository;

    public LicenseRightsManager(ImageRepository imageRepository,
            LicenseRightRepository licenseRightRepository) {
        this.imageRepository = imageRepository;
        this.licenseRightRepository = licenseRightRepository;
    }

    
    // TODO: Create one method to service both reserve and release rights
    public StatusMessage reserveRights(String appID, String imageID,
            long vCPU, long ram, long instances) {
        StatusMessage statusMessage = new StatusMessage();
        String rKey;
        long quantity;

        Image image = null;
        List<SoftwareRelease> swReleases = null;

        if (imageRepository.existsById(imageID)) {
            image = imageRepository.getOne(imageID);
            swReleases = image.getSoftwareReleases();
        }
        
        HashMap<String, LicenseRight> appRights
                = getLicenseRightsHash(licenseRightRepository.findLicenseRightsByAppId(appID));
        // TODO Check appRights == null and send error message to this effect

        if (swReleases != null && !swReleases.isEmpty()) {
            boolean rightsAvailable = true;
            statusMessage.setMessage("App ID = " + appID);

            // First check each swRelease to see if the App has enough rights to set
            for (SoftwareRelease swRelease : swReleases) {
                rKey = appID + "-" + swRelease.getLicenseModel().getId();
                if (appRights.containsKey(rKey)) {
                    LicenseRight lr = appRights.get(rKey);
                    if (swRelease.getLicenseModel().getLicenseMetric().equals(LicenseMetric.INSTANCE)) {
                        quantity = instances;
                    } else if (swRelease.getLicenseModel().getLicenseMetric().equals(LicenseMetric.RAM)) {
                        quantity = ram;
                    } else if (swRelease.getLicenseModel().getLicenseMetric().equals(LicenseMetric.VCPU)) {
                        quantity = vCPU;
                    } else {
                        quantity = -1;   // TODO: Handle this case
                    }

                    if (!lr.hasAvailableRights(quantity)) {
                        rightsAvailable = false;
                        statusMessage.setElement(new StatusMessageElement("Rights are not available for: " + swRelease.getId(), lr.copy()));
                    } else {
                        statusMessage.setElement(new StatusMessageElement("Rights are available for: " + swRelease.getId(), lr.copy()));
                    }
                } else {
                    // !licenseRights.containsKey(slrKey)
                    if (swRelease.getLicenseModel().getSoftwareCategory().equals(SoftwareCategory.APPLICATION)) {
                        rightsAvailable = false;
                        statusMessage.setElement(new StatusMessageElement("Application doesn't have license right for software, Release ID: "
                                + swRelease.getId(), null));
                    } else {
                        statusMessage.setElement(new StatusMessageElement("Non-application software, Release ID: "
                                + swRelease.getId(), null));
                    }
                }
            }
            // All rights have been checked at this point.
            if (rightsAvailable) {
                for (SoftwareRelease swRelease : swReleases) {
                    rKey = appID + "-" + swRelease.getLicenseModel().getId();
                    if (appRights.containsKey(rKey)) {
                        LicenseRight slr = appRights.get(rKey);
                        if (swRelease.getLicenseModel().getLicenseMetric().equals(LicenseMetric.INSTANCE)) {
                            quantity = instances;
                        } else if (swRelease.getLicenseModel().getLicenseMetric().equals(LicenseMetric.RAM)) {
                            quantity = ram;
                        } else if (swRelease.getLicenseModel().getLicenseMetric().equals(LicenseMetric.VCPU)) {
                            quantity = vCPU;
                        } else {
                            quantity = -1;   // TODO: Handle this case
                        }
                        if (swRelease.getLicenseModel().getSoftwareCategory().equals(SoftwareCategory.APPLICATION)) {
                            slr.reserveRights(quantity);
                            licenseRightRepository.save(slr);
                            statusMessage.setElement(new StatusMessageElement("Rights reserverd for: " + swRelease.getId(), slr));
                        }
                    }
                }
                statusMessage.setStatus(StatusMessage.SUCCESS);
                statusMessage.setMessage(statusMessage.getMessage().concat(". Rights successfully reserved"));
            } else {
                // !rightsAvailable
                statusMessage.setMessage("Rights are not available for all titles ");
            }
        } else {
            // (swReleases.isEmpty())
            statusMessage.setMessage("No manifest found for ImageID: " + imageID); //TO DO: Test this condition
        }
        return statusMessage;
    }

    public StatusMessage releaseRights(String appID, String imageID,
            long vCPU, long ram, long instances) {
        StatusMessage statusMessage = new StatusMessage();
        String rKey;
        long quantity;

        Image image = null;
        List<SoftwareRelease> swReleases = null;

        if (imageRepository.existsById(imageID)) {
            image = imageRepository.getOne(imageID);
            swReleases = image.getSoftwareReleases();
        }

        HashMap<String, LicenseRight> rights
                = getLicenseRightsHash(licenseRightRepository.findLicenseRightsByAppId(appID));

        if (swReleases != null && !swReleases.isEmpty()) {
            statusMessage.setMessage("App ID = " + appID);

            for (SoftwareRelease swRelease : swReleases) {
                rKey = appID + "-" + swRelease.getLicenseModel().getId();
                if (rights.containsKey(rKey)) {
                    LicenseRight lr = rights.get(rKey);
                    if (swRelease.getLicenseModel().getLicenseMetric().equals(LicenseMetric.INSTANCE)) {
                        quantity = instances;
                    } else if (swRelease.getLicenseModel().getLicenseMetric().equals(LicenseMetric.RAM)) {
                        quantity = ram;
                    } else if (swRelease.getLicenseModel().getLicenseMetric().equals(LicenseMetric.VCPU)) {
                        quantity = vCPU;
                    } else {
                        quantity = -1;   // TODO: Handle this case
                    }
                    if (swRelease.getLicenseModel().getSoftwareCategory().equals(SoftwareCategory.APPLICATION)) {
                        lr.releaseRights(quantity);
                        licenseRightRepository.save(lr);
                        statusMessage.setElement(new StatusMessageElement("Rights released for: ", lr));
                    }
                }
            }
            statusMessage.setStatus(StatusMessage.SUCCESS);
            statusMessage.setMessage(statusMessage.getMessage().concat(". Rights have been released"));
        } else {
            // (swReleases.isEmpty())
            statusMessage.setMessage("No manifest found for ImageID: " + imageID); //TO DO: Test this condition
        }
        return statusMessage;
    }

    private HashMap<String, LicenseRight> getLicenseRightsHash(Collection<LicenseRight> licenseRights) {
        HashMap<String, LicenseRight> lHash = new HashMap<>();
        licenseRights.forEach((l) -> {
            lHash.put(l.getId(), l);
        });
        return lHash;
    }

}
