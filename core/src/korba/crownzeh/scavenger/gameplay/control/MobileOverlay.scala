package korba.crownzeh.scavenger.gameplay.control

import com.badlogic.gdx.scenes.scene2d.Stage

case class MobileOverlay(action: PlayerAction) {

  private val stage: Stage = new Stage()

  def getStage: Stage = {
    stage
  }

}
