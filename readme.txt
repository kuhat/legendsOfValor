Name
--------------------------------------------------------
Wenhao Zhou
U36389876


Files
---------------------------------------------------------
main.java: Entrance of the game
Game.java: Starter of the game
game/FactoryCreator.java: Base Factory interface for other factories
game/GameRunner.java: running instance of the game
game/GameState.java: GameState interface
game/Product.java: base product obj for each object
game/utils/fileReader.java: file reader utility class
game/utils/instructions.java: instructions for print hints
game/RPGGame/Board.java: Board instance of the game
game/RPGGame/Cell.java: Cell contained in the Board
game/RPGGame/InaccessibleCell.java: Inaccessible Cell instance which the heroes cannot enter
game/RPGGame/MarketCell.java: MarketCell instance where heroes can sell and buy items
game/RPGGame/normalCell.java: Normal Cell instance where battle against monsters may occur
game/RPGGame/RPGItem.java: Base item object which inherited by all the items
game/PRGGame/Round.java: Round class where heroes and Monsters take action
game/role/attackable.java: Base interface for objects that have attack behavior
game/role/Buyable.java: Base interface for objects that can buy other items
game/role/mountable.java: Base interface for objects that can be mounted to other roles
game/role/Prop.java: Instance of Common properties of heroes and monsters
game/role/role.java: Base class inherited by other roles: heroes and monsters
game/role/RoleFactory.java: Factory class that produces roles (heroes and monsters), which inherited by hero factory and monster factory
game/role/Sellable.java: Sell interface which implemented by all the items that can be sold
game/role/heroes/Hero.java: Hero class inherited by other kinds of heroes
game/role/heroes/HeroFactory.java: Factory that only produces Hero instances
game/role/heroes/Paladin.java: Paladin Hero Instance
game/role/heroes/Party.java: Hero team, the number of which is 1 to 3
game/role/heroes/Sorceror.java: Sorceror hero instance
game/role/heroes/Warrior.java: Warrior hero instance
game/role/item/Armor.java: Armor instance
game/role/item/fireSpell.java: fireSpell Instance
game/role/item/iceSpell.java: iceSPell Instance
game/role/item/Inventory.java: Each hero's inventory to store their items bought from the market
game/role/item/ItemFactory.java: Factory which is in charge of producing all the items
game/role/item/lightningSpell.java: lightning Spell instance
game/role/item/Potion.java: Potion instance
game/role/item/Spell.java: Spell instance inherited by fire, ice, lightning spells
game/role/item/SpellFactory.java: Spell factory which in charge of producing Spells
game/role/item/Weapon.java: Weapon instance
game/role/monsters/Dragon.java: Dragon instance
game/role/monsters/Exoskeleton.java: Exoskeleton Instance
game/role/monsters/Monster.java: Base monster class inherited by all the monsters
game/role/monsters/MonsterFactory.java: Factory creator which only incharge of producing monster objects
game/role/monsters/Spirit.java: Instance of spirits
game/role/places/Bush.java: Bush place which boosts role's dexterity
game/role/places/Cave.java: Cave place which boosts role's agility
game/role/places/Koulou/java: Koulou place which boosts role's strength
game/role/places/Map.java: Map class which inherits from Board, the place where the player play game
game/role/places/Nexus.java: Nexus place where heroes and Monsters spawn and heroes can buy things
game/role/places/Plain.java: Plain place which boosts nothing
-------------------------------------------------------------------------------------------------------------


Notes:
--------------------------------------------------------------------------------------------------------------
1. Factory Pattern: When producing new instances of Hero, Monsters, items, factory of the kind is used to create
objects
2. Files to be parsed should be stored in /configs, for parser class to read class

Coding platform: Windows

Bonus Points: Background Music, Colorful output, input parse from txt files, factory pattern


How to run:
---------------------------------------------------------------------------------------------------------------
1. Navigate to the directory after unzip
2. run the following instructions:

