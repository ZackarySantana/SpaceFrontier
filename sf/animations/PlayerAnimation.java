package sf.animations;

import java.text.DecimalFormat;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import resources.Resources;
import sf.AchievementMenu;
import sf.Drop;
import sf.Inventory;
import sf.Main;
import sf.Weapon;
import sf.WorldsMenu;
import sf.worlds.Worlds;

public class PlayerAnimation extends MainAnimation {
	public Worlds _worlds;
	public Inventory _inventory;
	private ColorAdjust _colorAdjust;
	private ColorAdjust _blank;
	private DecimalFormat _format;
	private AudioClip _playerDamage;
	private AudioClip _playerDeath;
	private AudioClip _playerLevelup;
	private AudioClip _bossDeath;
	private double _velX;
	private double _velY;
	private double _health;
	private double _enemy1;
	private double _enemy2;
	private double _enemy3;
	private int _goBack;

	public void stp() {
		this._playerDeath.stop();
		this._worlds = new Worlds();
		this._inventory = new Inventory();
		this._borderWidth = 515.0D;
		this._borderHeight = 515.0D;
		this._velX = 2.3D;
		this._playerLevel = 1;
		this._playerExp = 0;
		this._goBack = 0;
		this._maxHealth = 100;
		this._health = this._maxHealth;
		this._goFirst = false;
		this._dead = false;
		this._colorAdjust = new ColorAdjust();
		this._colorAdjust.setSaturation(0.9D);
		this._blank = new ColorAdjust();
		this._format = new DecimalFormat("#.##");

		this._sprite.setLayoutX((this._velX > 0.0D) ? this._begX : 459.0D);
		this._sprite.setLayoutY(this._begY);
		this._enemy1 = this._worlds.getCurrentWorld().getCurrentMobHealth();
		_controller._enemy1.setBar(1.0D);
		_controller._enemy1.setVisible(true);
		this._enemy2 = this._enemy1;
		_controller._enemy2.setBar(1.0D);
		_controller._enemy2.setVisible(true);
		if (this._worlds.getCurrentWorld().getCurrentLevel() % 5 == 0) {
			this._enemy3 = this._worlds.getCurrentWorld().getCurrentBossHealth();
		} else {
			this._enemy3 = this._enemy1;
		}
		_controller._enemy3.setBar(1.0D);
		_controller._enemy3.setVisible(true);
		this._velY = -1.0D;
		this._direction = true;
	}

	private int _maxHealth;
	private int _playerLevel;
	private int _playerExp;
	private boolean _direction;
	private boolean _goFirst;
	private boolean _dead;
	private int _killedMobs;
	private int _killedBosses;
	private int _heal;
	private int _stagesBack;
	private boolean _1mob;
	private boolean _2mob;
	private boolean _1boss;
	private boolean _2boss;
	private boolean _1heal;
	private boolean _1stageBack;

	public PlayerAnimation() {
		super(_controller.player, 455.0D, 163.0D, 4500);
		this._worlds = new Worlds();
		this._inventory = new Inventory();
		this._borderWidth = 515.0D;
		this._borderHeight = 515.0D;
		this._velX = 2.3D;
		this._playerLevel = 1;
		this._playerExp = 0;
		this._goBack = 0;
		this._maxHealth = 100;
		this._health = this._maxHealth;
		this._goFirst = false;
		this._dead = false;
		this._colorAdjust = new ColorAdjust();
		this._colorAdjust.setSaturation(0.9D);
		this._blank = new ColorAdjust();
		this._format = new DecimalFormat("#.##");

		this._playerDamage = new AudioClip(Resources.getSoundEffect("player-damage").getSource());
		this._playerDamage.setCycleCount(1);

		this._playerDeath = new AudioClip(Resources.getSoundEffect("player-death").getSource());
		this._playerDeath.setCycleCount(-1);

		this._playerLevelup = new AudioClip(Resources.getSoundEffect("player-level_up").getSource());
		this._playerLevelup.setCycleCount(1);

		this._bossDeath = new AudioClip(Resources.getSoundEffect("boss-death").getSource());
		this._bossDeath.setCycleCount(1);

		this._sprite.setLayoutX((this._velX > 0.0D) ? this._begX : 459.0D);
		this._sprite.setLayoutY(this._begY);
		this._enemy1 = this._worlds.getCurrentWorld().getCurrentMobHealth();
		_controller._enemy1.setBar(1.0D);
		_controller._enemy1.setVisible(true);
		this._enemy2 = this._enemy1;
		_controller._enemy2.setBar(1.0D);
		_controller._enemy2.setVisible(true);
		if (this._worlds.getCurrentWorld().getCurrentLevel() % 5 == 0) {
			this._enemy3 = this._worlds.getCurrentWorld().getCurrentBossHealth();
		} else {
			this._enemy3 = this._enemy1;
		}
		_controller._enemy3.setBar(1.0D);
		_controller._enemy3.setVisible(true);
		this._velY = -1.0D;
		this._direction = true;
	}

