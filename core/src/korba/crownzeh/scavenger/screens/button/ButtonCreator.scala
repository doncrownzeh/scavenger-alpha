package korba.crownzeh.scavenger.screens.button

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}
import korba.crownzeh.scavenger.assets.SoundTrackManager

object ButtonCreator {

  private[screens] def createButton(imagePath: String, function: () => Any): Image = {
    val button = new Image(new Texture(imagePath))
    button.setSize(button.getWidth * 2, button.getHeight * 2)
    button.addListener(new InputListener() {
      override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
        SoundTrackManager.buttonSound.play()
        function()
        super.touchDown(event, x, y, pointer, button)
      }
    })
    button
  }

}
