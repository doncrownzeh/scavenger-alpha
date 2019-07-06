package korba.crownzeh.scavenger.gameplay.world.obstacle

import box2dLight.RayHandler
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.{Body, World}
import korba.crownzeh.scavenger.config.Properties
import korba.crownzeh.scavenger.gameplay.lights.{BodyLight, LightSource}

class Heart(world: World, tiledMap: TiledMap, rectangle: Rectangle, rayHandler: RayHandler) extends Collidable with LightSource{

  private val tile = GenericTile(world, tiledMap, rectangle)
  tile.fixture.setUserData(this)
  tile.fixture.setSensor(true)
  tile.setCategoryFilter(Properties.HEART_BIT.toShort)
  setLightUp(tile.body, rayHandler)

  override def collideWithPlayer(): Unit = {
    // TODO
  }

  override def setLightUp(body: Body, rayHandler: RayHandler): Unit = {
    val light = new BodyLight(rayHandler, 200, Color.RED, 0.65f, 2);
    light.attachToBody(tile.body)
  }
}