	protected void interpolate(double frac) {
		double touch = this._sprite.getLayoutX() + this._sprite.getFitWidth();
		this._sprite.setLayoutY(
				(this._sprite.getLayoutY() + this._velY > 163.0D) ? 163.0D : (this._sprite.getLayoutY() + this._velY));

		if (this._direction) {
			this._velY += 0.2D;
			if (this._velY >= 1.0D) {
				this._direction = false;
			}
		} else {
			this._velY -= 0.2D;
			if (this._velY <= -1.0D) {
				this._direction = true;
			}
		}
		if (this._goBack > 0) {
			this._sprite.setLayoutX(this._sprite.getLayoutX() - this._goBack);
			this._goBack--;
		} else {
			this._sprite.setLayoutX(this._sprite.getLayoutX() + this._velX);
			_controller.enemy1.setEffect(this._blank);
			_controller.enemy2.setEffect(this._blank);
			_controller.enemy3.setEffect(this._blank);
		}
		_controller._player.setX(this._sprite.getLayoutX());

		if (touch >= 520.0D) {
			nextLevel();
			reset();
		} else if (touch >= 450.0D) {
			if (this._enemy3 > 0.0D) {
				this._enemy3 -= this._inventory.get().getPower() * this._playerLevel;
				if (this._enemy3 <= 0.0D) {
					_controller.enemy3.setImage((Image) null);
					_controller._enemy3.setVisible(false);
					if (this._worlds.getCurrentWorld().getCurrentLevel() % 5 == 0) {
						if (++this._killedBosses >= 10) {
							if (!this._1boss) {
								this._1boss = true;
								(AchievementMenu.getMe()).three.setImage(Resources.getImage("g", Resources.Type.ICON));
							}
							if (this._killedBosses >= 50 && !this._2boss) {
								this._2boss = true;
								(AchievementMenu.getMe()).four.setImage(Resources.getImage("g", Resources.Type.ICON));
							}
						}

						this._bossDeath.play();
					} else if (++this._killedMobs >= 10) {
						if (!this._1mob) {
							this._1mob = true;
							(AchievementMenu.getMe()).one.setImage(Resources.getImage("g", Resources.Type.ICON));
						}
						if (this._killedMobs >= 50 && !this._2mob) {
							this._2mob = true;
							(AchievementMenu.getMe()).two.setImage(Resources.getImage("g", Resources.Type.ICON));
						}
					}

					List<Drop> drops = this._worlds.getCurrentWorld().getDrops(this._inventory);
					for (int i = 0; i < ((drops.size() > 3) ? 3 : drops.size()); i++) {
						if (!this._inventory.contains(((Drop) drops.get(i)).getWeapon())) {
							double chance = Math.random();
							if (chance < ((Drop) drops.get(i)).getChance()) {
								this._inventory.add(((Drop) drops.get(i)).getWeapon());
							}
						}
					}
				} else {
					_controller.enemy3.setEffect(this._colorAdjust);
				}
				if (this._worlds.getCurrentWorld().getCurrentLevel() % 5 == 0) {
					_controller._enemy3.setBar(this._enemy3 / this._worlds.getCurrentWorld().getCurrentBossHealth());
				} else {
					_controller._enemy3.setBar(this._enemy3 / this._worlds.getCurrentWorld().getCurrentMobHealth());
				}
				if (doHealth()) {
					goBack();
					if (this._enemy3 <= 0.0D) {
						doExp();
					}
				}
			}
		} else if (touch >= 350.0D) {
			if (this._enemy2 > 0.0D) {
				this._enemy2 -= this._inventory.get().getPower() * this._playerLevel;
				if (this._enemy2 <= 0.0D) {
					_controller.enemy2.setImage((Image) null);
					_controller._enemy2.setVisible(false);
					if (++this._killedMobs >= 10) {
						if (!this._1mob) {
							this._1mob = true;
							(AchievementMenu.getMe()).one.setImage(Resources.getImage("g", Resources.Type.ICON));
						}
						if (this._killedMobs >= 50 && !this._2mob) {
							this._2mob = true;
							(AchievementMenu.getMe()).two.setImage(Resources.getImage("g", Resources.Type.ICON));
						}
					}
				} else {

					_controller.enemy2.setEffect(this._colorAdjust);
				}
				_controller._enemy2.setBar(this._enemy2 / this._worlds.getCurrentWorld().getCurrentMobHealth());
				if (doHealth()) {
					goBack();
					if (this._enemy2 <= 0.0D) {
						doExp();
					}
				}
			}
		} else if (touch >= 250.0D) {
			if (this._enemy1 > 0.0D) {
				this._enemy1 -= this._inventory.get().getPower() * this._playerLevel;
				if (this._enemy1 <= 0.0D) {
					_controller.enemy1.setImage((Image) null);
					_controller._enemy1.setVisible(false);
					if (++this._killedMobs >= 10) {
						if (!this._1mob) {
							this._1mob = true;
							(AchievementMenu.getMe()).one.setImage(Resources.getImage("g", Resources.Type.ICON));
						}
						if (this._killedMobs >= 50 && !this._2mob) {
							this._2mob = true;
							(AchievementMenu.getMe()).two.setImage(Resources.getImage("g", Resources.Type.ICON));
						}
					}
				} else {

					_controller.enemy1.setEffect(this._colorAdjust);
				}
				_controller._enemy1.setBar(this._enemy1 / this._worlds.getCurrentWorld().getCurrentMobHealth());
				if (doHealth()) {
					goBack();
					if (this._enemy1 <= 0.0D) {
						doExp();
					}
				}
			}
		} else if (this._sprite.getLayoutX() <= 18.0D) {
			if (this._worlds.getCurrentWorld().getCurrentLevel() == 0) {
				this._sprite.setLayoutX(this._begX);
				this._sprite.setLayoutY(this._begY);
				this._velX = 2.3D;
				this._sprite.setScaleX(1.0D);
				return;
			}
			reset();
			lastLevel();
			int heal = this._maxHealth / 2;
			this._health += heal;
			if (this._health > this._maxHealth) {
				this._health = this._maxHealth;
			}
			if ((this._heal += heal) >= 500 && !this._1heal) {
				this._1heal = true;
				(AchievementMenu.getMe()).five.setImage(Resources.getImage("g", Resources.Type.ICON));
			}

			_controller.health_text.setText(String.valueOf(this._format.format(this._health)) + "/" + this._maxHealth);
			_controller._player.setBar(this._health / this._maxHealth);
		}
	}

