package korba.crownzeh.scavenger.gameplay.enemy

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.Body

trait Enemy {

  val sprite: Sprite

  def defineEnemy(): Body

  def die()

  def prepareSprite(): Sprite

  def update(delta: Float)
}
