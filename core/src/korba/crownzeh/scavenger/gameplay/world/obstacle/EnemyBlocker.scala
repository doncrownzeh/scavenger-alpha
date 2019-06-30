package korba.crownzeh.scavenger.gameplay.world.obstacle

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.World
import korba.crownzeh.scavenger.config.Properties

class EnemyBlocker(world: World, tiledMap: TiledMap, rectangle: Rectangle) {

  val tile = GenericTile(world, tiledMap, rectangle)
  tile.setCategoryFilter(Properties.INVISIBLE_WALL_BIT.toShort)

}
