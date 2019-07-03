package korba.crownzeh.scavenger.gameplay.control

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.{Gdx, InputProcessor}
import korba.crownzeh.scavenger.gameplay.player.PlayerAction

case class KeyboardProcessor(action: PlayerAction) extends InputProcessor{
  override def keyDown(keycode: Int): Boolean = {
    keycode match {
      case Keys.LEFT => {Gdx.app.log("KeyDown", "Left")}
      case Keys.RIGHT => {Gdx.app.log("KeyDown", "Right")}
      case Keys.UP => {Gdx.app.log("KeyDown", "Up")}
      case Keys.DOWN => {Gdx.app.log("KeyDown", "Down")}
      case Keys.SPACE => {Gdx.app.log("KeyDown", "Space")}
      case _ => {}
    }
    false
  }

  override def keyUp(keycode: Int): Boolean =  {
    keycode match {
      case Keys.LEFT => {Gdx.app.log("KeyUp", "Left")}
      case Keys.RIGHT => {Gdx.app.log("KeyUp", "Right")}
      case Keys.UP => {Gdx.app.log("KeyUp", "Up")}
      case Keys.DOWN => {Gdx.app.log("KeyUp", "Down")}
      case Keys.SPACE => {Gdx.app.log("KeyUp", "Space")}
      case _ => {}
    }
    false
  }

  override def keyTyped(character: Char): Boolean =  {
    false
  }

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean =  {
    false
  }

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean =  {
    false
  }

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean =  {
    false
  }

  override def mouseMoved(screenX: Int, screenY: Int): Boolean =  {
    false
  }

  override def scrolled(amount: Int): Boolean =  {
    false
  }
}
