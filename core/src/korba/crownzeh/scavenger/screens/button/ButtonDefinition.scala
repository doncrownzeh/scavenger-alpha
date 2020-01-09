package korba.crownzeh.scavenger.screens.button

import com.badlogic.gdx.graphics.Texture

case class ButtonDefinition[A](texture: Texture, function: () => A) {

}
