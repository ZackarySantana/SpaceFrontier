 package sf.worlds;
 
 import java.util.ArrayList;
 import java.util.List;
 import javafx.scene.image.Image;
 import resources.Resources;
 import sf.Drop;
 import sf.Inventory;
 import sf.Weapon;
 
 public class World
 {
   private Worlds.WorldType _type;
   private boolean _nextPlanetUnlocked;
   private int _currentLevel;
   private int _nextPlanetLevel;
   
   public World(Worlds.WorldType type) {
     this._type = type;
     this._currentLevel = 1;
     this._nextPlanetLevel = 20;
     this._nextPlanetUnlocked = false;
     if (this._type == Worlds.WorldType.EARTH) {
       this._currentLevel = 0;
     }
   }
   
   public boolean nextPlanetUnlocked() {
     if (this._currentLevel >= this._nextPlanetLevel) {
       this._nextPlanetUnlocked = true;
     }
     return this._nextPlanetUnlocked;
   }
   
   public boolean nextLevel() {
     if (++this._currentLevel == this._nextPlanetLevel && !this._nextPlanetUnlocked) {
       return this._nextPlanetUnlocked = true;
     }
     return false;
   }
 
   
   public void lastLevel() {
     if (--this._currentLevel < 1) {
       this._currentLevel = 1;
     }
   }
   
   public int getCurrentLevel() {
     return this._currentLevel;
   }
   
   public double getCurrentMobHealth() {
     if (this._type == Worlds.WorldType.EARTH)
       return (this._currentLevel * 2 + 3); 
     if (this._type == Worlds.WorldType.MARS)
       return (this._currentLevel * 3 + 45); 
     if (this._type == Worlds.WorldType.SATURN)
       return (this._currentLevel * 4 + 101); 
     if (this._type == Worlds.WorldType.PLUTO)
       return (this._currentLevel * 5 + 186); 
     if (this._type == Worlds.WorldType.SUN) {
       return (this._currentLevel * 6 + 291);
     }
     return this._currentLevel;
   }
 
   
   public double getCurrentBossHealth() {
     if (this._type == Worlds.WorldType.EARTH)
       return (this._currentLevel * 4 + 3); 
     if (this._type == Worlds.WorldType.MARS)
       return (this._currentLevel * 6 + 45); 
     if (this._type == Worlds.WorldType.SATURN)
       return (this._currentLevel * 8 + 101); 
     if (this._type == Worlds.WorldType.PLUTO)
       return (this._currentLevel * 10 + 186); 
     if (this._type == Worlds.WorldType.SUN) {
       return (this._currentLevel * 12 + 291);
     }
     return this._currentLevel;
   }
 
   
   public double getCurrentEnemyPower() {
     return (((this._currentLevel == 0) ? 5L : Math.round(this._currentLevel / 2.0D)) * this._type.getDifficulty());
   }
   
   public Image[] getEnemies() {
     Image[] enemies = new Image[3];
     if (this._type != Worlds.WorldType.PLUTO) {
       if (this._currentLevel % 5 == 0) {
         enemies[0] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob", Resources.Type.ENEMY);
         enemies[1] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob", Resources.Type.ENEMY);
         enemies[2] = Resources.getImage(String.valueOf(this._type.getPath()) + "-boss", Resources.Type.ENEMY);
       } else {
         enemies[0] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob", Resources.Type.ENEMY);
         enemies[1] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob", Resources.Type.ENEMY);
         enemies[2] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob", Resources.Type.ENEMY);
       }
     
     } else if (this._currentLevel % 5 == 0) {
       enemies[0] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob" + ((int)(Math.random() * 3.0D) + 1), 
           Resources.Type.ENEMY);
       enemies[1] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob" + ((int)(Math.random() * 3.0D) + 1), 
           Resources.Type.ENEMY);
       enemies[2] = Resources.getImage(String.valueOf(this._type.getPath()) + "-boss", Resources.Type.ENEMY);
     } else {
       enemies[0] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob" + ((int)(Math.random() * 3.0D) + 1), 
           Resources.Type.ENEMY);
       enemies[1] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob" + ((int)(Math.random() * 3.0D) + 1), 
           Resources.Type.ENEMY);
       enemies[2] = Resources.getImage(String.valueOf(this._type.getPath()) + "-mob" + ((int)(Math.random() * 3.0D) + 1), 
           Resources.Type.ENEMY);
     } 
     
     return enemies;
   }
   
   public List<Drop> getDrops(Inventory inventory) {
     List<Drop> drops = new ArrayList<>();
     if (this._currentLevel % 5 == 0) {
       Weapon planet = Weapon.valueOf(this._type.getPath().toUpperCase());
       if (!inventory.contains(planet)) {
         if (this._currentLevel == 0) {
           drops.add(new Drop(Resources.getImage(String.valueOf(this._type.getPath()) + "-sword", Resources.Type.WEAPON), planet, 1.0D));
         } else {
           drops.add(new Drop(Resources.getImage(String.valueOf(this._type.getPath()) + "-sword", Resources.Type.WEAPON), planet, 
                 this._currentLevel / 500.0D));
         } 
       }
     } 
     if (this._currentLevel >= 15 * this._type.getDifficulty() && 
       !inventory.contains(Weapon.LEGENDARY)) {
       drops.add(new Drop(Resources.getImage("legendary-sword", Resources.Type.WEAPON), Weapon.LEGENDARY, 
             this._currentLevel / 1500.0D));
     }
     
     if (this._currentLevel >= 10 * this._type.getDifficulty() && 
       !inventory.contains(Weapon.EPIC)) {
       drops.add(new Drop(Resources.getImage("epic-sword", Resources.Type.WEAPON), Weapon.EPIC, 
             this._currentLevel / 1000.0D));
     }
     
     if (this._currentLevel >= 5 * this._type.getDifficulty() && 
       !inventory.contains(Weapon.RARE)) {
       drops.add(new Drop(Resources.getImage("rare-sword", Resources.Type.WEAPON), Weapon.RARE, 
             this._currentLevel / 500.0D));
     }
 
     
     return drops;
   }
 }


/* Location:              E:\Old Coding\Old Programs\SpaceFrontier.jar!\sf\worlds\World.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */