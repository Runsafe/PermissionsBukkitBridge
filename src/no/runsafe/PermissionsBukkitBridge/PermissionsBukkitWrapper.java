package no.runsafe.PermissionsBukkitBridge;

import com.platymuus.bukkit.permissions.Group;
import com.platymuus.bukkit.permissions.PermissionInfo;
import com.platymuus.bukkit.permissions.PermissionsPlugin;
import no.runsafe.framework.hook.IPlayerBuildPermission;
import no.runsafe.framework.hook.IPlayerPermissions;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.RunsafeServer;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionsBukkitWrapper implements IPlayerPermissions, IPlayerBuildPermission
{
	public PermissionsBukkitWrapper(IOutput console)
	{
		this.console = console;
	}

	@Override
	public List<String> getUserGroups(RunsafePlayer player)
	{
		if (!hasPermissionsBukkit())
			return null;
		ArrayList<String> results = new ArrayList<String>();
		for (Group group : permissionsPlugin.getGroups(player.getName()))
			results.add(group.getName());
		console.fine("PermissionsBukkit says %s has groups %s", player, results);
		return results;
	}

	@Override
	public List<String> getGroupPermissions(String groupName)
	{
		// Trying to get this information from PermissionsBukkit only throws a NullPointerException.
		return null;
	}

	@Override
	public List<String> getPlayerPermissions(RunsafePlayer player)
	{
		PermissionInfo info = permissionsPlugin.getPlayerInfo(player.getName());
		if (info != null)
		{
			ArrayList<String> granted = new ArrayList<String>();
			for (Map.Entry<String, Boolean> permission : info.getPermissions().entrySet())
				if (permission.getValue())
					granted.add(permission.getKey());
			return granted;
		}
		return null;
	}

	@Override
	public boolean blockPlayerBuilding(RunsafePlayer player, RunsafeLocation location)
	{
		return !player.hasPermission("permissions.build");
	}

	private boolean hasPermissionsBukkit()
	{
		if (permissionsPlugin == null)
			permissionsPlugin = RunsafeServer.Instance.getPlugin("PermissionsBukkit");
		console.fine("Checking for PermissionsBukkit.. %s", permissionsPlugin);
		return permissionsPlugin != null;
	}

	private PermissionsPlugin permissionsPlugin;
	private final IOutput console;
}
