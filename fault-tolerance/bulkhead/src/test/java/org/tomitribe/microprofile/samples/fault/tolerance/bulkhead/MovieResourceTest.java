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

import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class MovieResourceTest extends Arquillian {
    @Inject
    private MovieResource movieResource;

    @Test
    public void testFindAll() throws Exception {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(movieResource::findAll);
        executorService.submit(movieResource::findAll);
        executorService.submit(movieResource::findAll);

        executorService.submit(movieResource::findAll);
        executorService.submit(movieResource::findAll);
        executorService.submit(movieResource::findAll);

        TimeUnit.SECONDS.sleep(5);

        assertEquals(MovieResource.executions.get(), 3);
    }
}
