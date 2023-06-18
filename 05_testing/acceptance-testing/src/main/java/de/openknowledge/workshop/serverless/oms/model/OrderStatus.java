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
package de.openknowledge.workshop.serverless.oms.model;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {

    UNKNOWN("unknown"),
    ORDERED("ordered"),            // by customer
    CONFIRMED("confirmed"),         // by system
    IN_PROGRESS("in progress"),     // by barista
    FULFILLED("fulfilled"),         // by barista
    CANCELED("canceled"),           // by customer or barista
    PICKED_UP("picked-up");         // by customer

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // Reverse Lookup
    public static Optional<OrderStatus> get(String status) {
        return Arrays.stream(OrderStatus.values())
                .filter(env -> env.status.equals(status))
                .findFirst();
    }


}
