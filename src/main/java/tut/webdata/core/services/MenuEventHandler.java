package tut.webdata.core.services;

import tut.webdata.events.menu.*;
import tut.webdata.persistence.services.MenuPersistenceService;

public class MenuEventHandler implements MenuService {

  private MenuPersistenceService menuPersistenceService;

  public MenuEventHandler(MenuPersistenceService menuPersistenceService) {
    this.menuPersistenceService = menuPersistenceService;
  }

  @Override
  public AllMenuItemsEvent requestAllMenuItems(RequestAllMenuItemsEvent requestAllMenuItemsEvent) {
    return menuPersistenceService.requestAllMenuItems(requestAllMenuItemsEvent);
  }

  @Override
  public MenuItemDetailsEvent requestMenuItemDetails(RequestMenuItemDetailsEvent requestMenuItemDetailsEvent) {
    return menuPersistenceService.requestMenuItemDetails(requestMenuItemDetailsEvent);
  }

  @Override
  public MenuItemDetailsEvent createMenuItem(CreateMenuItemEvent createMenuItemEvent) {
    return menuPersistenceService.createMenuItem(createMenuItemEvent);
  }
}
