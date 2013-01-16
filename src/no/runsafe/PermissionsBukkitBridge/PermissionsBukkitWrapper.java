package no.runsafe.PermissionsBukkitBridge;

import com.platymuus.bukkit.permissions.Group;
import com.platymuus.bukkit.permissions.PermissionsPlugin;
import no.runsafe.framework.hook.IPlayerBuildPermission;
import no.runsafe.framework.hook.IPlayerPermissions;
import no.runsafe.framework.plugin.PluginResolver;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.ArrayList;
import java.util.List;

public class PermissionsBukkitWrapper implements IPlayerPermissions, IPlayerBuildPermission
{
	public PermissionsBukkitWrapper(PluginResolver pluginResolver)
	{
		resolver = pluginResolver;
	}

	@Override
	public List<String> getUserGroups(RunsafePlayer player)
	{
		if (!hasPermissionsBukkit())
			return null;
		ArrayList<String> results = new ArrayList<String>();
		for (Group group : permissionsPlugin.getGroups(player.getName()))
			results.add(group.getName());
		return results;
	}

	@Override
	public boolean blockPlayerBuilding(RunsafePlayer player, RunsafeLocation location)
	{
		return !player.hasPermission("permissions.build");
	}

	private boolean hasPermissionsBukkit()
	{
		if (permissionsPlugin == null)
			permissionsPlugin = getPermissionsBukkit();
		return permissionsPlugin != null;
	}

	private PermissionsPlugin getPermissionsBukkit()
	{
		return resolver.getPlugin("PermissionsBukkit");
	}

	private PluginResolver resolver;
	private PermissionsPlugin permissionsPlugin;
}
