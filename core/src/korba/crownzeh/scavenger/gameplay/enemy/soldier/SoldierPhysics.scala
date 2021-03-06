package korba.crownzeh.scavenger.gameplay.enemy.soldier

import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d._
import korba.crownzeh.scavenger.config.Properties

case class SoldierPhysics(world: World, bounds: Rectangle, soldier: Soldier) {

  def create(): Body = {
    val bodyDef = createBodyDefinition()
    val body = world.createBody(bodyDef)
    body.createFixture(createBodyHitBox()).setUserData(soldier)
    body.createFixture(createHeadHitBox()).setUserData(soldier)
    body
  }

  private def createBodyDefinition(): BodyDef = {
    val bodyDef = new BodyDef
    bodyDef.position.set(bounds.getX / Properties.PIXELS_PER_METER, bounds.getY / Properties.PIXELS_PER_METER)
    bodyDef.`type` = BodyDef.BodyType.DynamicBody
    bodyDef
  }

  private def createBodyHitBox(): FixtureDef = {
    val fixtureDef = new FixtureDef
    fixtureDef.filter.categoryBits = Properties.ENEMY_BIT.toShort
    fixtureDef.filter.maskBits = {
      Properties.ASPECT_BIT | Properties.DEFAULT_BIT | Properties.ENEMY_BIT | Properties.INVISIBLE_WALL_BIT
    }.toShort
    fixtureDef.shape = createBodyShape()
    fixtureDef
  }

  private def createBodyShape(): Shape = {
    val shape = new PolygonShape
    shape.setAsBox(10 / Properties.PIXELS_PER_METER, 20 / Properties.PIXELS_PER_METER)
    shape
  }

  private def createHeadHitBox(): FixtureDef = {
    val fixtureDef = new FixtureDef
    val head = createHeadShape()
    fixtureDef.filter.categoryBits = Properties.ENEMY_HEAD_BIT.toShort
    fixtureDef.filter.maskBits = {
      Properties.ASPECT_BIT | Properties.ASPECT_FEET_BIT
    }.toShort
    fixtureDef.shape = head
    fixtureDef.restitution = 0.6f
    fixtureDef
  }

  private def createHeadShape(): Shape = {
    val head = new PolygonShape
    val offsetVector = new Vector2(0, 20).scl(1 / Properties.PIXELS_PER_METER)
    head.setAsBox(9 / Properties.PIXELS_PER_METER, 1 / Properties.PIXELS_PER_METER, offsetVector, 0)
    head
  }
}
