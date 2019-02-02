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

import com.gnoxy.SoftLi.init.ManifestsInitializer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class Manifests {
    private final HashMap<String, Manifest> manifests;
    LicenseModels lookup;
    
    public Manifests(LicenseModels lookup) {
        manifests = new HashMap<>();
        this.lookup = lookup;
    }
    
    public void init(ManifestsInitializer mi) {
        List<ManifestsInitializer.ManifestTemplate> l = mi.getManifestTemplates();
        Iterator<ManifestsInitializer.ManifestTemplate> i = l.iterator();
        while (i.hasNext()) {
            ManifestsInitializer.ManifestTemplate t = i.next();
            addSwReleaseID(t.getImageId(), t.getSwReleaseId());
            System.out.println("Manifest init(): Adding manifest: "
                    + t.getImageId() + " : " + t.getSwReleaseId());
        }        
    }
    
    public void addSwReleaseID(String imageID, String swReleaseID) {
        if (manifests.containsKey(imageID)) {
            manifests.get(imageID).addSwReleaseId(swReleaseID);
        } else {
            manifests.put(imageID, new Manifest(imageID, swReleaseID));
        }
    }
    
    public Manifest getManifest(String imageID) {
        return manifests.get(imageID);
    }
    
    public Set<String> getSwReleaseIds(String imageID) {
        return manifests.get(imageID).getSwReleaseIds();
    }
    
}
