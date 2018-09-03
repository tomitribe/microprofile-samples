/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tomitribe.microprofile.samples.fault.tolerance.bulkhead;

import org.eclipse.microprofile.faulttolerance.Bulkhead;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
@Path("/movies")
public class MovieResource {
    static AtomicInteger executions = new AtomicInteger(0);

    @GET
    @Bulkhead(value = 3)
    public List<String> findAll() {
        final int execution = executions.addAndGet(1);

        try {
            System.out.println("Execution " + execution);
            TimeUnit.SECONDS.sleep(30);
        } catch (final InterruptedException e) {

        }

        System.out.println("Finished Execution " + execution);
        return Stream.of("The Terminator", "The Matrix", "Rambo").collect(Collectors.toList());
    }
}
