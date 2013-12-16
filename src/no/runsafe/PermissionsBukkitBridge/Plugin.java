package no.runsafe.PermissionsBukkitBridge;

import no.runsafe.framework.RunsafePlugin;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void pluginSetup()
	{
		addComponent(PermissionsBukkitWrapper.class);
	}
}
