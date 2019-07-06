package korba.crownzeh.scavenger.gameplay.enemy.soldier

import box2dLight.RayHandler
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.{Color, Texture}
import com.badlogic.gdx.graphics.g2d.{Animation, Sprite, TextureRegion}
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d._
import com.badlogic.gdx.utils.Array
import korba.crownzeh.scavenger.assets.ImagePath
import korba.crownzeh.scavenger.assets.level.{City, Desert, Dungeon, LevelType}
import korba.crownzeh.scavenger.config.Properties
import korba.crownzeh.scavenger.gameplay.enemy.{Direction, Enemy, Left, Right}
import korba.crownzeh.scavenger.gameplay.lights.BodyLight

class Soldier(world: World, screen: Screen, bounds: Rectangle, levelType: LevelType, rayHandler: RayHandler) extends Enemy {
  val sprite: Sprite = prepareSprite()
  private val body = defineEnemy()
  private val (walkPath, deathPath, xVelocity) = getSoldierSettingsForType
  private val frames: Array[TextureRegion] = loadTextures
  private val walk = new Animation[AnyRef](0.15f, frames)
  private val enemyDead: TextureRegion = new TextureRegion(new Texture(deathPath), 0, 0, 40, 20)
  private var destroyBody: Boolean = false
  private var destroyed: Boolean = false
  private var frameTimer: Float = 0
  private var direction: Direction = Right


  override def defineEnemy(): Body = {
    val soldierPhysics = SoldierPhysics(world, bounds)
    val body = soldierPhysics.create()
    setLightUp(body, rayHandler)
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
      sprite.setRegion(walk.getKeyFrame(frameTimer, true).asInstanceOf[TextureRegion])
      body.setLinearVelocity(new Vector2(getVelocity, body.getLinearVelocity.y))
    }
  }

  def flipEnemy(): Unit = {
    direction = if (direction == Right) Left else Right
    for (frame <- frames.toArray) {
      frame.flip(true, false)
    }
  }

  private def getVelocity: Float = {
    direction match {
      case Right => xVelocity
      case Left => -xVelocity
      case _ => 0f
    }
  }

  private def getSoldierSettingsForType: (String, String, Float) = {
    import ImagePath._
    levelType match {
      case City => (GREEN_SOLDIER, GREEN_SOLDIER_CORPSE, 3.0f)
      case Desert => (YELLOW_SOLDIER, YELLOW_SOLDIER_CORPSE, 3.5f)
      case Dungeon => (GREY_SOLDIER, GREY_SOLDIER_CORPSE, 4.0f)
      case _ => (DOCTOR_SOLDIER, DOCTOR_SOLDIER_CORPSE, 4.5f)
    }
  }

  private def loadTextures: Array[TextureRegion] = {
    val frames = new Array[TextureRegion]
    for (i <- 0 until 4) {
      frames.add(new TextureRegion(new Texture(walkPath), i * 22, 0, 22, 40))
    }
    frames
  }

  override def setLightUp(body: Body, rayHandler: RayHandler): Unit = {
    val light = new BodyLight(rayHandler, 200, Color.BLACK, 0.8f, 4);
    light.attachToBody(body)
  }
}
