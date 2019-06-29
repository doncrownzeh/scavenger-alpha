package korba.crownzeh.scavenger.screens

import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.{Game, Screen}
import korba.crownzeh.scavenger.assets.ImagePath
import korba.crownzeh.scavenger.screens.button.{ButtonAction, ButtonCreator}

trait Backable {

  def createBackTable(game: Game, previousScreen: Screen): Table = {
    import ButtonAction._, ImagePath._
    val backButton = ButtonCreator.createButton(BACK_BUTTON, () => goBack(game, previousScreen))
    val navTable = new Table()
    navTable.bottom.left.pad(5, 5, 5, 5)
    navTable.add(backButton).size(backButton.getWidth, backButton.getHeight)
    navTable.setFillParent(true)
    navTable
  }

}
