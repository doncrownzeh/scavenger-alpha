package korba.crownzeh.scavenger.gameplay.world.obstacle

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d._
import korba.crownzeh.scavenger.config.Properties

case class GenericTile(world: World, tiledMap: TiledMap, bounds: Rectangle) {

  val body: Body = createBody()
  val fixture: Fixture = createFixture()

  private[world] def setCategoryFilter(filterBit: Short): Unit = {
    val filter = new Filter
    filter.categoryBits = filterBit
    fixture.setFilterData(filter)
  }

  private def createBody(): Body = {
    val bodyDef = new BodyDef
    bodyDef.`type` = BodyDef.BodyType.StaticBody
    import Properties._
    bodyDef.position.set((bounds.getX + bounds.getWidth / 2) / PIXELS_PER_METER, (bounds.getY + bounds.getHeight / 2) / PIXELS_PER_METER)
    world.createBody(bodyDef)
  }

  private def createFixture(): Fixture = {
    val fixtureDef = new FixtureDef
    val polyShape = new PolygonShape
    import Properties._
    polyShape.setAsBox(bounds.getWidth / 2 / PIXELS_PER_METER, bounds.getHeight / 2 / PIXELS_PER_METER)
    fixtureDef.shape = polyShape
    body.createFixture(fixtureDef)
  }
}
