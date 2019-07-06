package korba.crownzeh.scavenger.gameplay.enemy

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{Animation, Sprite, TextureRegion}
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d._
import com.badlogic.gdx.utils.Array
import korba.crownzeh.scavenger.assets.ImagePath
import korba.crownzeh.scavenger.assets.level.{City, Desert, Dungeon, LevelType}
import korba.crownzeh.scavenger.config.Properties

class Soldier(world: World, screen: Screen, bounds: Rectangle, levelType: LevelType) extends Enemy {
  val sprite: Sprite = prepareSprite()
  private var xVelocity: Float = 3
  private var destroyBody: Boolean = false
  private var destroyed: Boolean = false
  private var frameTimer: Float = 0
  private var walkPath: String = _
  private var deathPath: String = _
  private var tempRegion: TextureRegion = _
  private val body = defineEnemy()

  import ImagePath._
  levelType match {
    case City =>
      enemyGraphicPath(GREEN_SOLDIER, GREEN_SOLDIER_CORPSE)
      xVelocity = 3.0f
    case Desert =>
      enemyGraphicPath(YELLOW_SOLDIER, YELLOW_SOLDIER_CORPSE)
      xVelocity = 3.5f
    case Dungeon =>
      enemyGraphicPath(GREY_SOLDIER, GREY_SOLDIER_CORPSE)
      xVelocity = 4.0f
    case _ =>
      enemyGraphicPath(DOCTOR_SOLDIER, DOCTOR_SOLDIER_CORPSE)
      xVelocity = 4.5f
  }
  private val frames: Array[TextureRegion] = new Array[TextureRegion]
  for (i <- 0 until 4) {
    frames.add(new TextureRegion(new Texture(walkPath), i * 22, 0, 22, 40))
  }
  private val walk = new Animation[AnyRef](0.15f, frames)
  private val enemyDead: TextureRegion = new TextureRegion(new Texture(deathPath), 0, 0, 40, 20)


  override def defineEnemy(): Body = {
    val bodyDef = new BodyDef
    bodyDef.position.set(sprite.getX, sprite.getY)
    bodyDef.`type` = BodyDef.BodyType.DynamicBody
    val body = world.createBody(bodyDef)

    val fixtureDef = new FixtureDef
    fixtureDef.filter.categoryBits = Properties.ENEMY_BIT.toShort
    fixtureDef.filter.maskBits = {Properties.ASPECT_BIT | Properties.DEFAULT_BIT | Properties.ENEMY_BIT | Properties.INVISIBLE_WALL_BIT}.toShort

    val shape = new PolygonShape
    shape.setAsBox(10 / Properties.PIXELS_PER_METER, 20 / Properties.PIXELS_PER_METER)
    fixtureDef.shape = shape
    body.createFixture(fixtureDef).setUserData(this)

    val head = new PolygonShape
    fixtureDef.filter.categoryBits = Properties.ENEMY_HEAD_BIT.toShort
    fixtureDef.filter.maskBits = {Properties.ASPECT_BIT | Properties.ASPECT_FEET_BIT}.toShort
    val vertices: Array[Vector2] = new Array[Vector2]
    vertices.add(new Vector2(-11, 25).scl(1 / Properties.PIXELS_PER_METER))
    vertices.add(new Vector2(11, 25).scl(1 / Properties.PIXELS_PER_METER))
    vertices.add(new Vector2(-9, 19).scl(1 / Properties.PIXELS_PER_METER))
    vertices.add(new Vector2(9, 19).scl(1 / Properties.PIXELS_PER_METER))
    //head.set(vertices.toArray)
    fixtureDef.shape = head
    fixtureDef.restitution = 0.6f
    body.createFixture(fixtureDef).setUserData(this)
    body
  }

  override def die(): Unit = {
    destroyBody = true
  }

  override def prepareSprite(): Sprite = {
    val sprite: Sprite = new Sprite()
    import Properties._
    val x = bounds.getX / PIXELS_PER_METER
    val y = bounds.getY / PIXELS_PER_METER
    sprite.setPosition(x, y)
    sprite.setBounds(x, y, 22 / PIXELS_PER_METER, 40 / PIXELS_PER_METER)
    sprite
  }

  override def update(delta: Float): Unit = {
    frameTimer += delta
    if (destroyBody && !destroyed) {
      sprite.setBounds(sprite.getX, sprite.getY, 40 / Properties.PIXELS_PER_METER, 20 / Properties.PIXELS_PER_METER)
      //screen.getPlayer.setScore(screen.getPlayer.getScore + 150) TODO better score handling
      sprite.setRegion(enemyDead)
      world.destroyBody(this.body)
      destroyed = true
    }
    else if (!destroyed) {
      sprite.setPosition(body.getPosition.x - sprite.getWidth / 2, body.getPosition.y - sprite.getHeight / 2)
      tempRegion = walk.getKeyFrame(frameTimer, true).asInstanceOf[TextureRegion]
      sprite.setRegion(tempRegion)
      body.setLinearVelocity(new Vector2(xVelocity, body.getLinearVelocity.y))
    }
  }

  def flipVelocity(): Unit = {
    xVelocity = -xVelocity
    for (frame <- frames.toArray) {
      frame.flip(true, false)
    }
  }

  private def enemyGraphicPath(pathWalk: String, pathDead: String): Unit = {
    walkPath = pathWalk
    deathPath = pathDead
  }
}
