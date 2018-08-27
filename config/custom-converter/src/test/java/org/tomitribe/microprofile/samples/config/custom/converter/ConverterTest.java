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
package org.tomitribe.microprofile.samples.config.custom.converter;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.assertEquals;

public class ConverterTest extends Arquillian {
    @Inject
    private Config config;
    @Inject
    private ApplicationBean applicationBean;

    @Test
    public void testConfigSourceStatic() throws Exception {
        assertEquals(config.getValue("application.currency", Currency.class), Currency.EURO);
        assertEquals(config.getValue("application.country", Country.class), Country.PT);

        assertEquals(applicationBean.getApplicationCurrrency(), Currency.EURO);
        assertEquals(applicationBean.getApplicationCountry(), Country.PT);

        assertEquals(ConfigProvider.getConfig().getValue("application.currency", Currency.class), Currency.EURO);
        assertEquals(ConfigProvider.getConfig().getValue("application.country", Country.class), Country.PT);
    }
}
