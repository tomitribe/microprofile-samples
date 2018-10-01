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
package org.tomitribe.microprofile.samples.fault.tolerance.fallback;

import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.net.URL;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertEquals;

public class MovieResourceTest extends Arquillian {
    @Inject
    private MovieResource movieResource;

    @Test
    public void testFindAll() throws Exception {
        assertEquals(Stream.of("The Terminator", "The Matrix", "Rambo").collect(toList()), movieResource.findAll());
        assertEquals(Stream.of("The Terminator", "The Matrix", "Rambo").collect(toList()), movieResource.findAll());
        assertEquals(Stream.of("The Terminator", "The Matrix", "Rambo").collect(toList()), movieResource.findAll());
    }

    @Test
    public void testFindAllResource(@ArquillianResource final URL base) throws Exception {
        final List<String> movies =
                ClientBuilder.newClient()
                             .target(base.toURI())
                             .path("movies")
                             .request()
                             .get(new GenericType<List<String>>() {});

        assertEquals(Stream.of("The Terminator", "The Matrix", "Rambo").collect(toList()), movies);
    }
}
