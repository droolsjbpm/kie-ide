/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.smoke.wb.selenium.model;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.kie.smoke.wb.selenium.util.ScreenshotOnFailure;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
public class KieSeleniumTest {

    private static final Logger LOG = LoggerFactory.getLogger(KieSeleniumTest.class);

    @Drone
    protected static WebDriver driver;

    @Page
    protected LoginPage login;

    public static final boolean IS_KIE_WB = isKieWb();

    @Rule
    public ScreenshotOnFailure screenshotter = new ScreenshotOnFailure();

    private static boolean isKieWb() {
        String prop = System.getProperty("app.name");
        if (!("kie-wb".equals(prop) || "kie-drools-wb".equals(prop))) {
            throw new IllegalStateException("Invalid app.name='" + prop + "' Expecting kie-wb or kie-drools-wb");
        }
        LOG.info("Tested application: {}", prop);
        return "kie-wb".equals(prop);
    }
}
