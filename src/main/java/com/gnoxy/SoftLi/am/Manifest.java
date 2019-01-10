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

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class Manifest {

    private final String imageID;
    private final Set<String> swReleaseIDs;

    public Manifest(String imageID, String swReleaseID) {
        this.imageID = imageID;
        swReleaseIDs = new HashSet<>();
        swReleaseIDs.add(swReleaseID);
    }

    public void addSwReleaseID(String swReleaseID) {
        swReleaseIDs.add(swReleaseID);
    }
    
    public Set<String> getSwReleaseIDs() {
        return swReleaseIDs;
    }

}
