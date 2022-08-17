package io.github.jacksonhoggard.spidersmakewebs;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.block.Blocks;

public class PlaceWebRandomGoal extends Goal {

    private Spider spider;

    public PlaceWebRandomGoal(Spider spider) {
        this.spider = spider;
    }

    @Override
    public boolean canUse() {
        return spider.getRandom().nextFloat() < 0.02F;
    }

    @Override
    public void start() {
        spider.level.setBlockAndUpdate(spider.blockPosition(), Blocks.COBWEB.defaultBlockState());
    }
}
