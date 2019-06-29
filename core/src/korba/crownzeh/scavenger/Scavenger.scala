package korba.crownzeh.scavenger

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import korba.crownzeh.scavenger.screens.menu.MenuScreen

class Scavenger extends Game {
  private[scavenger] var batch: SpriteBatch = _

  override def create() {
    batch = new SpriteBatch()
    setScreen(new MenuScreen(this, batch))
  }

  override def render(): Unit = {
    super.render()
  }
}