	public void changeWorlds(Worlds.WorldType type) {
		if (this._worlds.setCurrentWorld(type)) {
			_controller.background.setImage(Resources.getImage(type.getPath(), Resources.Type.MAP));
			reset();
		}
	}

	public void doExp() {
		if (++this._playerExp >= this._playerLevel) {
			this._playerExp = 0;
			this._playerLevel++;
			this._maxHealth += 5;
			this._health += (this._maxHealth / 4);
			if (this._health > this._maxHealth) {
				this._health = this._maxHealth;
			}
			_controller.health_text.setText(String.valueOf(this._format.format(this._health)) + "/" + this._maxHealth);
			_controller._player.setBar(this._health / this._maxHealth);
			_controller.player_level_text.setText((new StringBuilder(String.valueOf(this._playerLevel))).toString());
			this._playerLevelup.play();
		}
	}

	public boolean equipWeapon(Weapon weapon) {
		if (!this._inventory.contains(weapon)) {
			return false;
		}
		_controller.power_text.setText((new StringBuilder(String.valueOf(weapon.getPower()))).toString());
		if (this._worlds.getCurrentWorldType() == Worlds.WorldType.EARTH) {
			if (weapon == Weapon.EARTH) {
				_controller.currentSword_image.setImage(Resources.getImage("earth-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character-earth", Resources.Type.CHARACTER));
			} else if (weapon == Weapon.CHEAT) {
				_controller.currentSword_image.setImage(Resources.getImage("cheat-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character-cheat", Resources.Type.CHARACTER));
			} else if (weapon == Weapon.DEFAUlT) {
				_controller.currentSword_image.setImage(Resources.getImage("default-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character", Resources.Type.CHARACTER));
			} else if (weapon == Weapon.EPIC) {
				_controller.currentSword_image.setImage(Resources.getImage("epic-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character-epic", Resources.Type.CHARACTER));
			} else if (weapon == Weapon.LEGENDARY) {
				_controller.currentSword_image.setImage(Resources.getImage("legendary-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character-legendary", Resources.Type.CHARACTER));
			} else if (weapon == Weapon.PLUTO) {
				_controller.currentSword_image.setImage(Resources.getImage("pluto-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character-pluto", Resources.Type.CHARACTER));
			} else if (weapon == Weapon.RARE) {
				_controller.currentSword_image.setImage(Resources.getImage("rare-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character-rare", Resources.Type.CHARACTER));
			} else if (weapon == Weapon.SATURN) {
				_controller.currentSword_image.setImage(Resources.getImage("saturn-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character-saturn", Resources.Type.CHARACTER));
			} else if (weapon == Weapon.SUN) {
				_controller.currentSword_image.setImage(Resources.getImage("sun-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character-sun", Resources.Type.CHARACTER));
			} else if (weapon == Weapon.MARS) {
				_controller.currentSword_image.setImage(Resources.getImage("mars-sword", Resources.Type.WEAPON));
				_controller.player.setImage(Resources.getImage("character-mars", Resources.Type.CHARACTER));
			}

		} else if (weapon == Weapon.EARTH) {
			_controller.currentSword_image.setImage(Resources.getImage("earth-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space-earth", Resources.Type.CHARACTER));
		} else if (weapon == Weapon.CHEAT) {
			_controller.currentSword_image.setImage(Resources.getImage("cheat-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space-cheat", Resources.Type.CHARACTER));
		} else if (weapon == Weapon.DEFAUlT) {
			_controller.currentSword_image.setImage(Resources.getImage("default-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space", Resources.Type.CHARACTER));
		} else if (weapon == Weapon.EPIC) {
			_controller.currentSword_image.setImage(Resources.getImage("epic-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space-epic", Resources.Type.CHARACTER));
		} else if (weapon == Weapon.LEGENDARY) {
			_controller.currentSword_image.setImage(Resources.getImage("legendary-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space-legendary", Resources.Type.CHARACTER));
		} else if (weapon == Weapon.PLUTO) {
			_controller.currentSword_image.setImage(Resources.getImage("pluto-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space-pluto", Resources.Type.CHARACTER));
		} else if (weapon == Weapon.RARE) {
			_controller.currentSword_image.setImage(Resources.getImage("rare-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space-rare", Resources.Type.CHARACTER));
		} else if (weapon == Weapon.SATURN) {
			_controller.currentSword_image.setImage(Resources.getImage("saturn-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space-saturn", Resources.Type.CHARACTER));
		} else if (weapon == Weapon.SUN) {
			_controller.currentSword_image.setImage(Resources.getImage("sun-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space-sun", Resources.Type.CHARACTER));
		} else if (weapon == Weapon.MARS) {
			_controller.currentSword_image.setImage(Resources.getImage("mars-sword", Resources.Type.WEAPON));
			_controller.player.setImage(Resources.getImage("space-mars", Resources.Type.CHARACTER));
		}

		this._inventory.setCurrent(weapon);
		return true;
	}

