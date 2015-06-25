/*
 * Copyright 2015 JBoss Inc
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

package org.kie.config.cli.command.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.guvnor.common.services.project.model.Project;
import org.guvnor.structure.organizationalunit.OrganizationalUnit;
import org.guvnor.structure.organizationalunit.OrganizationalUnitService;
import org.guvnor.structure.repositories.Repository;
import org.guvnor.structure.repositories.RepositoryService;
import org.jboss.weld.environment.se.WeldContainer;
import org.kie.config.cli.CliContext;
import org.kie.config.cli.command.CliCommand;
import org.kie.config.cli.support.InputReader;
import org.kie.workbench.common.screens.explorer.model.ProjectExplorerContent;
import org.kie.workbench.common.screens.explorer.service.ExplorerService;
import org.kie.workbench.common.screens.explorer.service.Option;
import org.kie.workbench.common.screens.explorer.service.ProjectExplorerContentQuery;
import org.kie.workbench.common.services.shared.project.KieProjectService;

public class AddGroupToProjectCliCommand implements CliCommand {

    @Override
    public String getName() {
        return "add-group-project";
    }

    @Override
    public String execute( CliContext context ) {
        StringBuffer result = new StringBuffer();
        WeldContainer container = context.getContainer();

        OrganizationalUnitService organizationalUnitService = container.instance().select( OrganizationalUnitService.class ).get();
        RepositoryService repositoryService = container.instance().select( RepositoryService.class ).get();
        ExplorerService projectExplorerService = container.instance().select( ExplorerService.class ).get();
        KieProjectService projectService = container.instance().select( KieProjectService.class ).get();

        InputReader input = context.getInput();
        System.out.print( ">>Repository alias:" );
        String alias = input.nextLine();

        Repository repo = repositoryService.getRepository( alias );
        if ( repo == null ) {
            return "No repository " + alias + " was found";
        }

        OrganizationalUnit ou = null;
        Collection<OrganizationalUnit> units = organizationalUnitService.getOrganizationalUnits();
        for ( OrganizationalUnit unit : units ) {
            if ( unit.getRepositories().contains( repo ) ) {
                ou = unit;
                break;
            }
        }
        if ( ou == null ) {
            return "Could not find Organizational Unit containing repository. Unable to proceed.";
        }

        ArrayList<Project> projects = new ArrayList<Project>();
        ProjectExplorerContentQuery query = new ProjectExplorerContentQuery( ou, repo );
        query.setOptions( new HashSet<Option>() );
        ProjectExplorerContent content = projectExplorerService.getContent( query );
        projects.addAll( content.getProjects() );
        if ( projects.size() == 0 ) {
            return "No projects found in repository " + alias;
        }

        int projectIndex = 0;
        while ( projectIndex == 0 ) {
            System.out.println( ">>Select project:" );
            for ( int i = 0; i < projects.size(); i++ ) {
                System.out.println( ( i + 1 ) + ") " + projects.get( i ).getProjectName() );
            }
            try {
                projectIndex = Integer.parseInt( input.nextLine() );
            } catch ( NumberFormatException e ) {
                System.out.println( "Invalid index" );
            }
            if ( projectIndex < 1 || projectIndex > projects.size() ) {
                projectIndex = 0;
                System.out.println( "Invalid index" );
            }
        }
        Project project = projects.get( projectIndex - 1 );

        System.out.print( ">>Security groups (comma separated list):" );
        String groupsIn = input.nextLine();
        if ( groupsIn.trim().length() > 0 ) {
            String[] groups = groupsIn.split( "," );
            for ( String group : groups ) {
                if (project.getGroups().contains(group)) {
                    continue;
                }
                projectService.addGroup( project, group );
                result.append( "Group " + group + " added successfully to project " + project.getProjectName() + "\n" );
            }
        }

        return result.toString();
    }

}
