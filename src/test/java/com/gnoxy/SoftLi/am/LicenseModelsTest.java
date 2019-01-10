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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class LicenseModelsTest {

    private LicenseModels models;

    public LicenseModelsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        models = Initializer.getLicenseModels();
    }


    @Test
    public void testGetModel() {
        System.out.println("getModel");

        System.out.println("testModels");
        LicenseModel model = models.getModel("50");
        assertEquals("50", model.getID());
        assertEquals(LicenseMetric.VCPU, model.getLicenseMetric());
        assertEquals(SoftwareCategory.APPLICATION,
                model.getSoftwareCategory());

        model = models.getModel("51");
        assertEquals("51", model.getID());
        assertEquals(LicenseMetric.INSTANCE, model.getLicenseMetric());
        assertEquals(SoftwareCategory.INFRASTRUCTURE,
                model.getSoftwareCategory());
    }

}
