package korba.crownzeh.scavenger.gameplay.world.obstacle

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.World
import korba.crownzeh.scavenger.config.Properties

class Heart(world: World, tiledMap: TiledMap, rectangle: Rectangle) extends Collidable {

  private val tile = GenericTile(world, tiledMap, rectangle)
  tile.fixture.setUserData(this)
  tile.fixture.setSensor(true)
  tile.setCategoryFilter(Properties.HEART_BIT.toShort)

  override def collideWithPlayer(): Unit = {
    // TODO
  }
}
