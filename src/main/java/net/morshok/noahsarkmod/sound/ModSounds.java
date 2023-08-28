package net.morshok.noahsarkmod.sound;

import net.minecraft.client.sound.Sound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.morshok.noahsarkmod.NoahsArkMod;

import java.util.List;
import java.util.Random;

public class ModSounds
{
    public static SoundEvent LAUGHTER = registerSoundEvent("laughter");
    public static SoundEvent KNOCKBACK2_BREAD = registerSoundEvent("knockback2_bread");
    public static SoundEvent SIP = registerSoundEvent("sip");
    public static SoundEvent SIP_DONE = registerSoundEvent("sip_done");
    public static SoundEvent BURP = registerSoundEvent("burp");
    public static SoundEvent GRIMACE_SHAKE_BURP = registerSoundEvent("grimace_shake_burp");
    public static SoundEvent I_HAVE_ANTHONS_CAR_KEYS_1 = registerSoundEvent("i_have_anthons_car_keys_1");
    public static SoundEvent I_HAVE_ANTHONS_CAR_KEYS_2 = registerSoundEvent("i_have_anthons_car_keys_2");
    public static SoundEvent I_HAVE_ANTHONS_CAR_KEYS_3 = registerSoundEvent("i_have_anthons_car_keys_3");
    public static SoundEvent FUCKING_MEATBALL_1 = registerSoundEvent("fucking_meatball_1");
    public static SoundEvent FUCKING_MEATBALL_2 = registerSoundEvent("fucking_meatball_2");
    public static SoundEvent FUCKING_BIRD_1 = registerSoundEvent("fucking_bird_1");
    public static SoundEvent FUCKING_BIRD_2 = registerSoundEvent("fucking_bird_2");
    public static SoundEvent FUCKING_BIRD_3 = registerSoundEvent("fucking_bird_3");
    public static SoundEvent GIGA_CHAD_THEME = registerSoundEvent("giga_chad_theme");
    public static SoundEvent SOME_RANDOM_GUY_IN_A_GARAGE = registerSoundEvent("some_random_guy_in_a_garage");

    public static void registerModSounds()
    {
        NoahsArkMod.LOGGER.info("Registering Mod Sounds for " + NoahsArkMod.MOD_ID);
    }

    private static SoundEvent registerSoundEvent(String name)
    {
        Identifier identifier = new Identifier(NoahsArkMod.MOD_ID, name);

        return Registry.register(
                Registries.SOUND_EVENT,
                identifier,
                SoundEvent.of(identifier)
        );
    }

    public static SoundEvent getRandomSoundEvent(List<SoundEvent> soundEventList)
    {
        return soundEventList.get(new Random().nextInt(soundEventList.size()));
    }
}