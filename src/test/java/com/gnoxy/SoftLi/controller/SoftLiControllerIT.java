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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
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

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.softLiController).build();// Standalone context

        // mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        // .build();
    }

    @Test
    public void testListRights() throws Exception {
        System.out.println("Testing /listRights");
        mockMvc.perform(get("/listRights").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("*.swReleaseID", hasItem(is("54"))));
    }

//    @Test
//    public void testSearchASync() throws Exception {
//        MvcResult result = mockMvc.perform(get("/manga/async/ken").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(request().asyncStarted())
//                .andDo(print())
//                .andReturn();
//
//        mockMvc.perform(asyncDispatch(result))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.*.title", hasItem(is("Hokuto no Ken"))));
//
//    }
}
