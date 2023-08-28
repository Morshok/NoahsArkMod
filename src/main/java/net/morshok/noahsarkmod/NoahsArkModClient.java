package net.morshok.noahsarkmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.Queue;

public class NoahsArkModClient implements ClientModInitializer
{
    public static final Queue<Integer> TRIDENT_QUEUE = new LinkedList<>();
    @Override
    public void onInitializeClient()
    {
        ClientPlayConnectionEvents.INIT.register((handler, client) -> {
            ClientPlayNetworking.registerReceiver(
                    new Identifier(NoahsArkMod.MOD_ID, "anthons_car_keys"),
                    (minecraft, listener, buf, responseSender) -> TRIDENT_QUEUE.add(buf.readInt())
            );
        });
    }
}