package korba.crownzeh.scavenger.screens.info

import com.badlogic.gdx.scenes.scene2d.ui.{Label, Table}

class InfoTable {
  private val table = new Table
  table.center.top
  table.setFillParent(true)

  private[info] def getTable: Table = {
    this.table
  }

  private[info] def addLabelRow (descriptionLabel: Label, valueLabel: Label): Unit = {
    table.add(descriptionLabel).expandX.padTop(10)
    table.add(valueLabel).expandX.padTop(10)
    table.row
  }
}
