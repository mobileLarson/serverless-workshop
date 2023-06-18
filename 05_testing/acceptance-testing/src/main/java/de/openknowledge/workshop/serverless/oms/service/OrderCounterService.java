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
package de.openknowledge.workshop.serverless.oms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ThreadLocalRandom;


// see https://nickolasfisher.com/blog/Atomic-Incrementing-in-DynamoDB-with-the-Java-AWS-SDK-20
public class OrderCounterService {

    private static final Logger logger = LoggerFactory.getLogger(OrderCounterService.class);

    public static final Long next() {
        return ThreadLocalRandom.current().nextLong(100);
    }
}
