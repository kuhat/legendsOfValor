# CS611-Legends

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

javac -d bin src/*.java src/game/*.java src/game/utils/*.java src/game/RPGGame/*.java src/game/role/*.java src/game/role/heroes/*.java src/game/role/item/*.java src/game/role/monsters/*.java
java -cp bin main


Sample output
---------------------------------------------------------------------------------------------------------------


