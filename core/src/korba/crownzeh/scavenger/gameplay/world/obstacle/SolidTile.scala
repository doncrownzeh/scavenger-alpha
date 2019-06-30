package C

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d._
import korba.crownzeh.scavenger.gameplay.world.obstacle.GenericTile

class SolidTile(world: World, tiledMap: TiledMap, bounds: Rectangle) {

  val tile: GenericTile = GenericTile(world, tiledMap, bounds)
  tile.fixture.setUserData("static")
}
