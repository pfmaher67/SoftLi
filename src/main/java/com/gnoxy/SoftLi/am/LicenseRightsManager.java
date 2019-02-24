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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@Service
public class LicenseRightsManager {

    @Autowired
    private ImageRepository imageRepository;
    
    @Autowired
    private LicenseRightRepository licenseRightRepository;

    public StatusMessage reserveRights(String appID, String imageID,
            long vCPU, long ram, long instances) {
        return manageRights(appID, imageID, vCPU, ram, instances, RightsAction.Reserve);
    }

    public StatusMessage releaseRights(String appID, String imageID,
            long vCPU, long ram, long instances) {
        return manageRights(appID, imageID, vCPU, ram, instances, RightsAction.Release);
    }

    private StatusMessage manageRights(String appID, String imageID,
            long vCPU, long ram, long instances, RightsAction action) {
        long quantity;
        LicenseRight right;

        StatusMessage statusMessage = checkRights(appID, imageID, vCPU, ram, instances);
        int status = statusMessage.getStatus();
        if (action == RightsAction.Reserve && status != StatusMessage.SUCCESS) {
            return statusMessage;
        }
        statusMessage.setStatus(StatusMessage.SUCCESS);

        for (StatusMessageElement element : statusMessage.getElements()) {
            right = element.getLicenseRight();
            if (right != null) {
                LicenseMetric metric = right.getLicenseModel().getLicenseMetric();
                if (metric.equals(LicenseMetric.INSTANCE)) {
                    quantity = instances;
                } else if (metric.equals(LicenseMetric.RAM)) {
                    quantity = ram;
                } else if (metric.equals(LicenseMetric.VCPU)) {
                    quantity = vCPU;
                } else {
                    quantity = -1;   // TODO: Handle this case
                }

                String s;
                if (action == RightsAction.Release) {
                    right.releaseRights(quantity);
                    // Because we're operating on the results of checkRights, it could be that rights are not
                    // available. But we are able to release them.
                    s = element.getMessage().replace("available", "released").replace("NOT ", "");
                } else {
                    right.reserveRights(quantity);
                    s = element.getMessage().replace("available", "reserved");
                }
                element.setMessage(s);
                licenseRightRepository.save(right);
            }
        }

        return statusMessage;
    }

    public StatusMessage checkRights(String appID, String imageID,
            long vCPU, long ram, long instances) {

        StatusMessage statusMessage = new StatusMessage();
        Image image;
        String rKey;
        long quantity;
        List<SoftwareRelease> swReleases;

        if (imageRepository.existsById(imageID)) {
            image = imageRepository.getOne(imageID);
            swReleases = image.getSoftwareReleases();
        } else {
            statusMessage.setMessage("No image found for ImageID: " + imageID); //TO DO: Test this condition
            statusMessage.setStatus(StatusMessage.NO_IMAGE);
            return statusMessage;
        }

        Collection<LicenseRight> licenseRights = licenseRightRepository.findLicenseRightsByAppId(appID);
        HashMap<String, LicenseRight> appRights = new HashMap<>();
        licenseRights.forEach((l) -> {
            appRights.put(l.getId(), l);
        });

        if (swReleases != null && !swReleases.isEmpty()) {
            boolean rightsAvailable = true;
            statusMessage.setMessage(String.format("LicenseRights for [appId=%s, imageId=%s]", appID, imageID));

            for (SoftwareRelease swRelease : swReleases) {
                // Check each software title on the image, but only check rights for Application software
                if (swRelease.getLicenseModel().getSoftwareCategory().equals(SoftwareCategory.INFRASTRUCTURE)) {
                    statusMessage.setElement(new StatusMessageElement("Non-application software: "
                            + swRelease.toString(), null));
                } else {
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
                            statusMessage.setStatus(StatusMessage.RIGHTS_NOT_AVAILABLE);
                            statusMessage.setElement(new StatusMessageElement(quantity + " Rights NOT available: " + swRelease.toString(), lr));

                        } else {
                            statusMessage.setElement(new StatusMessageElement(quantity + " Rights available: " + swRelease.toString(), lr));
                        }
                    } else {
                        // !licenseRights.containsKey(slrKey)
                        rightsAvailable = false;
                        statusMessage.setStatus(StatusMessage.RIGHTS_NOT_AVAILABLE);
                        statusMessage.setElement(new StatusMessageElement("No Rights: " + swRelease.toString(), null));
                    }
                }
            }
            // All rights have been checked at this point.
            if (rightsAvailable) {
                statusMessage.setStatus(StatusMessage.SUCCESS);
            }
        } else {
            // (swReleases.isEmpty())
            statusMessage.setMessage("No manifest found for ImageID: " + imageID);
            statusMessage.setStatus(StatusMessage.NO_MANIFEST);
        }
        return statusMessage;
    }

    private enum RightsAction {
        Reserve,
        Release
    }

}
