package korba.crownzeh.scavenger.screens.menu

import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import com.badlogic.gdx.scenes.scene2d.ui.Image
import korba.crownzeh.scavenger.assets.SoundTrackManager

object Action {
  implicit class MenuButtonAction(button: Image) {
    def addOnClick[A](function: => A): Image = {
      button.addListener(new InputListener() {
        override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
          SoundTrackManager.buttonSound.play()
          function
          true
        }
      })
      button
    }
  }
}
