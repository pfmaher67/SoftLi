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

import com.gnoxy.SoftLi.init.LicenseModelsInitializer;
import com.gnoxy.SoftLi.init.ManifestsInitializer;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManifestsTest {

    @Autowired
    private LicenseModelsInitializer lmi;

    @Autowired
    private ManifestsInitializer mi;
    
    public Manifests manifests;
    
    public ManifestsTest() {
    }
    
    @Before
    public void setUp() {
        LicenseModels models = new LicenseModels();
        models.init(lmi);
        manifests = new Manifests(models);
        manifests.init(mi);
    }


    @Test
    public void testGetSwReleaseIds() {
        System.out.println("\n\nTesting: getSwReleaseIds");
        Image manifest = manifests.getManifest("I-1");
        Set<String> binaryIDs = manifest.getSwReleaseIds();
        assertEquals(true, binaryIDs.contains("TM-1"));
        assertEquals(true, binaryIDs.contains("TM-2"));
        assertEquals(true, binaryIDs.contains("TM-4"));
        assertEquals(3, binaryIDs.size());

    }

    
}
