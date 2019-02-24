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

import com.gnoxy.SoftLi.am.LicenseModel;
import com.gnoxy.SoftLi.am.SoftwareRelease;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
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
public class SoftwareReleaseRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    SoftwareReleaseRepository swReleaseRepository;

    @Test
    public void testWriteAndRead() {
        System.out.println("\n\nSoftwareReleaseRepository: testWriteandRead()\n\n");
        SoftwareRelease swRelease = new SoftwareRelease("SWR-1", "Release 1", "Version 1", "License ID 1");
        this.entityManager.persist(swRelease);

        SoftwareRelease foundSoftwareRelease = swReleaseRepository.getOne(swRelease.getId());
        assertNotNull(foundSoftwareRelease);
        System.out.println("Found swRelease: " + foundSoftwareRelease.toString());

        SoftwareRelease swR2 = swReleaseRepository.getOne("RB-4");
        assertEquals("RHEL 7", swR2.getName());
        System.out.println("Found swRelease2: " + swR2.toString());
        LicenseModel m = swR2.getLicenseModel();
        if (m == null) {
            System.out.println("No License Model Found.");
        } else {
            System.out.println("Found related LicenseModel: " + m.toString());
        }

    }

}
