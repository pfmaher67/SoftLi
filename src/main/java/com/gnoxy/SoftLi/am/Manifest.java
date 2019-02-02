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
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@Entity
@Table(name = "Images")
public class Manifest {

    @Id
    @Column(name = "imageId")
    private String imageId;
    @Column(name = "platform")
    private String platform;
    @ElementCollection
    @Column(name = "swReleaseId")
    @CollectionTable(name="manifests", joinColumns= {@JoinColumn(name="image_id")})
    private Set<String> swReleaseIds;

    protected Manifest() {

    }
    
    public String getImageId() {
        return imageId;
    }

    public Manifest(String imageId, String swReleaseID) {
        this.imageId = imageId;
        this.platform = "undefined";
        if (swReleaseIds == null) {
            swReleaseIds = new HashSet<>();
        }
        swReleaseIds.add(swReleaseID);
    }

    public Manifest(String imageId, String platform, String swReleaseID) {
        this.imageId = imageId;
        this.platform = platform;
        if (swReleaseIds == null) {
            swReleaseIds = new HashSet<>();
        }
        swReleaseIds.add(swReleaseID);
    }

    public void addSwReleaseId(String swReleaseID) {
        swReleaseIds.add(swReleaseID);
    }

    public Set<String> getSwReleaseIds() {
        return swReleaseIds;
    }
    
    public void setSwReleaseIds(Set<String> swReleaseIds) {
        this.swReleaseIds = swReleaseIds;
    }

    @Override
    public String toString() {
        return String.format("Manifest[imageId=%s, platform=%s, swReleaseIds=%s]",
                imageId, platform, swReleaseIds.toString());
    }

}
