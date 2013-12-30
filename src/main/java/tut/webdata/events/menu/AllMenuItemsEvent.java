package tut.webdata.events.menu;


import tut.webdata.events.ReadEvent;

import java.util.List;

public class AllMenuItemsEvent extends ReadEvent {
  private List<MenuItemDetails> menuItemDetails;

  public AllMenuItemsEvent(List<MenuItemDetails> menuItemDetails) {
    this.menuItemDetails = menuItemDetails;
  }

  public List<MenuItemDetails> getMenuItemDetails() {
    return menuItemDetails;
  }
}
