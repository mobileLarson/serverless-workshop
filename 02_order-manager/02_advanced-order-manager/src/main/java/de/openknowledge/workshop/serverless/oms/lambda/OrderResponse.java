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
package de.openknowledge.workshop.serverless.oms.lambda;

import de.openknowledge.workshop.serverless.oms.lambda.create.CreateOrderResponse;
import de.openknowledge.workshop.serverless.oms.lambda.delete.DeleteOrderResponse;
import de.openknowledge.workshop.serverless.oms.lambda.read.ReadOrderResponse;
import de.openknowledge.workshop.serverless.oms.lambda.update.UpdateOrderResponse;
import de.openknowledge.workshop.serverless.oms.model.Order;


/**
 * Wrapper order response.
 */
public class OrderResponse {

    public final static String STATUS_OK = "OK";
    public final static String STATUS_ERROR = "ERROR";

    private String status;
    private String message;
    private Order order;

    public OrderResponse() {}


    public OrderResponse(ReadOrderResponse readOrderResponse) {
        this.order = readOrderResponse.getOrder();
        this.status = readOrderResponse.getStatus();
        this.message = readOrderResponse.getMessage();
    }

    public OrderResponse(CreateOrderResponse createOrderResponse) {
        this.status = createOrderResponse.getStatus();
        this.order = createOrderResponse.getOrder();
        this.message = createOrderResponse.getMessage();
    }

    public OrderResponse(UpdateOrderResponse updateOrderResponse) {
        this.order = updateOrderResponse.getOrder();
        this.status = updateOrderResponse.getStatus();
        this.message = updateOrderResponse.getMessage();
    }

    public OrderResponse(DeleteOrderResponse deleteOrderResponse) {
        this.status = deleteOrderResponse.getStatus();
        this.message = deleteOrderResponse.getMessage();
    }

    public String getMessage() {
        return message;
    }
    void setMessage(String message) { this.message = message; }

    public String getStatus() {
        return status;
    }
    void setStatus(String status) { this.status = status; }


    public Order getOrder() {
        return this.order;
    }

    public static OrderResponse emptyResponse() {
        return emptyResponse("", "");
    }

    public static OrderResponse emptyResponse(String status, String message) {
        OrderResponse response = new OrderResponse();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}
