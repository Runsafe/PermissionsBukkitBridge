package no.runsafe.PermissionsBukkitBridge;

import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.server.player.RunsafePlayer;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void PluginSetup()
	{
		RunsafePlayer.permissionHooks.add(new PermissionsBukkitWrapper());
	}
}
