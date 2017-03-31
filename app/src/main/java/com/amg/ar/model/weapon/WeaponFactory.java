package com.amg.ar.model.weapon;

/**
 * Every {@link com.amg.ar.model.weapon.Weapon} should be instantiated from this factory
 */
public class WeaponFactory {
    private static final int BASIC_WEAPON_DAMAGE = 1;
    private static final int BASIC_WEAPON_AMMUNITION_LIMIT = 8;
    private static final long BASIC_WEAPON_RELOADING_TIME = 1000;

    /**
     * Create a very basic {@link com.amg.ar.model.weapon.Weapon}
     *
     * @return a basic {@link com.amg.ar.model.weapon.Weapon}
     */
    public static Weapon createBasicWeapon() {
        Weapon basicWeapon = new Weapon();
        basicWeapon.setDamage(BASIC_WEAPON_DAMAGE);//伤害
        basicWeapon.setAmmunitionLimit(BASIC_WEAPON_AMMUNITION_LIMIT);//弹量
        basicWeapon.setReloadingTime(BASIC_WEAPON_RELOADING_TIME);//
        basicWeapon.reload(BASIC_WEAPON_AMMUNITION_LIMIT);
        return basicWeapon;
    }
}
