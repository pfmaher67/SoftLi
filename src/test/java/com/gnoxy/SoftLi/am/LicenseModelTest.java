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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class LicenseModelTest {
    
    public LicenseModelTest() {
    }
    
    @Before
    public void setUp() {
    }

// This test was only used to test the dummy constructors of LicenseModel to ensure
// the int values could be converted to the proper Metric or Category class.
    
//    @Test
//    public void testGetID() {
//        System.out.println("getID");
//        LicenseModel instance = new LicenseModel();
//        String expResult = "";
//        String result = instance.getID();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testGetLicenseMetric() {
//        System.out.println("getLicenseMetric\n\n\n");
//        LicenseModel instance0 = new LicenseModel("Model0", 0);
//        LicenseMetric result0 = instance0.getLicenseMetric();
//        assertEquals(LicenseMetric.VCPU, result0);
//        LicenseModel instance = new LicenseModel("Model1", 1);
//        LicenseMetric result = instance.getLicenseMetric();
//        assertEquals(LicenseMetric.RAM, result);
//        LicenseModel instance2 = new LicenseModel("Model2", 2);
//        LicenseMetric result2 = instance2.getLicenseMetric();
//        assertEquals(LicenseMetric.INSTANCE, result2);
//        LicenseMetric result2a = instance2.getLicenseMetric();
//        assertEquals(LicenseMetric.INSTANCE, result2a);
//    }

//    @Test
//    public void testGetSoftwareCategory() {
//        System.out.println("getSoftwareCategory\n\n\n");
//        
//        LicenseModel instance0 = new LicenseModel("Model0", 0);
//        SoftwareCategory result0 = instance0.getSoftwareCategory();
//        assertEquals(SoftwareCategory.APPLICATION, result0);
//        
//        LicenseModel instance = new LicenseModel("Model1", 1);
//        SoftwareCategory result = instance.getSoftwareCategory();
//        assertEquals(SoftwareCategory.INFRASTRUCTURE, result);
//        
//        SoftwareCategory result2 = instance.getSoftwareCategory();
//        assertEquals(SoftwareCategory.INFRASTRUCTURE, result2);
//    }
    
}
