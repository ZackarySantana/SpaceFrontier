 package sf.worlds;
 
 public class Worlds {
   private WorldType _current;
   private World _earth;
   private World _mars;
   
   public Worlds() {
     this._earth = new World(WorldType.EARTH);
     this._mars = new World(WorldType.MARS);
     this._saturn = new World(WorldType.SATURN);
     this._pluto = new World(WorldType.PLUTO);
     this._sun = new World(WorldType.SUN);
     this._current = WorldType.EARTH;
   }
   private World _saturn; private World _pluto; private World _sun;
   public World getCurrentWorld() {
     if (this._current == WorldType.EARTH)
       return this._earth; 
     if (this._current == WorldType.MARS)
       return this._mars; 
     if (this._current == WorldType.SATURN)
       return this._saturn; 
     if (this._current == WorldType.PLUTO)
       return this._pluto; 
     if (this._current == WorldType.SUN) {
       return this._sun;
     }
     return this._earth;
   }
 
   
   public WorldType getCurrentWorldType() {
     return this._current;
   }
   
   public boolean isAvailable(WorldType type) {
     if (type == WorldType.EARTH)
       return true; 
     if (type == WorldType.MARS) {
       if (this._earth.nextPlanetUnlocked()) {
         return true;
       }
     } else if (type == WorldType.SATURN) {
       if (this._mars.nextPlanetUnlocked()) {
         return true;
       }
     } else if (type == WorldType.PLUTO) {
       if (this._saturn.nextPlanetUnlocked()) {
         return true;
       }
     } else if (type == WorldType.SUN && 
       this._pluto.nextPlanetUnlocked()) {
       return true;
     } 
     
     return false;
   }
   
   public boolean setCurrentWorld(WorldType type) {
     if (type == WorldType.EARTH) {
       this._current = type;
       return true;
     }  if (type == WorldType.MARS) {
       if (this._earth.nextPlanetUnlocked()) {
         this._current = type;
         return true;
       } 
     } else if (type == WorldType.SATURN) {
       if (this._mars.nextPlanetUnlocked()) {
         this._current = type;
         return true;
       } 
     } else if (type == WorldType.PLUTO) {
       if (this._saturn.nextPlanetUnlocked()) {
         this._current = type;
         return true;
       } 
     } else if (type == WorldType.SUN && 
       this._pluto.nextPlanetUnlocked()) {
       this._current = type;
       return true;
     } 
     
     return false;
   }
   
   public enum WorldType {
     EARTH("earth", 1), MARS("mars", 2), SATURN("saturn", 3), PLUTO("pluto", 4), SUN("sun", 5);
     
     private int _difficulty;
     
     private String _path;
     
     WorldType(String path, int difficulty) {
       this._path = path;
       this._difficulty = difficulty;
     }
     
     public String getPath() {
       return this._path;
     }
     
     public int getDifficulty() {
       return this._difficulty;
     }
   }
 }


/* Location:              E:\Old Coding\Old Programs\SpaceFrontier.jar!\sf\worlds\Worlds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */