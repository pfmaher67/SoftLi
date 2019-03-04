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
package com.gnoxy.SoftLi.aws;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@Entity
@Table(name = "EC2InstanceType")
@Proxy(lazy=false)
public class EC2InstanceType {

    @Id
    @Column(name = "instanceType")
    private String type;
    @Column(name = "vcpu")
    private long vCPUs;
    @Column(name = "memory")
    private float memory;
    
    public EC2InstanceType() {
        
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getvCPUs() {
        return vCPUs;
    }

    public void setvCPUs(long vCPUs) {
        this.vCPUs = vCPUs;
    }

    public float getMemory() {
        return memory;
    }

    public void setMemory(float memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return String.format("EC2InstanceType[type=%s, vCPUs=%d, memory=%.1f]",
                type, vCPUs, memory);
    }    
}
