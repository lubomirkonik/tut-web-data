package tut.webdata.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tut.webdata.core.services.MenuService;
import tut.webdata.events.menu.AllMenuItemsEvent;
import tut.webdata.events.menu.MenuItemDetails;
import tut.webdata.events.menu.RequestAllMenuItemsEvent;
import tut.webdata.web.domain.Basket;
import tut.webdata.web.domain.MenuItem;

@Controller
@RequestMapping("/")
public class SiteController {

	private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private Basket basket;
		
	@RequestMapping(method = RequestMethod.GET)
	
	public String getCurrentMenu(Model model) {
		LOG.debug("Yummy MenuItemDetails to home view");
		model.addAttribute("menuItems",getMenuItems(menuService.requestAllMenuItems(new RequestAllMenuItemsEvent())));
		return "/home";
	}
			
	private List<MenuItem> getMenuItems(AllMenuItemsEvent requestAllMenuItems) {
		List<MenuItem> menuDetails = new ArrayList<MenuItem>();
		
		for (MenuItemDetails menuItemDetails : requestAllMenuItems.getMenuItemDetails()) {
			menuDetails.add(MenuItem.fromMenuDetails(menuItemDetails));
		}

		return menuDetails;
	}
	
	@ModelAttribute("basket")
	private Basket getBasket() {
		return basket;
	}

}
