package net.fabricmc.example;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class BlockMap extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty TOP = IntProperty.of("top", 0, 2);

    public BlockMap(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING);
        stateManager.add(TOP);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ctx) {
        Direction dir = state.get(FACING);
        if (dir.getAxis() == Direction.Axis.X)
            if (state.get(TOP) == 0) {
                if (dir == Direction.EAST) {
                    return VoxelShapes.cuboid(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.0625F);
                } else if (dir == Direction.WEST) {
                    return VoxelShapes.cuboid(0.4375F, 0.0F, 0.9375F, 0.5625F, 1.0F, 1.0F);
                } else {
                    return VoxelShapes.cuboid(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
                }
            } else 
            return VoxelShapes.cuboid(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
        else
            if (state.get(TOP) == 0) {
                if (dir == Direction.NORTH) {
                    return VoxelShapes.cuboid(0.0F, 0.0F, 0.4375F, 0.0625F, 1.0F, 0.5625F);
                } else if (dir == Direction.SOUTH) {
                    return VoxelShapes.cuboid(0.9375F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                } else {
                    return VoxelShapes.cuboid(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                }
            } else 
            return VoxelShapes.cuboid(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
    }

    public boolean canBePlacedAt(World worldIn, BlockPos pos, BlockState state, LivingEntity placer) {
        if (pos.getY() >= 254) return false;
        Direction var9 = placer.getHorizontalFacing();
		BlockPos pos2 = pos;
		switch (var9) {
		case NORTH:
			pos2 = pos.add(1, 0, 0);
			break;
		case EAST:
			pos2 = pos.add(0, 0, 1);
			break;
		case SOUTH:
			pos2 = pos.add(-1, 0, 0);
			break;
		case WEST:
			pos2 = pos.add(0, 0, -1);
			break;
		default:
			break;
        }

        if (worldIn.getBlockState(pos2).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos2.up()).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos2.up(2)).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos.up(2)).getMaterial().isReplaceable()) {
                    return true;
                }
        
                return false;
    }

    public void place(World worldIn, BlockPos pos, BlockState state, LivingEntity placer) {
		Direction var9 = placer.getHorizontalFacing();
		BlockPos pos2 = pos;
		switch (var9) {
		case NORTH:
			pos2 = pos.add(1, 0, 0);
			break;
		case EAST:
			pos2 = pos.add(0, 0, 1);
			break;
		case SOUTH:
			pos2 = pos.add(-1, 0, 0);
			break;
		case WEST:
			pos2 = pos.add(0, 0, -1);
			break;
		default:
			break;
        }
        
		if (worldIn.getBlockState(pos2).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos2.up()).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos2.up(2)).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos.up(2)).getMaterial().isReplaceable()) {
			worldIn.setBlockState(pos2,
					getDefaultState().with(FACING, var9.getOpposite()).with(TOP, 0));
			worldIn.setBlockState(pos2.add(0, 1, 0),
					getDefaultState().with(FACING, var9.getOpposite()).with(TOP, 1));
			worldIn.setBlockState(pos2.add(0, 2, 0),
					getDefaultState().with(FACING, var9.getOpposite()).with(TOP, 2));
			worldIn.setBlockState(pos.add(0, 1, 0), getDefaultState().with(FACING, var9).with(TOP, 1));
            worldIn.setBlockState(pos.add(0, 2, 0), getDefaultState().with(FACING, var9).with(TOP, 2));
            worldIn.setBlockState(pos, getDefaultState().with(FACING, var9).with(TOP, 0));
		}
    }

    @Override
    public void onPlaced(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
		Direction var9 = placer.getHorizontalFacing();
		BlockPos pos2 = pos;
		switch (var9) {
		case NORTH:
			pos2 = pos.add(1, 0, 0);
			break;
		case EAST:
			pos2 = pos.add(0, 0, 1);
			break;
		case SOUTH:
			pos2 = pos.add(-1, 0, 0);
			break;
		case WEST:
			pos2 = pos.add(0, 0, -1);
			break;
		default:
			break;
        }
        
		if (worldIn.getBlockState(pos2).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos2.up()).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos2.up(2)).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()
				&& worldIn.getBlockState(pos.up(2)).getMaterial().isReplaceable()) {
			worldIn.setBlockState(pos2,
					getDefaultState().with(FACING, var9.getOpposite()).with(TOP, 0));
			worldIn.setBlockState(pos2.add(0, 1, 0),
					getDefaultState().with(FACING, var9.getOpposite()).with(TOP, 1));
			worldIn.setBlockState(pos2.add(0, 2, 0),
					getDefaultState().with(FACING, var9.getOpposite()).with(TOP, 2));
			worldIn.setBlockState(pos.add(0, 1, 0), getDefaultState().with(FACING, var9).with(TOP, 1));
            worldIn.setBlockState(pos.add(0, 2, 0), getDefaultState().with(FACING, var9).with(TOP, 2));
            worldIn.setBlockState(pos, getDefaultState().with(FACING, var9).with(TOP, 0));
		}
    }
    
    @Override
	public void onBroken(IWorld worldIn, BlockPos pos, BlockState state) {
		BlockPos pos2 = pos;
		boolean b = false;
		switch (state.get(FACING)) {
		case NORTH:
			pos2 = pos.add(1, 0, 0);
			break;
		case EAST:
			pos2 = pos.add(0, 0, 1);
			break;
		case SOUTH:
			pos2 = pos.add(-1, 0, 0);
			break;
		case WEST:
			pos2 = pos.add(0, 0, -1);
			break;
		default:
			break;
		}
		switch (state.get(TOP)) {
        case 0:
            if (worldIn.getBlockState(pos.add(0, 1, 0)).getBlock() instanceof BlockMap)
                worldIn.breakBlock(pos.add(0, 1, 0), false);
            if (worldIn.getBlockState(pos.add(0, 2, 0)).getBlock() instanceof BlockMap)
                worldIn.breakBlock(pos.add(0, 2, 0), false);
            if (worldIn.getBlockState(pos2).getBlock() instanceof BlockMap)
                worldIn.breakBlock(pos2, false);
            if (worldIn.getBlockState(pos2.add(0, 1, 0)).getBlock() instanceof BlockMap)
                worldIn.breakBlock(pos2.add(0, 1, 0), false);
            if (worldIn.getBlockState(pos2.add(0, 2, 0)).getBlock() instanceof BlockMap)
                worldIn.breakBlock(pos2.add(0, 2, 0), false);
			break;
		case 1:
        if (worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos.add(0, -1, 0), false);
        if (worldIn.getBlockState(pos.add(0, 1, 0)).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos.add(0, 1, 0), false);
        if (worldIn.getBlockState(pos2).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos2, false);
        if (worldIn.getBlockState(pos2.add(0, -1, 0)).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos2.add(0, -1, 0), false);
        if (worldIn.getBlockState(pos2.add(0, 1, 0)).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos2.add(0, 1, 0), false);
			break;
		case 2:
        if (worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos.add(0, -1, 0), false);
        if (worldIn.getBlockState(pos.add(0, -2, 0)).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos.add(0, -2, 0), false);
        if (worldIn.getBlockState(pos2).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos2, false);
        if (worldIn.getBlockState(pos2.add(0, -1, 0)).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos2.add(0, -1, 0), false);
        if (worldIn.getBlockState(pos2.add(0, -2, 0)).getBlock() instanceof BlockMap)
            worldIn.breakBlock(pos2.add(0, -2, 0), false);
			break;
		}
		if (!(worldIn.getBlockState(pos2).getBlock() instanceof BlockMap) || b)
			worldIn.breakBlock(pos, false);
	}
}