	public boolean doHealth() {
		this._health -= this._worlds.getCurrentWorld().getCurrentEnemyPower();
		_controller.health_text.setText(String.valueOf(this._format.format(this._health)) + "/" + this._maxHealth);
		_controller._player.setBar(this._health / this._maxHealth);
		if (this._health <= 0.0D) {
			this._dead = true;
			this._sprite.setLayoutY(163.0D);
			Main.getInstance().getMusic().stop();
			this._playerDeath.play();
			stop();
			_controller._deathMenu.show();
			return false;
		}
		this._playerDamage.play();

		return true;
	}

	public void goBack() {
		this._sprite.setLayoutX(this._sprite.getLayoutX() - 5.0D);
		this._goBack = 8;
	}

	public void nextLevel() {
		this._stagesBack = 0;
		if (this._goFirst) {
			this._goFirst = false;
		} else if (this._worlds.getCurrentWorld().nextLevel()) {
			if (this._worlds.getCurrentWorldType() == Worlds.WorldType.EARTH) {
				(WorldsMenu.getMe()).mars.setDisable(false);
			} else if (this._worlds.getCurrentWorldType() == Worlds.WorldType.MARS) {
				(WorldsMenu.getMe()).saturn.setDisable(false);
			} else if (this._worlds.getCurrentWorldType() == Worlds.WorldType.SATURN) {
				(WorldsMenu.getMe()).pluto.setDisable(false);
			} else if (this._worlds.getCurrentWorldType() == Worlds.WorldType.PLUTO) {
				(WorldsMenu.getMe()).sun.setDisable(false);
			}
			if (this._worlds.getCurrentWorldType() != Worlds.WorldType.SUN) {
				(new Alert(Alert.AlertType.INFORMATION, "You have unlocked the next world!!",
						new ButtonType[] { ButtonType.OK })).show();
			}
		}
		Image[] enemies = this._worlds.getCurrentWorld().getEnemies();
		_controller.level_text.setText(
				(new StringBuilder(String.valueOf(this._worlds.getCurrentWorld().getCurrentLevel()))).toString());
		_controller.enemy1.setImage(enemies[0]);
		_controller.enemy2.setImage(enemies[1]);
		_controller.enemy3.setImage(enemies[2]);

		List<Drop> drops = this._worlds.getCurrentWorld().getDrops(this._inventory);
		if (drops.size() > 0) {
			_controller.pdrop1_image.setImage(((Drop) drops.get(0)).getDrop());
			_controller.pdrop1_chance
					.setText(String.valueOf(this._format.format(((Drop) drops.get(0)).getChance() * 100.0D)) + "%");
			if (drops.size() > 1) {
				_controller.pdrop2_image.setImage(((Drop) drops.get(1)).getDrop());
				_controller.pdrop2_chance
						.setText(String.valueOf(this._format.format(((Drop) drops.get(1)).getChance() * 100.0D)) + "%");
				if (drops.size() > 2) {
					_controller.pdrop3_image.setImage(((Drop) drops.get(2)).getDrop());
					_controller.pdrop3_chance.setText(
							String.valueOf(this._format.format(((Drop) drops.get(2)).getChance() * 100.0D)) + "%");
				} else {
					_controller.pdrop3_image.setImage((Image) null);
					_controller.pdrop3_chance.setText("");
				}
			} else {
				_controller.pdrop2_image.setImage((Image) null);
				_controller.pdrop2_chance.setText("");
				_controller.pdrop3_image.setImage((Image) null);
				_controller.pdrop3_chance.setText("");
			}
		} else {
			_controller.pdrop1_image.setImage((Image) null);
			_controller.pdrop1_chance.setText("");
			_controller.pdrop2_image.setImage((Image) null);
			_controller.pdrop2_chance.setText("");
			_controller.pdrop3_image.setImage((Image) null);
			_controller.pdrop3_chance.setText("");
		}
	}

