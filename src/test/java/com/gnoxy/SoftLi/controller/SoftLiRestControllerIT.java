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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gnoxy.SoftLi.am.StatusMessage;
import com.gnoxy.SoftLi.data.SoftLiAwsRequest;
import com.gnoxy.SoftLi.data.SoftLiStdRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftLiRestControllerIT {

    // @Autowired
    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    SoftLiRestController softLiController;

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
                .andExpect(jsonPath("*.licenseModel.id", hasItem(is("MB-1"))));
    }

    @Test
    public void testReserveandReleasesRights() throws Exception {
        System.out.println("\n\nIntegration Testing /reserveRights\n\n");
        mockMvc.perform(get("/reserveRights?appID=AB-2&imageID=IB-3&vCPUs=16&ram=256&instances=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.SUCCESS))
                .andExpect(jsonPath("elements.*.licenseRight.licenseModel.id", hasItem(is("MB-5"))));

        System.out.println("Integration Testing /releaseRights\n\n");
        mockMvc.perform(get("/releaseRights?appID=AB-2&imageID=IB-3&vCPUs=16&ram=256&instances=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.SUCCESS))
                .andExpect(jsonPath("elements.*.licenseRight.licenseModel.id", hasItem(is("MB-5"))));

    }

    @Test
    public void testReserveandReleasesRightsStdPost() throws Exception {
        String jsonRequest = null;
        SoftLiStdRequest softLiRequest = new SoftLiStdRequest("AB-2", "IB-3", "16", "256", "1");
        ObjectMapper objectToJsonMapper = new ObjectMapper();
        objectToJsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            jsonRequest = objectToJsonMapper.writeValueAsString(softLiRequest);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SoftLiRestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\n\nIntegration Testing /reserveRights/std Post\n\n");
        mockMvc.perform(post("/reserveRights/std").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.SUCCESS))
                .andExpect(jsonPath("elements.*.licenseRight.licenseModel.id", hasItem(is("MB-5"))));

        System.out.println("Integration Testing /releaseRights/std Post\n\n");
        mockMvc.perform(post("/releaseRights/std").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.SUCCESS))
                .andExpect(jsonPath("elements.*.licenseRight.licenseModel.id", hasItem(is("MB-5"))));

    }

    @Test
    public void testCheckRightsAvailable() throws Exception {
        System.out.println("\n\nIntegration Testing /checkRights Available\n\n");
        mockMvc.perform(get("/checkRights?appID=AB-2&imageID=IB-3&vCPUs=16&ram=256&instances=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.SUCCESS))
                .andExpect(jsonPath("elements.*.licenseRight.licenseModel.id", hasItem(is("MB-5"))));
    }

    @Test
    public void testCheckRightsNotAvailable() throws Exception {
        System.out.println("\n\nIntegration Testing /checkRights NotAvailable\n\n");
        mockMvc.perform(get("/checkRights?appID=AB-2&imageID=IB-3&vCPUs=160&ram=256&instances=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.RIGHTS_NOT_AVAILABLE));
    }

    @Test
    public void testCheckRightsNoImage() throws Exception {
        System.out.println("\n\nIntegration Testing /checkRights NoImage\n\n");
        mockMvc.perform(get("/checkRights?appID=AB-2&imageID=IB-3X&vCPUs=16&ram=256&instances=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.NO_IMAGE));
//                .andExpect(jsonPath("message").value("No image found for ImageID: IB-3X"));        
    }

    @Test
    public void testCheckRightsNoManifest() throws Exception {
        System.out.println("\n\nIntegration Testing /checkRights NoManifest\n\n");
        mockMvc.perform(get("/checkRights?appID=AB-2&imageID=IB-4&vCPUs=16&ram=256&instances=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.NO_MANIFEST));
//                .andExpect(jsonPath("message").value("No image found for ImageID: IB-3X"));        
    }

    @Test
    public void testCheckRightsAWS() throws Exception {
        System.out.println("\n\nIntegration Testing /checkRightsAWS\n\n");
        mockMvc.perform(get("/checkRightsAWS?appID=AB-2&imageID=IB-3&instanceType=t3.large&instances=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.SUCCESS))
                .andExpect(jsonPath("elements.*.licenseRight.licenseModel.id", hasItem(is("MB-5"))));
    }

    @Test
    public void testReserveandReleasesRightsAwsPost() throws Exception {
        String jsonRequest = null;
        SoftLiAwsRequest softLiRequest = new SoftLiAwsRequest("AB-2", "IB-3", "t3.large", "1");
        ObjectMapper objectToJsonMapper = new ObjectMapper();
        objectToJsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            jsonRequest = objectToJsonMapper.writeValueAsString(softLiRequest);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SoftLiRestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\n\nIntegration Testing /reserveRights/aws Post\n\n");
        mockMvc.perform(post("/reserveRights/aws").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.SUCCESS))
                .andExpect(jsonPath("elements.*.licenseRight.licenseModel.id", hasItem(is("MB-5"))));

        System.out.println("Integration Testing /releaseRights/aws Post\n\n");
        mockMvc.perform(post("/releaseRights/aws").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value(StatusMessage.SUCCESS))
                .andExpect(jsonPath("elements.*.licenseRight.licenseModel.id", hasItem(is("MB-5"))));

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
