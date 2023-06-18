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
package de.openknowledge.workshop.serverless.oms.eventhandler;

import java.util.Arrays;
import java.util.Optional;

public enum EventHandlerType {

    MOCK("mock"),
    EVENT_BRIDGE("eventBridge");

    private String type;

    EventHandlerType(String status) {
        this.type = status;
    }

    public String getType() {
        return type;
    }

    // Reverse Lookup
    public static Optional<EventHandlerType> get(String type) {
        return Arrays.stream(EventHandlerType.values())
                .filter(env -> env.type.equals(type))
                .findFirst();
    }

}
