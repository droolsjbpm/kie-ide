/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.kie.smoke.wb.category.KieWbSeleniumSmoke;
import org.kie.smoke.wb.selenium.model.persps.HomePerspective;
import org.kie.smoke.wb.selenium.model.KieSeleniumTest;
import static org.junit.Assert.assertTrue;

@Category(KieWbSeleniumSmoke.class)
public class LoginIntegrationTest extends KieSeleniumTest {

    @Test
    public void loginAndLogout() {
        HomePerspective home = login.loginDefaultUser();
        assertTrue(home.isDisplayed());

        home.logout();
        assertTrue(login.isDisplayed());
    }
}
