package io.github.jacksonhoggard.spidersmakewebs;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod("spidersmakewebs")
public class SpidersMakeWebs
{
    public static final Logger LOGGER = LogUtils.getLogger();

    public SpidersMakeWebs()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void EntityJoined(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Spider spider) {
            ((Spider) event.getEntity()).goalSelector.addGoal(2, new MakeWebGoal(spider));
            ((Spider) event.getEntity()).goalSelector.addGoal(7, new PlaceWebRandomGoal(spider));
        }
    }

}
