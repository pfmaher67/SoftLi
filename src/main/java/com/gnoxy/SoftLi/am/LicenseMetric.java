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
/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */

public class LicenseMetric {
    
    public static final int VCPU = 0;
    public static final int RAM = 1;
    public static final int INSTANCE = 1;
    
    private int value;
    private String desc;
    
    public LicenseMetric() {
    }
    
    public LicenseMetric(int metric) {
        value = metric;
            switch (value) {
                case 0:
                    desc = "vCPU";
                    break;
                case 1:
                    desc = "RAM";
                    break;
                case 2:
                    desc = "Instance";
                    break;
                default:
                    desc = "Invalid Metric";
                    break;
            }        
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int metric) {
        value = metric;
            switch (value) {
                case 0:
                    desc = "vCPU";
                    break;
                case 1:
                    desc = "RAM";
                    break;
                case 2:
                    desc = "Instance";
                    break;
                default:
                    desc = "Invalid Metric";
                    break;
            }          
    }
    
    public String getDescription() {
        return desc;
    }
        
    public void setDescription(String description) {
        // Exists for the JsonToObject call
    }
        
}