package korba.crownzeh.scavenger.config


import com.badlogic.gdx.Application.ApplicationType
import com.badlogic.gdx.Gdx

object Properties {
  val VIRTUAL_WIDTH = 640
  val ACTUAL_WIDTH = 1280
  val VIRTUAL_HEIGHT = 360
  val ACTUAL_HEIGHT = 720
  val PIXELS_PER_METER = 20f
  val DEFAULT_BIT = 1
  val ASPECT_BIT = 2
  val COIN_BIT = 4
  val NONE_BIT = 8
  val HEART_BIT = 16
  val SPIKE_BIT = 32
  val ENEMY_BIT = 64
  val INVISIBLE_WALL_BIT = 128
  val ASPECT_FEET_BIT = 256
  val ENEMY_HEAD_BIT = 512
  val device: ApplicationType = Gdx.app.getType
  val devMode: Boolean = device.eq(ApplicationType.Desktop)
}
