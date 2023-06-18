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

import de.openknowledge.workshop.serverless.oms.model.Order;

import java.util.Collections;
import java.util.List;

public class ReadMulitpleOrdersResponse {

    private String status;
    private String message;
    private int orderCount;
    private List<Order> orders;

    public ReadMulitpleOrdersResponse() {
    }

    public ReadMulitpleOrdersResponse(List<Order> orders, String status, String message) {
        this.status = status;
        this.message = message;
        this.orders = orders;
        if (orders != null) {
            this.orderCount = orders.size();
        } else {
            this.orderCount = 0;
        }
    }

    public int getOrderCount() {
        return this.orderCount;
    }

    public List<Order>  getOrders() {
        return orders;
    }
    public void setOrders(List<Order>  orders) {
        this.orders = orders;
        if ( this.orders != null) {
            this.orderCount =  this.orders.size();
        } else {
            this.orderCount = 0;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() { return message;}

    public void setMessage(String message) {this.message = message;}

    public static ReadMulitpleOrdersResponse emptyResponse() {
        return emptyResponse("", "");
    }

    public static ReadMulitpleOrdersResponse emptyResponse(String status, String message) {
        ReadMulitpleOrdersResponse response = new ReadMulitpleOrdersResponse();
        response.setStatus(status);
        response.setMessage(message);
        response.setOrders(Collections.emptyList());
        return response;
    }

}
