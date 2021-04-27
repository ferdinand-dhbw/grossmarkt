package grossmarkt.controller;

import grossmarkt.maps.MapReference;

/**
 * Interface to define Controller
 *
 * @author Gruppe 2: Clara, Ferdinand, Florian, Jonas
 * @version 1.0
 * @since 27.04.2021
 */
public interface Controller {

  /**
   * Every Controller needs an init-function.
   *
   * @param reference global references to HashMaps
   */
  void init(MapReference reference);
}
