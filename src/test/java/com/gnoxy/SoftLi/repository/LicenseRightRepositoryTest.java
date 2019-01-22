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

import com.gnoxy.SoftLi.repository.LicenseRightRepository;
import com.gnoxy.SoftLi.SoftLiApplication;
import com.gnoxy.SoftLi.am.DBLicenseRight;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */

//@AutoConfigurationPackage
//@SpringBootConfiguration
//@EntityScan("com.gnoxy.SoftLi.am")
//@ContextConfiguration(classes= LicenseRightRepository.class)
//@SpringBootTest(classes = SoftLiApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class LicenseRightRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    
    @Autowired
    LicenseRightRepository licenseRightRepository;
    
    @Test
    public void testWriteAndReadLicenseRightRepo() {
        System.out.println("\n\nhere 0\n\n");
        this.entityManager.persist(new DBLicenseRight("App0", "SW-1", 24 ));
        
        System.out.println("\n\nhere 1\n\n");
        DBLicenseRight licenseRight = licenseRightRepository
                .save(new DBLicenseRight("App-1","SW-1", 16));
        System.out.println("\n\nhere 2\n\n");
//        DBLicenseRight foundRight = licenseRightRepository.getOne(licenseRight.getId());
        DBLicenseRight foundRight = licenseRightRepository.getOne(licenseRight.getId());
        System.out.println("\n\nhere 3\n\n");
        
        assertNotNull(foundRight);
        System.out.println("Found Right: " + foundRight.getAppId() + "/" + foundRight.getSwReleaseId()
            + ": " + foundRight.getQuantityOwned());
        
    }
    
}
