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
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 *
 * Contains a list of License Models in play for
 */
public class LicenseModels {

    private final HashMap<String, LicenseModel> modelsMap;

    public LicenseModels() {
        modelsMap = new HashMap<>();
    }

    public void addModel(LicenseModel l) {
        String id = l.getId();
        if (!modelsMap.containsKey(id)) {
            modelsMap.put(id, l);
        }
    }

    public LicenseModel getModel(String id) {
        return modelsMap.get(id);
    }

//    public void init(LicenseModelsInitializer lmi) {
//        List<LicenseModelsInitializer.LicenseModelTemplate> l = lmi.getLicenseModelTemplates();
//        Iterator<LicenseModelsInitializer.LicenseModelTemplate> i = l.iterator();
//        while (i.hasNext()) {
//            LicenseModelsInitializer.LicenseModelTemplate t = i.next();
//            LicenseModel model = t.getModel();
//            System.out.println("LicenseModels init() " + model.getId()
//                    + " : " + model.getLicenseMetric().getDescription()
//                    + " : " + model.getSoftwareCategory().getDescription());
//            String id = model.getId();
//            if (!modelsMap.containsKey(id)) {
//                modelsMap.put(id, model);
//            }
//        }
//
//    }

}
