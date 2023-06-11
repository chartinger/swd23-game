# How to map Exercise Goals

## Exercise 1

| Goal                                                          | Achieved Via                                                                                       |
|---------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| A factory exists and creates objects                          | `TileFactory` and `PlayerFactory`                                                                  |
| Tiles are created and placed on a higher level of abstraction | Tiles are created and maintained by the game board (`Board`) according to the `Game`s requirements |
| Separation of concerns is respected                           | All of our components follow the SRP                                                               |
| There is a player on the game board and the program compiles  | Yep. It sure does. |

## Exercise 2

| Goal                             | Achieved Via                                                                                                                                                                                              |
|----------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Singleton to load assets         | `AssetRepository`                                                                                                                                                                                         |
| Player moves via Command pattern | `GameCommand` and lambdas in `Main.setupKeybindings()`                                                                                                                                                    |
| An Observer exists               | Sure. In fact, multiple Observers exist - see next point.                                                                                                                                                 |
| Multiple Observers exists        | `MovementObserver`, `FloorObserver` and `BudgetObserver` are the interfaces. `ScoreBoard` implements all of them to display the HUD, and various lambdas in `Main.setupReporting()` provide console logs. | 

## Exercise 3

| Goal                                                       | Achieved Via                                                                                                                            |
|------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| More than one enemy                                        | The enemies are the threat strategies. There are multiple of them.                                                                      |
| Strategies for enemies                                     | There are multiple threat strategies (`RandomFloorDestroyer`, `AmplifiedEdgeDamage`, `NoDamage`)                                        |
| Strategy can change dynamically                            | User can choose strategies during runtime                                                                                               |
| Kill closest enemy                                         | `RepairBomb` will repair all broken tiles closest to you                                                                                |
| Display counter for eliminated enemies and players health  | Display remaining budget and number of repaired tiles                                                                                   |
| Bonus: some enemies need more than one key press           | Not implemented                                                                                                                         |
| Bonus: damage only enemies in the direction you are facing | `RepairGun` will restore tiles in any particular direction. However, in this game the player doesn't face in any particular directions. |
