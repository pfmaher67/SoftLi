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
package com.gnoxy.SoftLi.repository;

import com.gnoxy.SoftLi.am.LicenseMetric;
import com.gnoxy.SoftLi.am.LicenseModel;
import com.gnoxy.SoftLi.am.SoftwareCategory;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class LicenseModelRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    
    @Autowired
    LicenseModelRepository licenseModelRepository;
    
    @Test
    public void testWriteAndRead() {
        System.out.println("\n\nLicenseModelRepositoryTest: testWriteandRead()\n\n");
        LicenseModel licenseModel = new LicenseModel("TM-1", LicenseMetric.VCPU, SoftwareCategory.APPLICATION );
        this.entityManager.persist(licenseModel);
        
        LicenseModel foundModel = licenseModelRepository.getOne(licenseModel.getId());
        assertNotNull(foundModel);
        System.out.println("Found Model: " + foundModel.toString());
        
        List<LicenseModel> models = licenseModelRepository.findAll();
        models.forEach((m) -> {
            System.out.println(m.toString());
        });

        LicenseModel foundModel2 = licenseModelRepository.getOne("MB-1");
        assertNotNull(foundModel2);
        System.out.println("Found Right2: " + foundModel2.toString());
        
        
    }
    
}
