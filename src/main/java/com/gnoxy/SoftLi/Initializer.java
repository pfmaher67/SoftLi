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


package com.gnoxy.SoftLi;

import com.gnoxy.SoftLi.am.LicenseRights;
import com.gnoxy.SoftLi.am.LicenseModel;
import com.gnoxy.SoftLi.am.LicenseModels;
import com.gnoxy.SoftLi.am.LicenseMetric;
import com.gnoxy.SoftLi.am.Manifests;
import com.gnoxy.SoftLi.am.SoftwareCategory;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class Initializer {

//  Applications
//  1
//  2
//  3
// Software
// 50 vCPU (WAS ND)
// 51 Instance (RHEL)
// 52 Instance (Mongo)
// 53 Instance (Qualys)
// 54 vCPU (Kafka)
// Images  *App Licensed*
// I-1  51, 53, *50*
// I-2  51, 53, 52
// I-3  51, 53, *50*, *54*
    public static LicenseModels getLicenseModels() {
        LicenseModels l = new LicenseModels();
        l.addModel(new LicenseModel(
                "50", 
                LicenseMetric.VCPU, 
                SoftwareCategory.APPLICATION));
        l.addModel(new LicenseModel(
                "51", 
                LicenseMetric.INSTANCE, 
                SoftwareCategory.INFRASTRUCTURE));
        l.addModel(new LicenseModel(
                "52", 
                LicenseMetric.INSTANCE, 
                SoftwareCategory.APPLICATION));
        l.addModel(new LicenseModel(
                "53", 
                LicenseMetric.INSTANCE, 
                SoftwareCategory.INFRASTRUCTURE));
        l.addModel(new LicenseModel(
                "54", 
                LicenseMetric.VCPU, 
                SoftwareCategory.APPLICATION));
        return l;
    }
        
    public static Manifests getManifests() {
        Manifests manifests = new Manifests(getLicenseModels());
        manifests.addSwReleaseID("I-1", "50");
        manifests.addSwReleaseID("I-1", "51");
        manifests.addSwReleaseID("I-1", "53");
        manifests.addSwReleaseID("I-2", "51");
        manifests.addSwReleaseID("I-2", "52");
        manifests.addSwReleaseID("I-2", "53");
        manifests.addSwReleaseID("I-3", "50");
        manifests.addSwReleaseID("I-3", "51");
        manifests.addSwReleaseID("I-3", "53");
        manifests.addSwReleaseID("I-3", "54");
        return manifests;
    }
// Rights
// 1    50: 32  
// 2    50: 16      54: 16
// 3    52:  3

    public static LicenseRights getLicenseRights() {
        LicenseRights sli = new LicenseRights();
        sli.addRight("1", "50", 32);
        sli.addRight("2", "50", 16);
        sli.addRight("2", "54", 16);
        sli.addRight("3", "52", 3);
        return sli;
    }

}
