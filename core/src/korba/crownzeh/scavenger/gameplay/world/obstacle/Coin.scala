package korba.crownzeh.scavenger.gameplay.world.obstacle

import box2dLight.{PointLight, RayHandler}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.{Body, World}
import korba.crownzeh.scavenger.config.Properties
import korba.crownzeh.scavenger.gameplay.world.LightSource

class Coin(world: World, tiledMap: TiledMap, rectangle: Rectangle, rayHandler: RayHandler) extends Collidable with LightSource{

  private val tile = GenericTile(world, tiledMap, rectangle)
  tile.fixture.setUserData(this)
  tile.fixture.setSensor(true)
  tile.setCategoryFilter(Properties.COIN_BIT.toShort)
  setLightUp(tile.body, rayHandler)

  override def collideWithPlayer(): Unit = {
    // TODO
  }

  override def setLightUp(body: Body, rayHandler: RayHandler): Unit = {
    val pointLight = new PointLight(rayHandler, 200)
    pointLight.attachToBody(tile.body)
    val color: Color = Color.GOLD
    color.a = 0.8f
    pointLight.setColor(color)
    pointLight.setDistance(2)
    pointLight.setContactFilter(0.toShort, 4.toShort, 4.toShort) //random values
  }
}
