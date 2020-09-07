package korba.crownzeh.scavenger.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Table}
import com.badlogic.gdx.{Game, Screen}
import korba.crownzeh.scavenger.assets.ImagePath
import korba.crownzeh.scavenger.screens.button.ButtonAction
import korba.crownzeh.scavenger.screens.menu.Action._

trait Back {

  def createBackTable(game: Game, previousScreen: Screen): Table = {
    import ButtonAction._, ImagePath._
    val backButton = new Image(new Texture(BACK_BUTTON)).addOnClick(() => goBack(game, previousScreen))
    val navTable = new Table()
    navTable.bottom.left.pad(5, 5, 5, 5)
    navTable.add(backButton).size(backButton.getWidth, backButton.getHeight)
    navTable.setFillParent(true)
    navTable
  }
}
