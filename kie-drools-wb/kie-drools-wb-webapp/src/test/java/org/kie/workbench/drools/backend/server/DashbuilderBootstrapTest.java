/*
 * Copyright 2015 JBoss by Red Hat.
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
package org.kie.workbench.drools.backend.server;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.dashbuilder.dataset.def.DataSetDef;
import org.dashbuilder.dataset.def.DataSetDefRegistry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class DashbuilderBootstrapTest {

    @Mock
    DataSetDefRegistry dataSetDefRegistryMock;

    DashbuilderBootstrap dashbuilderBootstrap;

    @Before
    public void setupMocks() {
        dashbuilderBootstrap = new DashbuilderBootstrap();
        dashbuilderBootstrap.setDataSetDefRegistry(dataSetDefRegistryMock);

    }

    @Test
    public void registerDataSetDefinitionsTest() {
        dashbuilderBootstrap.registerDataSetDefinitions();
        verify(dataSetDefRegistryMock,times(8)).registerDataSetDef(any(DataSetDef.class));
    }



}
