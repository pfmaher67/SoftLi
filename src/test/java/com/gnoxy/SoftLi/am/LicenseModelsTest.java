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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class LicenseModelsTest {

    private LicenseModels models;

    @Autowired
    private LicenseModelsInitializer lmi;

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
        models = new LicenseModels();
        System.out.println("Testing Models, Initializing LMI");
        if (lmi == null) {
            System.out.println("LMI is null");
        } else {
            System.out.println("LMI is good!");
            models.init(lmi);
        }
    }

    @Test
    public void testGetModel() {
        System.out.println("\n\n Testing getModel\n\n");


        LicenseModel model = models.getModel("TM-1");
        assertEquals("TM-1", model.getId());
        assertEquals(LicenseMetric.VCPU, model.getLicenseMetric());
        assertEquals(SoftwareCategory.APPLICATION,
                model.getSoftwareCategory());

        model = models.getModel("TM-2");
        assertEquals("TM-2", model.getId());
        assertEquals(LicenseMetric.INSTANCE, model.getLicenseMetric());
        assertEquals(SoftwareCategory.INFRASTRUCTURE,
                model.getSoftwareCategory());

        model = models.getModel("TM-3");
        assertEquals("TM-3", model.getId());
        assertEquals(LicenseMetric.INSTANCE, model.getLicenseMetric());
        assertEquals(SoftwareCategory.APPLICATION,
                model.getSoftwareCategory());
    }

}
