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
import com.gnoxy.SoftLi.am.LicenseRight;
import com.gnoxy.SoftLi.am.SoftwareCategory;
import java.util.Collection;
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
public class LicenseRightRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    
    @Autowired
    LicenseRightRepository licenseRightRepository;
    
    @Test
    public void testWriteAndRead() {
        System.out.println("\n\nLicenseRightRepositoryTest: testWriteandRead()\n\n");
        LicenseRight licenseRight = new LicenseRight("A-4", 
                new LicenseModel("TM-4", new LicenseMetric(LicenseMetric.VCPU), 
                        new SoftwareCategory(SoftwareCategory.APPLICATION)) , 24 );
        this.entityManager.persist(licenseRight);
        
        LicenseRight foundRight = licenseRightRepository.getOne(licenseRight.getId());
        assertNotNull(foundRight);
        System.out.println("Found Right: " + foundRight.toString());
        
//        List<LicenseRight> rights = licenseRightRepository.findAll();
//        rights.forEach((r) -> {
//            System.out.println(r.toString());
//        });

        LicenseRight foundRight2 = licenseRightRepository.getOne("AB-2-MB-1");
        assertNotNull(foundRight2);
        System.out.println("Found Right2: " + foundRight2.toString());
        
        Collection<LicenseRight> rights = licenseRightRepository.findLicenseRightsByAppId("AB-2");
        assertNotNull(rights);
        rights.forEach((r) -> {
            System.out.println(r.toString());
        });        
    }
    
}
