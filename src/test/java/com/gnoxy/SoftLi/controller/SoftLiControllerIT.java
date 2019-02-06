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
package com.gnoxy.SoftLi.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftLiControllerIT {

    // @Autowired
    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    SoftLiController softLiController;
    
//    @Autowired
//    TestEntityManager entityManager;    

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.softLiController).build();// Standalone context

        // mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        // .build();
    }

    @Test
    public void testListRights() throws Exception {
        System.out.println("\n\nIntegration Testing /listRights\n\n");
        mockMvc.perform(get("/listRights").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("*.licenseModelId", hasItem(is("MB-1"))));
    }

    
    @Test
    public void testReserveandReleasesRights() throws Exception {
        System.out.println("\n\nIntegration Testing /reserveRights\n\n");
        mockMvc.perform(get("/reserveRights?appID=AB-2&imageID=IB-3&vCPUs=16&ram=256&instances=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("elements.*.licenseRight.licenseModelId", hasItem(is("MB-5"))));

        System.out.println("Integration Testing /releaseRights\n\n");
        mockMvc.perform(get("/reserveRights?appID=AB-2&imageID=IB-3&vCPUs=16&ram=256&instances=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("elements.*.licenseRight.licenseModelId", hasItem(is("MB-5"))));
        
    }

    
//    @Test
//    public void testListRights2() throws Exception {
//        System.out.println("\n\nIntegration Testing /listRights2\n\n");
//        MvcResult m = mockMvc.perform(get("/addRight?appID=A-Test&swReleaseID=S-Test&quantity=19")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//        m.getRequest().getContentAsString();
//        System.out.println("My IT Result: " + m.getRequest().getContentAsString());
//        MvcResult m2 = mockMvc.perform(get("/listRights2").contentType(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)).andReturn();
//        System.out.println("My IT Result2: " + m2.getRequest().getContentAsString());
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("*.swReleaseID", hasItem(is("TM-5"))));
//    }
////    @Test
//    public void testSearchASync() throws Exception {
//        MvcResult result = mockMvc.perform(get("").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(request().asyncStarted())
//                .andDo(print())
//                .andReturn();
//
//        mockMvc.perform(asyncDispatch(result))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.*.title", hasItem(is(""))));
//
//    }
}
