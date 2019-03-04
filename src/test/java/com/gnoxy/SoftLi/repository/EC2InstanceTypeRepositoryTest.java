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

import com.gnoxy.SoftLi.aws.EC2InstanceType;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class EC2InstanceTypeRepositoryTest {
//    @Autowired
//    TestEntityManager entityManager;
    
    @Autowired
    EC2InstanceTypeRepository ec2InstanceTypeRepository;
    
    @Test
    public void testWriteAndRead() {
        System.out.println("\n\nEC2InstanceTypeRepositoryTest: testRead()\n\n");
//        Image image = new Image("TI-1", "TM-1");
//        image.addSwReleaseId("TM-2");
//        this.entityManager.persist(image);
//
//        Image foundImage = imageRepository.getOne(image.getImageId());
//        assertNotNull(foundImage);
//        System.out.println("Found Image: " + foundImage.toString());
        

        EC2InstanceType instanceType = ec2InstanceTypeRepository.getOne("m5.4xlarge");
        assertEquals(instanceType.getvCPUs(), 16);
        System.out.println("Found EC2InstanceType: " + instanceType.toString());
        
        
    }
    
}
