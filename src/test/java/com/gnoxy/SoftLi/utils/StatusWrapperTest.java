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
package com.gnoxy.SoftLi.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class StatusWrapperTest {

    public StatusWrapperTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testGetMsg() {
        System.out.println("getMsg");
        StatusWrapper instance = null;
        instance = new StatusWrapper("Test");
        instance.AddMessage(new StatusMsg("Key1", "Val1"));
        instance.AddMessage(new StatusMsg("Key2", "Val2"));
        instance.AddMessage(new StatusMsg("Key3", "Val3"));

        ObjectMapper o = new ObjectMapper();
        try {
            String jsonInString = o.writerWithDefaultPrettyPrinter().writeValueAsString(instance);
            System.out.println("Status Message: " + jsonInString);
//            logger.info("Employee JSON is\n" + jsonInString);
//            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//            objectMapper.writeValue(new File(emp.getId() + "_employee.json"), emp);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        HashSet<StatusMsg> expResult = null;
//        HashSet<StatusMsg> result = instance.getMessages();
//        assertEquals(expResult, result);
    }

}
