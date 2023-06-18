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
package de.openknowledge.workshop.serverless.oms.repository;

import java.util.Arrays;
import java.util.Optional;

public enum OrderRepositoryType {

    MOCK("mock"),
    DYNAMODB("dynamoDb"),
    DYNAMODB_ENHANCED("dynamoDbEnhanced");

    private String type;

    OrderRepositoryType(String status) {
        this.type = status;
    }

    public String getType() {
        return type;
    }

    // Reverse Lookup
    public static Optional<OrderRepositoryType> get(String type) {
        return Arrays.stream(OrderRepositoryType.values())
                .filter(env -> env.type.equals(type))
                .findFirst();
    }

}
