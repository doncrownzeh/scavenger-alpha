package korba.crownzeh.scavenger.gameplay.world.obstacle

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d._
import korba.crownzeh.scavenger.config.Properties

case class GenericTile(world: World, tiledMap: TiledMap, bounds: Rectangle) {

  private val bodyDef = new BodyDef
  private val fixtureDef = new FixtureDef
  private val polyShape = new PolygonShape
  bodyDef.`type` = BodyDef.BodyType.StaticBody
  bodyDef.position.set((bounds.getX + bounds.getWidth / 2) / Properties.PIXELS_PER_METER, (bounds.getY + bounds.getHeight / 2) / Properties.PIXELS_PER_METER)
  private val body: Body = world.createBody(bodyDef)
  polyShape.setAsBox(bounds.getWidth / 2 / Properties.PIXELS_PER_METER, bounds.getHeight / 2 / Properties.PIXELS_PER_METER)
  fixtureDef.shape = polyShape
  val fixture: Fixture = body.createFixture(fixtureDef)

  def setCategoryFilter(filterBit: Short): Unit = {
    val filter = new Filter
    filter.categoryBits = filterBit
    fixture.setFilterData(filter)
  }
}
