package korba.crownzeh.scavenger.gameplay.world.obstacle

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d._
import korba.crownzeh.scavenger.config.Properties

class Spike(world: World, tiledMap: TiledMap, bounds: Rectangle) extends Collidable {

  val tile = GenericTile(world, tiledMap, bounds)
  tile.fixture.setUserData(this)
  tile.fixture.setSensor(true)
  tile.setCategoryFilter(Properties.SPIKE_BIT.toShort)

  override def collideWithPlayer(): Unit = {
    //TODO
  }
}