	public void lastLevel() {
		if (++this._stagesBack >= 10 && !this._1stageBack) {
			this._1stageBack = true;
			(AchievementMenu.getMe()).six.setImage(Resources.getImage("g", Resources.Type.ICON));
		}

		if (this._worlds.getCurrentWorld().getCurrentLevel() == 1) {
			this._goFirst = true;
		}
		this._worlds.getCurrentWorld().lastLevel();
		_controller.level_text.setText(
				(new StringBuilder(String.valueOf(this._worlds.getCurrentWorld().getCurrentLevel()))).toString());
		_controller.enemy1.setImage((Image) null);
		_controller.enemy2.setImage((Image) null);
		_controller.enemy3.setImage((Image) null);

		List<Drop> drops = this._worlds.getCurrentWorld().getDrops(this._inventory);
		if (drops.size() > 0) {
			_controller.pdrop1_image.setImage(((Drop) drops.get(0)).getDrop());
			_controller.pdrop1_chance
					.setText(String.valueOf(this._format.format(((Drop) drops.get(0)).getChance() * 100.0D)) + "%");
			if (drops.size() > 1) {
				_controller.pdrop2_image.setImage(((Drop) drops.get(1)).getDrop());
				_controller.pdrop2_chance
						.setText(String.valueOf(this._format.format(((Drop) drops.get(1)).getChance() * 100.0D)) + "%");
				if (drops.size() > 2) {
					_controller.pdrop3_image.setImage(((Drop) drops.get(2)).getDrop());
					_controller.pdrop3_chance.setText(
							String.valueOf(this._format.format(((Drop) drops.get(2)).getChance() * 100.0D)) + "%");
				} else {
					_controller.pdrop3_image.setImage((Image) null);
					_controller.pdrop3_chance.setText("");
				}
			} else {
				_controller.pdrop2_image.setImage((Image) null);
				_controller.pdrop2_chance.setText("");
				_controller.pdrop3_image.setImage((Image) null);
				_controller.pdrop3_chance.setText("");
			}
		} else {
			_controller.pdrop1_image.setImage((Image) null);
			_controller.pdrop1_chance.setText("");
			_controller.pdrop2_image.setImage((Image) null);
			_controller.pdrop2_chance.setText("");
			_controller.pdrop3_image.setImage((Image) null);
			_controller.pdrop3_chance.setText("");
		}
	}

