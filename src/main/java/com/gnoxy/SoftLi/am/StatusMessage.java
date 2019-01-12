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

import java.util.ArrayList;

/**
 *
 * @author Patrick Maher<dev@gnoxy.com>
 */
public class StatusMessage {

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;

    private int status;
    private String message;
    private ArrayList<StatusMessageElement> elements;

    public StatusMessage() {
        status = FAILURE;
        message = "";
        elements = new ArrayList<>();
    }

    public StatusMessage(int status, String message) {
        this.status = status;
        this.message = message;
        elements = new ArrayList<>();
    }

    public StatusMessage(int status, String message, StatusMessageElement element) {
        this.status = status;
        this.message = message;
        if (elements == null) {
            elements = new ArrayList<>();
        }
        elements.add(element);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setElement(StatusMessageElement element) {
        elements.add(element);
    }

    public ArrayList<StatusMessageElement> getElements() {
        return elements;
    }

}
