package tut.webdata.events.menu;


import tut.webdata.events.CreateEvent;

public class CreateMenuItemEvent extends CreateEvent {

  private MenuItemDetails details;

  public CreateMenuItemEvent(MenuItemDetails details) {
    this.details = details;
  }

  public MenuItemDetails getDetails() {
    return details;
  }
}
