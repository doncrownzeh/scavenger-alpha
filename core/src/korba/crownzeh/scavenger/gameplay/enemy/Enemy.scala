package korba.crownzeh.scavenger.gameplay.enemy

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.Body
import korba.crownzeh.scavenger.gameplay.lights.LightSource

trait Enemy extends LightSource{

  val sprite: Sprite

  def defineEnemy(): Body

  def die()

  def flipEnemy()

  def prepareSprite(): Sprite

  def update(delta: Float)
}
