package korba.crownzeh.scavenger.gameplay.world

import C.SolidTile
import box2dLight.RayHandler
import com.badlogic.gdx.Game
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.physics.box2d.World
import korba.crownzeh.scavenger.assets.level.Level
import korba.crownzeh.scavenger.gameplay.enemy.Enemy
import korba.crownzeh.scavenger.gameplay.enemy.soldier.Soldier
import korba.crownzeh.scavenger.gameplay.world.obstacle.{Coin, EnemyBlocker, Heart, Spike}
import korba.crownzeh.scavenger.screens.GameScreen

class Box2dWorldLoader(game: Game, map: TiledMap, world: World, gameScreen: GameScreen, rayHandler: RayHandler, level: Level) {

  private val GROUND_LAYER = 2
  private val PLATFORM_LAYER = 3
  private val COIN_LAYER = 4
  private val HEART_LAYER = 5
  private val SPIKE_LAYER = 6
  private val ENEMY_LAYER = 7
  private val ENEMY_BLOCKER_LAYER = 8

  var enemies: Vector[Enemy] = Vector()

  def load(): Unit = {
    val layers: Vector[Int] = Vector(GROUND_LAYER, PLATFORM_LAYER, COIN_LAYER, HEART_LAYER, SPIKE_LAYER, ENEMY_LAYER, ENEMY_BLOCKER_LAYER)
    layers.foreach(loadForType)
  }

  private def loadForType(layer: Int): Unit = {
    for (tile <- map.getLayers.get(layer).getObjects.getByType(classOf[RectangleMapObject]).toArray) {
     layer match {
       case GROUND_LAYER => new SolidTile(world, map, tile.getRectangle)
       case PLATFORM_LAYER => new SolidTile(world, map, tile.getRectangle)
       case COIN_LAYER => new Coin(world, map, tile.getRectangle, rayHandler)
       case HEART_LAYER => new Heart(world, map, tile.getRectangle, rayHandler)
       case SPIKE_LAYER => new Spike(world, map, tile.getRectangle)
       case ENEMY_LAYER =>
         val bounds = tile.getRectangle
         enemies = enemies :+ new Soldier(world, gameScreen, bounds, level.levelType, rayHandler)
       case ENEMY_BLOCKER_LAYER => new EnemyBlocker(world, map, tile.getRectangle)
     }
    }
  }
}