	public void forward() {
		this._velX = 2.3D;
		this._sprite.setScaleX(1.0D);
	}

	public void backward() {
		this._velX = -2.3D;
		this._sprite.setScaleX(-1.0D);
	}

	private void reset() {
		this._sprite.setLayoutX((this._velX > 0.0D) ? this._begX : 459.0D);
		this._sprite.setLayoutY(this._begY);
		if (this._velX > 0.0D) {
			this._enemy1 = this._worlds.getCurrentWorld().getCurrentMobHealth();
			_controller._enemy1.setBar(1.0D);
			_controller._enemy1.setVisible(true);
			this._enemy2 = this._enemy1;
			_controller._enemy2.setBar(1.0D);
			_controller._enemy2.setVisible(true);
			if (this._worlds.getCurrentWorld().getCurrentLevel() % 5 == 0) {
				this._enemy3 = this._worlds.getCurrentWorld().getCurrentBossHealth();
			} else {
				this._enemy3 = this._enemy1;
			}
			_controller._enemy3.setBar(1.0D);
			_controller._enemy3.setVisible(true);
		} else {
			this._enemy1 = 0.0D;
			_controller._enemy1.setVisible(false);
			this._enemy2 = 0.0D;
			_controller._enemy2.setVisible(false);
			this._enemy3 = 0.0D;
			_controller._enemy3.setVisible(false);
		}
		this._velY = -1.0D;
		this._direction = true;
		Image[] enemies = this._worlds.getCurrentWorld().getEnemies();
		_controller.level_text.setText(
				(new StringBuilder(String.valueOf(this._worlds.getCurrentWorld().getCurrentLevel()))).toString());
		_controller.enemy1.setImage(enemies[0]);
		_controller.enemy2.setImage(enemies[1]);
		_controller.enemy3.setImage(enemies[2]);

		List<Drop> drops = this._worlds.getCurrentWorld().getDrops(this._inventory);
		if (drops.size() > 0) {
			_controller.pdrop1_image.setImage(((Drop) drops.get(0)).getDrop());
			_controller.pdrop1_chance
					.setText(String.valueOf(this._format.format(((Drop) drops.get(0)).getChance() * 100.0D)) + "%");
			if (drops.size() > 1) {
				_controller.pdrop2_image.setImage(((Drop) drops.get(1)).getDrop());
				_controller.pdrop2_chance
						.setText(String.valueOf(this._format.format(((Drop) drops.get(1)).getChance() * 100.0D)) + "%");
				if (drops.size() > 2) {
					_controller.pdrop3_image.setImage(((Drop) drops.get(2)).getDrop());
					_controller.pdrop3_chance.setText(
							String.valueOf(this._format.format(((Drop) drops.get(2)).getChance() * 100.0D)) + "%");
				} else {
					_controller.pdrop3_image.setImage((Image) null);
					_controller.pdrop3_chance.setText("");
				}
			} else {
				_controller.pdrop2_image.setImage((Image) null);
				_controller.pdrop2_chance.setText("");
				_controller.pdrop3_image.setImage((Image) null);
				_controller.pdrop3_chance.setText("");
			}
		} else {
			_controller.pdrop1_image.setImage((Image) null);
			_controller.pdrop1_chance.setText("");
			_controller.pdrop2_image.setImage((Image) null);
			_controller.pdrop2_chance.setText("");
			_controller.pdrop3_image.setImage((Image) null);
			_controller.pdrop3_chance.setText("");
		}
	}

	public boolean isDead() {
		return this._dead;
	}
}
