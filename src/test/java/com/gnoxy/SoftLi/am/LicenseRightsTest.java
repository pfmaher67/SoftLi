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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class LicenseRightsTest {

    LicenseRights licenseRights;
    

    public LicenseRightsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        licenseRights = Initializer.getLicenseRights();
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testReserveRightsSuccess() {
        System.out.println("reserveRights: Success");
        StatusMessage result = licenseRights.reserveRights("2", "I-3", 8, 512, 1);
        assertEquals(0, result.getStatus());
    }
    
    @Test
    public void testReserveRightsFail() {
        System.out.println("reserveRights: Fail");
        StatusMessage result = licenseRights.reserveRights("2", "I-3", 24, 512, 1);
        assertEquals(1, result.getStatus());

    }
    
    @Test
    public void testGetSoftwareLicenseRights() {
        System.out.println("getSoftwareLicenseRights");
        HashMap<String, LicenseRight> expResult = null;
        HashMap<String, LicenseRight> result = licenseRights.getSoftwareLicenseRights();
        assertEquals(4, result.size());
        System.out.println("Rights:\n" + result);
    }

}
