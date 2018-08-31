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
package org.tomitribe.microprofile.samples.fault.tolerance.circuit.breaker;

import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class MovieResourceTest extends Arquillian {
    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap
                .create(WebArchive.class, "circuit-breaker.war")
                .addClass(MovieResource.class)
                .addClass(BusinessException.class)
                ;
    }

    @Inject
    private MovieResource movieResource;

    @Test(expectedExceptions = CircuitBreakerOpenException.class)
    public void testFindAll() throws Exception {
        for (int i = 1; i <= 3; i++) {
            try {
                movieResource.findAll();
                fail();
            } catch (final BusinessException e) {
                assertTrue(true);
            } catch (final Exception e) {
                fail();
            }
        }

        movieResource.findAll();
    }
}
