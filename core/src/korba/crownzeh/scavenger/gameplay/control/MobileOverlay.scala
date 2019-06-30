package korba.crownzeh.scavenger.gameplay.control

import com.badlogic.gdx.scenes.scene2d.Stage
import korba.crownzeh.scavenger.gameplay.player.PlayerAction

case class MobileOverlay(action: PlayerAction) {

  private val stage: Stage = new Stage()

  def getStage: Stage = {
    stage
  }

}
