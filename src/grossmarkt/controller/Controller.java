package grossmarkt.controller;

import grossmarkt.maps.MapReference;

public interface Controller {

  /**
   * Every Controller needs an init-function.
   * @param reference global references to HashMaps
   */
  void init(MapReference reference);
}
