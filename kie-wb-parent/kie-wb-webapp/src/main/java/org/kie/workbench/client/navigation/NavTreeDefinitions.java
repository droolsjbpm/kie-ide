/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.workbench.client.navigation;

import javax.enterprise.context.ApplicationScoped;

import org.dashbuilder.navigation.NavTree;
import org.dashbuilder.navigation.impl.NavTreeBuilder;
import org.kie.workbench.client.resources.i18n.NavigationConstants;

import static org.dashbuilder.navigation.workbench.NavWorkbenchCtx.perspective;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.APPS;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.BUSINESS_DASHBOARDS;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.DEPLOYMENTS;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.EXECUTION_ERRORS;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.JOBS;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.LIBRARY;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.PROCESS_DASHBOARD;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.PROCESS_DEFINITIONS;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.PROCESS_INSTANCES;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.SERVER_MANAGEMENT;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.TASKS;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.TASKS_ADMIN;

/**
 * Navigation tree definitions such as the workbench menu bar
 */
@ApplicationScoped
public class NavTreeDefinitions {

    public static final String GROUP_ROOT = "root";
    public static final String GROUP_WORKBENCH = "wb_group";

    public static final String GROUP_DESIGN = "wb_group_design";
    public static final String ENTRY_PROJECTS = "wb_entry_projects";
    public static final String ENTRY_DASHBOARDS = "wb_entry_dashboards";

    public static final String GROUP_DEVOPS = "wb_group_devops";
    public static final String ENTRY_DEPLOYMENTS = "wb_entry_deployments";
    public static final String ENTRY_EXECUTION_SERVERS = "wb_entry_execution_servers";

    public static final String GROUP_MANAGE = "wb_group_manage";
    public static final String ENTRY_PROCESS_DEFINITIONS = "wb_entry_process_definitions";
    public static final String ENTRY_PROCESS_INSTANCES = "wb_entry_process_instances";
    public static final String ENTRY_ADMINISTRATION_TASKS = "wb_entry_administration_tasks";
    public static final String ENTRY_JOBS = "wb_entry_jobs";
    public static final String ENTRY_EXECUTION_ERRORS = "wb_execution_errors";

    public static final String GROUP_TRACK = "wb_group_track";
    public static final String ENTRY_TASKS_LIST = "wb_entry_tasks_list";
    public static final String ENTRY_PROCESSES_AND_TASKS_DASHBOARD = "wb_entry_processes_and_tasks_dashboard";
    public static final String ENTRY_BUSINESS_DASHBOARDS = "wb_entry_business_dashboards";

    private NavigationConstants i18n = NavigationConstants.INSTANCE;

    public NavTree buildDefaultNavTree() {
        NavTreeBuilder builder = new NavTreeBuilder()
                .group(GROUP_ROOT,
                       i18n.navTreeRootName(),
                       i18n.navTreeRootDescr(),
                       false)
                .group(GROUP_WORKBENCH,
                       i18n.navTreeWorkbenchName(),
                       i18n.navTreeWorkbenchDescr(),
                       false);

        return builder
                .group(GROUP_DESIGN,
                       i18n.navTreeDesignName(),
                       i18n.navTreeDesignDescr(),
                       true)
                .item(ENTRY_PROJECTS,
                      i18n.navTreeProjectsName(),
                      i18n.navTreeProjectsDescr(),
                      true,
                      perspective(LIBRARY))
                .item(ENTRY_DASHBOARDS,
                      i18n.navTreeDashboardsName(),
                      i18n.navTreeDashboardsDescr(),
                      true,
                      perspective(BUSINESS_DASHBOARDS))
                .endGroup()
                .group(GROUP_DEVOPS,
                       i18n.navTreeDevOpsName(),
                       i18n.navTreeDevOpsDescr(),
                       true)
                .item(ENTRY_DEPLOYMENTS,
                      i18n.navTreeDeploymentsName(),
                      i18n.navTreeDeploymentsDescr(),
                      true,
                      perspective(DEPLOYMENTS))
                .item(ENTRY_EXECUTION_SERVERS,
                      i18n.navTreeExecutionServersName(),
                      i18n.navTreeExecutionServersDescr(),
                      true,
                      perspective(SERVER_MANAGEMENT))
                .endGroup()
                .group(GROUP_MANAGE,
                       i18n.navTreeManageName(),
                       i18n.navTreeManageDescr(),
                       true)
                .item(ENTRY_PROCESS_DEFINITIONS,
                      i18n.navTreeProcessDefinitionsName(),
                      i18n.navTreeProcessDefinitionsDescr(),
                      true,
                      perspective(PROCESS_DEFINITIONS))
                .item(ENTRY_PROCESS_INSTANCES,
                      i18n.navTreeProcessInstancesName(),
                      i18n.navTreeProcessInstancesDescr(),
                      true,
                      perspective(PROCESS_INSTANCES))
                .item(ENTRY_ADMINISTRATION_TASKS,
                      i18n.navTreeTasksAdministrationName(),
                      i18n.navTreeTasksAdministrationDescr(),
                      true,
                      perspective(TASKS_ADMIN))
                .item(ENTRY_JOBS,
                      i18n.navTreeJobsName(),
                      i18n.navTreeJobsDescr(),
                      true,
                      perspective(JOBS))
                .item(ENTRY_EXECUTION_ERRORS,
                      i18n.navTreeExecutionErrorsName(),
                      i18n.navTreeExecutionErrorsDescr(),
                      true,
                      perspective(EXECUTION_ERRORS))
                .endGroup()
                .group(GROUP_TRACK,
                       i18n.navTreeTrackName(),
                       i18n.navTreeTrackDescr(),
                       true)
                .item(ENTRY_TASKS_LIST,
                      i18n.navTreeTasksListName(),
                      i18n.navTreeTasksListDescr(),
                      true,
                      perspective(TASKS))
                .item(ENTRY_PROCESSES_AND_TASKS_DASHBOARD,
                      i18n.navTreeProcessesAndTasksDashboardName(),
                      i18n.navTreeProcessesAndTasksDashboardDescr(),
                      true,
                      perspective(PROCESS_DASHBOARD))
                .item(ENTRY_BUSINESS_DASHBOARDS,
                      i18n.navTreeBusinessDashboardsName(),
                      i18n.navTreeBusinessDashboardsDescr(),
                      true,
                      perspective(APPS))
                .endGroup()
                .endGroup()
                .endGroup()
                .build();
    }
}
