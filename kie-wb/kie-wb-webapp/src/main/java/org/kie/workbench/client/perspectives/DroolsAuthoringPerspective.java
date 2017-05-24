/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
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
package org.kie.workbench.client.perspectives;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.guvnor.inbox.client.InboxPresenter;
import org.kie.workbench.client.docks.AuthoringWorkbenchDocks;
import org.kie.workbench.client.resources.i18n.AppConstants;
import org.kie.workbench.common.screens.projecteditor.client.menu.ProjectMenu;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.client.handlers.NewResourcePresenter;
import org.kie.workbench.common.widgets.client.handlers.NewResourcesMenu;
import org.kie.workbench.common.widgets.client.menu.RepositoryMenu;
import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.panels.impl.MultiListWorkbenchPanelPresenter;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;
import org.uberfire.workbench.model.menu.MenuFactory;
import org.uberfire.workbench.model.menu.MenuPosition;
import org.uberfire.workbench.model.menu.Menus;

@ApplicationScoped
@WorkbenchPerspective(identifier = "AuthoringPerspective", isTransient = false)
public class DroolsAuthoringPerspective {

    private AppConstants constants = AppConstants.INSTANCE;

    @Inject
    private NewResourcePresenter newResourcePresenter;

    @Inject
    private NewResourcesMenu newResourcesMenu;

    @Inject
    private ProjectMenu projectMenu;

    @Inject
    private PlaceManager placeManager;

    @Inject
    private RepositoryMenu repositoryMenu;

    @Inject
    private AuthoringWorkbenchDocks docks;

    @PostConstruct
    public void setup() {
        docks.setup("AuthoringPerspective",
                    new DefaultPlaceRequest("org.kie.guvnor.explorer"));
    }

    @Perspective
    public PerspectiveDefinition getPerspective() {
        PerspectiveDefinitionImpl perspective = new PerspectiveDefinitionImpl(MultiListWorkbenchPanelPresenter.class.getName());
        perspective.setName(constants.Project_Authoring());

        return perspective;
    }

    @WorkbenchMenu
    public Menus getMenus() {
        return getFirstItem()
                .newTopLevelMenu(AppConstants.INSTANCE.Repository())
                .withItems(repositoryMenu.getMenuItems())
                .endMenu()
                .newTopLevelMenu(constants.assetSearch()).position(MenuPosition.RIGHT).respondsWith(new Command() {
                    @Override
                    public void execute() {
                        placeManager.goTo("FindForm");
                    }
                })
                .endMenu()
                .newTopLevelMenu(constants.Messages()).position(MenuPosition.RIGHT).respondsWith(new Command() {
                    @Override
                    public void execute() {
                        placeManager.goTo("org.kie.workbench.common.screens.messageconsole.MessageConsole");
                    }
                })
                .endMenu()
                .build();
    }

    private MenuFactory.TopLevelMenusBuilder<MenuFactory.MenuBuilder> getFirstItem() {

        if (ApplicationPreferences.getBooleanPref("org.guvnor.inbox.disabled")) {
            return MenuFactory
                    .newTopLevelMenu(constants.newItem())
                    .withItems(newResourcesMenu.getMenuItems())
                    .endMenu();
        } else {
            return MenuFactory
                    .newTopLevelMenu(constants.explore())
                    .menus()
                    .menu(constants.inboxIncomingChanges())
                    .respondsWith(new Command() {
                        @Override
                        public void execute() {
                            placeManager.goTo("Inbox");
                        }
                    })
                    .endMenu()
                    .menu(constants.inboxRecentlyEdited())
                    .respondsWith(new Command() {
                        @Override
                        public void execute() {
                            PlaceRequest p = new DefaultPlaceRequest("Inbox");
                            p.addParameter("inboxname",
                                           InboxPresenter.RECENT_EDITED_ID);
                            placeManager.goTo(p);
                        }
                    })
                    .endMenu()
                    .menu(constants.inboxRecentlyOpened())
                    .respondsWith(new Command() {
                        @Override
                        public void execute() {
                            PlaceRequest p = new DefaultPlaceRequest("Inbox");
                            p.addParameter("inboxname",
                                           InboxPresenter.RECENT_VIEWED_ID);
                            placeManager.goTo(p);
                        }
                    })
                    .endMenu()
                    .endMenus()
                    .endMenu().newTopLevelMenu(constants.newItem())
                    .withItems(newResourcesMenu.getMenuItems())
                    .endMenu();
        }
    }
}
