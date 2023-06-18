/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package de.openknowledge.workshop.serverless.oms.lambda.read;

import de.openknowledge.workshop.serverless.oms.model.Order;

public class ReadOrderResponse {

    private String status;
    private String message;
    private Order order;

    public ReadOrderResponse() {
    }

    public ReadOrderResponse(Order order, String status, String message) {
        this.status = status;
        this.message = getMessage();
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message = message;}

    public static ReadOrderResponse emptyResponse() {
        return emptyResponse("", "");
    }

    public static ReadOrderResponse emptyResponse(String status, String message) {
        ReadOrderResponse response = new ReadOrderResponse();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}
