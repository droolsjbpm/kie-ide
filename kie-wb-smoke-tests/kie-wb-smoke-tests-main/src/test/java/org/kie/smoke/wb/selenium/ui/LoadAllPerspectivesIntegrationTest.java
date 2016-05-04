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
package org.kie.smoke.wb.selenium.ui;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.kie.smoke.wb.category.KieWbSeleniumSmoke;
import org.kie.smoke.wb.selenium.model.KieSeleniumTest;
import org.kie.smoke.wb.selenium.model.Persp;
import org.kie.smoke.wb.selenium.model.persps.AbstractPerspective;
import org.kie.smoke.wb.selenium.model.persps.HomePerspective;

/**
 * Login and verify each perspective can be navigated to and loads some content.
 */
@Category(KieWbSeleniumSmoke.class)
public class LoadAllPerspectivesIntegrationTest extends KieSeleniumTest {

    @Test
    public void allPerspectivesCanBeLoaded() {
        HomePerspective home = login.loginDefaultUser();

        for (Persp<?> p : Persp.getAllPerspectives()) {
            if (perspectiveInCurrentWebApp(p)) {
                AbstractPerspective perspective = home.getNavbar().navigateTo(p);
                assertTrue("Perspective " + p.getName() + " should be loaded", perspective.isDisplayed());
            }
        }

        home.logout();
    }

    private boolean perspectiveInCurrentWebApp(Persp persp) {
        return IS_KIE_WB || !persp.isKieWbOnly();
    }
}
