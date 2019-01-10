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
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class ManifestsTest {
    
    public Manifests manifests;
    
    public ManifestsTest() {
    }
    
    @Before
    public void setUp() {
        manifests = Initializer.getManifests();
    }


    @Test
    public void testGetSwReleaseIds() {
        System.out.println("getBinaryIds");
        Manifest manifest = manifests.getManifest("I-1");
        Set<String> binaryIDs = manifest.getSwReleaseIDs();
        assertEquals(true, binaryIDs.contains("50"));
        assertEquals(true, binaryIDs.contains("51"));
        assertEquals(true, binaryIDs.contains("53"));
        assertEquals(3, binaryIDs.size());

    }

    
}
