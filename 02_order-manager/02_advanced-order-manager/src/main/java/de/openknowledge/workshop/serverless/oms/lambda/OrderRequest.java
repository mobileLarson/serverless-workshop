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

/**
 * Wrapper order request.
 */
public class OrderRequest {

    private String action;
    private Order order;

    public OrderRequest() {
        // empty by default
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() { return this.order; }
    public String getAction() { return this.action; }

}
