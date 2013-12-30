package tut.webdata.persistence.repository;

import tut.webdata.persistence.domain.MenuItem;

public interface MenuItemRepositoryWebapp {

  MenuItem save(MenuItem item);

  void delete(String key);

  MenuItem findById(String key);

  Iterable<MenuItem> findAll();
}
