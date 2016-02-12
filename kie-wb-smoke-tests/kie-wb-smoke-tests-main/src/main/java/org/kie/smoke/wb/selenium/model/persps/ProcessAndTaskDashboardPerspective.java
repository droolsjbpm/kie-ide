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
package org.kie.smoke.wb.selenium.model.persps;

import org.kie.smoke.wb.selenium.util.LoadingIndicator;
import org.kie.smoke.wb.selenium.util.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProcessAndTaskDashboardPerspective extends AbstractPerspective {

    private static final By PROCESSES_TAB = By.id("processes");

    public ProcessAndTaskDashboardPerspective(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return Waits.isElementPresent(driver, PROCESSES_TAB);
    }

    @Override
    public void waitForLoaded() {
        LoadingIndicator indicator = PageFactory.initElements(driver, LoadingIndicator.class);
        indicator.disappear("Loading dashboard");
    }
}
