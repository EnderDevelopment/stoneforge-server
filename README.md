# Minecraft Server Resource Reporting Plugin

Stoneforge Server is a Minecraft server plugin that reports the Java process’s memory limit, visible processor count, and available and total storage for the world directory. Use this resource reporting plugin to inspect what the server process can see; it does not configure or verify a hosting plan’s purchased resources.

## Install

1. Build the plugin with Java 21 and Maven: `mvn package`.
2. Copy `target/plugin.jar` into your Paper server’s `plugins` directory.
3. Start or restart the server.

## Use

1. Run `/serverresources` in-game or from the server console.
2. To let a non-operator run the command, grant the `resourcereport.view` permission using your permissions plugin.

---

More Minecraft plugin projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/ede1cc73-22d9-4090-a43e-e2b3c294ecd6?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=ede1cc73-22d9-4090-a43e-e2b3c294ecd6