javac -d bin src/*.java src/game/*.java src/game/utils/*.java src/game/RPGGame/*.java src/game/role/*.java src/game/role/heroes/*.java src/game/role/item/*.java src/game/role/monsters/*.java src/game/role/places/*.java
java -cp bin main


Sample output
---------------------------------------------------------------------------------------------------------------

--------------------++++++++------------------
        Welcome to Monsters and Heroes!
--------------------++++++++------------------

 This is a terminal based RPG game, pay attention to the output of terminal!
You will be given a team of Heroes to fight with a variety of Monsters.
 Good Luck!
*++++++++++++++++++++++*++  Now Let's begin  +++++++++++++++++++*********
Please choose your hero on the lane 0
Please choose the type of Hero, Here is the choice of heroes:
1: Warriors: favored on strength and agility.
2: Sorcerers: favored on dexterity and agility.
3: Paladins: favored on strength and dexterity.

1
You chose Warrior!
Warrior: Muamman_Duathall entered lane 0!
Please choose your hero on the lane 1
Please choose the type of Hero, Here is the choice of heroes:
1: Warriors: favored on strength and agility.
2: Sorcerers: favored on dexterity and agility.
3: Paladins: favored on strength and dexterity.

2
You chose Sorcerer!
Sorcerer: Reverie_Ashels entered lane 1!
Please choose your hero on the lane 2
Please choose the type of Hero, Here is the choice of heroes:
1: Warriors: favored on strength and agility.
2: Sorcerers: favored on dexterity and agility.
3: Paladins: favored on strength and dexterity.

3
You chose Paladin!
Paladin: Parzival entered lane 2!
Monster BigBad-Wolf was spawned.
Monster Natsunomeryu was spawned.
Monster Blinky was spawned.
Your hero party of size 3 is ready to battle!
Here is your game map:
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Here are your Heroes information:
  ID                    Name     Level        HP      Mana       Strength        Agility      Dexterity          Money      Experience   Current lane
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   0        Muamman_Duathall         1       600       300            900            500            750           2546               6              0
   1          Reverie_Ashels         1       700       900            800            700            400           2500               7              1
   2                Parzival         1       700       300            750            650            700           2500               7              2
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
========Round 1 begins===========
Welcome to Nexus, do you want to buy or sell items?
 The hero's gold is 2546
You have chance to buy or sell items on Nexus for Hero: Muamman_Duathall
1: Buy
2: Sell
0: Quit
1
You chose to buy items
Please choose the kind of item to buy:
 1: Weapon
 2: Armor
 3: Potion
 4: Spell
1
Here are all the weapons that are on sale:
  ID                Name      costrequired level    damage      required hands
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   0               Sword       500         1       800                   1
   1                 Bow       300         2       500                   2
   2              Scythe      1000         6      1100                   2
   3                 Axe       550         5       850                   1
   4             TSwords      1400         8      1600                   2
   5              Dagger       200         1       250                   1
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Please choose which weapon to buy:
0
Transaction success! Current money: 2046
Hero's current money is 2046
You have chance to buy or sell items on Nexus for Hero: Muamman_Duathall
1: Buy
2: Sell
0: Quit
1
You chose to buy items
Please choose the kind of item to buy:
 1: Weapon
 2: Armor
 3: Potion
 4: Spell
2
Here is all the armors that are on sale:
  ID                Name      cost      required level    damage reduction
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   0     Platinum_Shield       150                   1                 200
   1         Breastplate       350                   3                 600
   2     Full_Body_Armor      1000                   8                1100
   3       Wizard_Shield      1200                  10                1500
   4      Guardian_Angel      1000                  10                1000
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
Please choose which armor to buy:
0
Transaction success! Current money: 1896
Hero's current money is 1896
You have chance to buy or sell items on Nexus for Hero: Muamman_Duathall
1: Buy
2: Sell
0: Quit
0
You chose to quit, good luck!
Please choose one action for hero Muamman_Duathall:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

3
Equipped Weapon is None
Equipped Armor is None
You can choose Armors and Weapons in the current inventory to equip.
 -0: Type: Weapon, Name: Sword, Damage: 800, required hands: 1
 -1: Type: Armor, Name: Platinum_Shield, Reduction: 200
Choose a Weapon or Armor to equip:
0
Hero successfully equipped Weapon: Sword
Welcome to Nexus, do you want to buy or sell items?
 The hero's gold is 1896
You have chance to buy or sell items on Nexus for Hero: Muamman_Duathall
1: Buy
2: Sell
0: Quit
0
You chose to quit, good luck!
Please choose one action for hero Muamman_Duathall:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|H1     |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Welcome to Nexus, do you want to buy or sell items?
 The hero's gold is 2500
You have chance to buy or sell items on Nexus for Hero: Reverie_Ashels
1: Buy
2: Sell
0: Quit
5
Please enter valid choice!
You have chance to buy or sell items on Nexus for Hero: Reverie_Ashels
1: Buy
2: Sell
0: Quit
1
You chose to buy items
Please choose the kind of item to buy:
 1: Weapon
 2: Armor
 3: Potion
 4: Spell
3
Here are all the weapons that are on sale:
  ID                Name      cost      required level  attribute increase                                     attribute affected
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   0      Healing_Potion       250                   1                 100                                                 Health
   1     Strength_Potion       200                   1                  75                                               Strength
   2        Magic_Potion       350                   2                 100                                                   Mana
   3         Luck_Elixir       500                   4                  65                                                Agility
   4       Mermaid_Tears       850                   5                 100                           Health/Mana/Strength/Agility
   5            Ambrosia      1000                   8                 150     All Health/Mana/Strength/Dexterity/Defense/Agility
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Please choose which potion to buy:
0
Transaction success! Current money: 2250
Hero's current money is 2250
You have chance to buy or sell items on Nexus for Hero: Reverie_Ashels
1: Buy
2: Sell
0: Quit
0
You chose to quit, good luck!
Please choose one action for hero Reverie_Ashels:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Koulou space and gained 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |H3     |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Welcome to Nexus, do you want to buy or sell items?
 The hero's gold is 2500
You have chance to buy or sell items on Nexus for Hero: Parzival
1: Buy
2: Sell
0: Quit
1
You chose to buy items
Please choose the kind of item to buy:
 1: Weapon
 2: Armor
 3: Potion
 4: Spell
4
Here is all the spells that are on sale:
  ID                Name      costrequired level    damage      mana cost
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
   0         Snow_Cannon       500         2       650            250
   1           Ice_Blade       250         1       450            100
   2      Frost_Blizzard       750         5       850            350
   3        Arctic_Storm       700         6       800            300
   4       Flame_Tornado       700         4       850            300
   5      Breath_of_Fire       350         1       450            100
   6           Heat_Wave       450         2       600            150
   7          Lava_Comet       800         7      1000            550
   8          Hell_Storm       600         3       950            600
   9       Flame_Tornado       700         4       850            300
  10      Breath_of_Fire       350         1       450            100
  11           Heat_Wave       450         2       600            150
  12          Lava_Comet       800         7      1000            550
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
Please choose which spell to buy:
1
Transaction success! Current money: 2250
Hero's current money is 2250
You have chance to buy or sell items on Nexus for Hero: Parzival
1: Buy
2: Sell
0: Quit
0
You chose to quit, good luck!
Please choose one action for hero Parzival:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Koulou space and gained 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
================= Heroes' tur end =================
================= Monster's turn begin =================
Monster BigBad-Wolf is taking action.
M1
No hero is within attack range, move forward
Press any key to proceed.

Monster Natsunomeryu is taking action.
M2
No hero is within attack range, move forward
Press any key to proceed.

Monster Blinky is taking action.
M3
No hero is within attack range, move forward
Press any key to proceed.

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
========Round 2 begins===========
Please choose one action for hero Muamman_Duathall:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Koulou space and gained 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|H1     |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Please choose one action for hero Reverie_Ashels:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Bush space and gained 10% of dexterity
Hero exited Koulou space and lost extra 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |H3     |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Please choose one action for hero Parzival:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Bush space and gained 10% of dexterity
Hero exited Koulou space and lost extra 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
================= Heroes' tur end =================
================= Monster's turn begin =================
Monster BigBad-Wolf is taking action.
M1
No hero is within attack range, move forward
Press any key to proceed.

Monster Natsunomeryu is taking action.
M2
No hero is within attack range, move forward
Press any key to proceed.

Monster Blinky is taking action.
M3
No hero is within attack range, move forward
Press any key to proceed.

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
========Round 3 begins===========
Please choose one action for hero Muamman_Duathall:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Bush space and gained 10% of dexterity
Hero exited Koulou space and lost extra 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|H1     |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Please choose one action for hero Reverie_Ashels:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero exited the Bush space and lost extra 10% of dexterity
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |H3     |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Please choose one action for hero Parzival:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Bush space and gained 10% of dexterity
Hero exited the Bush space and lost extra 10% of dexterity
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
================= Heroes' tur end =================
================= Monster's turn begin =================
Monster BigBad-Wolf is taking action.
M1
No hero is within attack range, move forward
Press any key to proceed.

Monster Natsunomeryu is taking action.
M2
No hero is within attack range, move forward
Press any key to proceed.

Monster Blinky is taking action.
M3
No hero is within attack range, move forward
Press any key to proceed.

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|H1     |  |       |  | X X X |  |H2     |  |       |  | X X X |  |H3     |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
========Round 4 begins===========
Please choose one action for hero Muamman_Duathall:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

6
Enter the lane number to teleport:
1. Top Lane
2. Mid Lane
3. Bot Lane
2
Your destination lane is: Middle Lane
Enter the row number to teleport(0-7):
4
Enter the column number to teleport(0-7):
4
Your destination lane is: Middle Lane
Your destination Row is: 4
Your destination Column is: 4
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |     M1|  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |H2     |  |H1     |  | X X X |  |H3     |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Please choose one action for hero Reverie_Ashels:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game


Please choose one number from 1 to 8!
Please choose one action for hero Reverie_Ashels:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

1
Natsunomeryu did not dodge this attack!
Reverie_Ashels caused 30 hp loss to Natsunomeryu
Natsunomeryu has HP: 70
Please choose one action for hero Parzival:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

1
Blinky dodged this attack.
================= Heroes' tur end =================
================= Monster's turn begin =================
Monster BigBad-Wolf is taking action.
M1
No hero is within attack range, move forward
Press any key to proceed.

Monster Natsunomeryu is taking action.
Reverie_Ashels did not dodge this attack!
Natsunomeryu caused 5 hp loss to Reverie_Ashels
Reverie_Ashels has HP: 695
Press any key to proceed.

Monster Blinky is taking action.
Parzival dodged this attack.
Press any key to proceed.

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |     M2|  | X X X |  |       |  |     M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |     M1|  | X X X |  |H2     |  |H1     |  | X X X |  |H3     |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
========Round 5 begins===========
Please choose one action for hero Muamman_Duathall:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

1
Natsunomeryu did not dodge this attack!
Muamman_Duathall caused 75 hp loss to Natsunomeryu
Natsunomeryu has HP: -5
Monster: Natsunomeryu has dead after the attack of Hero: Muamman_Duathall
Your hero has slayed a monster. Leveled up by one. Current level: 11
 - get experience: 20
 -  Get power points: 89, Current power points: 1785
 -  Get Agility Points: 80, Current Agility points: 1606
All the heroes got500 gold.
All the heroes got500 gold.
All the heroes got500 gold.
Please choose one action for hero Reverie_Ashels:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

7
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |     M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |     M1|  | X X X |  |       |  |H1     |  | X X X |  |H3     |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Welcome to Nexus, do you want to buy or sell items?
 The hero's gold is 2750
You have chance to buy or sell items on Nexus for Hero: Reverie_Ashels
1: Buy
2: Sell
0: Quit
2
You chose to sell items
Please choose the kind of item to sell:
 1: Weapon
 2: Armor
 3: Potion
 4: Spell
3
Here are all the weapons that are in the hero's inventory:
  ID                Name      cost      required level  attribute increase                                     attribute affected
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
   0      Healing_Potion       250                   1                 100                                                 Health
-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Please choose which potion to sell:
0
Transaction success! hero gains gold 125, Now the hero has: 2875
Hero's current money is 2875
You have chance to buy or sell items on Nexus for Hero: Reverie_Ashels
1: Buy
2: Sell
0: Quit
0
You chose to quit, good luck!
Please choose one action for hero Parzival:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Cave space and gained 10% of Agility
Hero exited the Bush space and lost extra 10% of dexterity
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |H3     |  |     M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |     M1|  | X X X |  |       |  |H1     |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
================= Heroes' tur end =================
================= Monster's turn begin =================
Monster BigBad-Wolf is taking action.
M1
No hero is within attack range, move forward
Press any key to proceed.

Monster Blinky is taking action.
Parzival did not dodge this attack!
Blinky caused 22 hp loss to Parzival
Parzival has HP: 678
Press any key to proceed.

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |H3     |  |     M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |H1     |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |     M1|  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
========Round 6 begins===========
Please choose one action for hero Muamman_Duathall:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Koulou space and gained 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |H1     |  | X X X |  |H3     |  |     M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |     M1|  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Welcome to Nexus, do you want to buy or sell items?
 The hero's gold is 2875
You have chance to buy or sell items on Nexus for Hero: Reverie_Ashels
1: Buy
2: Sell
0: Quit
0
You chose to quit, good luck!
Please choose one action for hero Reverie_Ashels:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Koulou space and gained 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |H1     |  | X X X |  |H3     |  |     M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |     M1|  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Please choose one action for hero Parzival:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
You cannot surpass the monster.
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
You cannot surpass the monster.
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
4
Hero entered Bush space and gained 10% of dexterity
Hero exited the Cave space and lost extra 10% of Agility
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |H1     |  | X X X |  |       |  |H3   M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |     M1|  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
================= Heroes' tur end =================
================= Monster's turn begin =================
Monster BigBad-Wolf is taking action.
M1
No hero is within attack range, move forward
Press any key to proceed.

Monster Blinky is taking action.
Parzival did not dodge this attack!
Blinky caused 22 hp loss to Parzival
Parzival has HP: 656
Press any key to proceed.

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |H1     |  | X X X |  |       |  |H3   M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |     M1|  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
========Round 7 begins===========
Please choose one action for hero Muamman_Duathall:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Cave space and gained 10% of Agility
Hero exited Koulou space and lost extra 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |H1     |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |H3   M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |     M1|  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Please choose one action for hero Reverie_Ashels:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

5
1. To Move Up
2. To Move Down
3. To Move Left
4. To Move Right
Please enter your move as below:
1
Hero entered Bush space and gained 10% of dexterity
Hero exited Koulou space and lost extra 10% of Strength
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
K - K - K  C - C - C  I - I - I  P - P - P  P - P - P  I - I - I  C - C - C  C - C - C

P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C
|       |  |       |  | X X X |  |       |  |H1     |  | X X X |  |       |  |       |
P - P - P  C - C - C  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  C - C - C

C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |H3   M3|
C - C - C  B - B - B  I - I - I  K - K - K  K - K - K  I - I - I  C - C - C  B - B - B

B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
B - B - B  B - B - B  I - I - I  P - P - P  P - P - P  I - I - I  B - B - B  P - P - P

K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K
|       |  |       |  | X X X |  |H2     |  |       |  | X X X |  |       |  |       |
K - K - K  P - P - P  I - I - I  B - B - B  C - C - C  I - I - I  B - B - B  K - K - K

P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C
|       |  |     M1|  | X X X |  |       |  |       |  | X X X |  |       |  |       |
P - P - P  K - K - K  I - I - I  K - K - K  C - C - C  I - I - I  K - K - K  C - C - C

N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N
|       |  |       |  | X X X |  |       |  |       |  | X X X |  |       |  |       |
N - N - N  N - N - N  I - I - I  N - N - N  N - N - N  I - I - I  N - N - N  N - N - N

    Lane 0                            Lane 1                            Lane 2
Please choose one action for hero Parzival:
 1: Attack
 2: Use a spell
 3: Change Weapon or Armor
 4: Use a potion
 5: Move
 6: Teleport
 7: Recall
 8: Quit game

1
Blinky dodged this attack.
================= Heroes' tur end =================
================= Monster's turn begin =================
Monster BigBad-Wolf is taking action.
M1
No hero is within attack range, move forward
A Monster has successfully entered the Nexus of Hero!
You lose the game!
Press any key to end the game.


Process finished with exit code 0

